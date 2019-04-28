package storming.uml_editor.view;

import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import storming.uml_editor.controller.UML_Controller;
import storming.uml_editor.model.UML_Element;
import storming.uml_editor.model.relationships.UML_Dependency;
import storming.uml_editor.model.relationships.UML_Generalization;
import storming.uml_editor.model.relationships.UML_Relationship;
import storming.uml_editor.model.relationships.associations.UML_Aggregation;
import storming.uml_editor.model.relationships.associations.UML_Association;
import storming.uml_editor.model.relationships.associations.UML_Composition;
import storming.uml_editor.model.things.UML_Thing;
import storming.uml_editor.model.things.classbox.UML_Attribute;
import storming.uml_editor.model.things.classbox.UML_ClassBox;
import storming.uml_editor.model.things.classbox.UML_Operation;

/**
 * A JavaFX view for a UML Model
 */
public class UML_View extends Application {
	private UML_Controller controller;

	private Inspector inspect = new Inspector();
	private Drawer drawer = new Drawer();

	private Runnable onUnfocus = null;

	@FXML
	private VBox inspector;

	@FXML
	private ScrollPane scroll;

	@FXML
	private Pane items;

	/*
	 * UML_View contains a 'main' to make certain parts of connecting with JavaFX
	 * easier. Additionally, with no other previous setup needed, and since the view
	 * is the place where every user begins, it makes sense that it could handle
	 * 'main'
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Constructs a UML_View. This view will automatically attach to a controller
	 */
	public UML_View() {
		this.controller = new UML_Controller(this);
	}

	/**
	 * An entry point for JavaFX
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/storming-logo.png")));
		
		primaryStage.setTitle("Storming UML Editor");
		primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/try.fxml"))));

		Scene scene = primaryStage.getScene();
		scene.getStylesheets().add(getClass().getResource("/storming_in_style.css").toExternalForm());

		// primaryStage.getScene().getStylesheets().add("/storming_in_style.css");
		primaryStage.show();
	}

	/**
	 * A function utilized by JavaFX that will put a Class Box on the screen
	 *
	 * @param event The MouseEvent that triggered this function
	 */
	@FXML
	private void putClassBox(MouseEvent event) {
		double width = 150;
		double height = 200;

		double x = scroll.getHvalue() * items.getWidth() - (width / 2);
		double y = scroll.getVvalue() * items.getHeight() - (height / 2);
		
		drawer.draw(controller.put(new UML_ClassBox(x, y, width, height)));
	}

	/**
	 * A function utilized by JavaFX that will put a Dependency on the screen
	 *
	 * @param event The MouseEvent that triggered this function
	 */
	@FXML
	private void putDependency(MouseEvent event) {
		putRelationship(new UML_Dependency());
	}

	/**
	 * A function utilized by JavaFX that will put a Generalization on the screen
	 *
	 * @param event The MouseEvent that triggered this function
	 */
	@FXML
	private void putGeneralization(MouseEvent event) {
		putRelationship(new UML_Generalization());
	}

	/**
	 * A function utilized by JavaFX that will put an Aggregation on the screen
	 *
	 * @param event The MouseEvent that triggered this function
	 */
	@FXML
	private void putAggregation(MouseEvent event) {
		putRelationship(new UML_Aggregation());
	}

	/**
	 * A function utilized by JavaFX that will put an Association on the screen
	 *
	 * @param event The MouseEvent that triggered this function
	 */
	@FXML
	private void putAssociation(MouseEvent event) {
		putRelationship(new UML_Association());
	}

	/**
	 * A function utilized by JavaFX that will put a Composition on the screen
	 *
	 * @param event The MouseEvent that triggered this function
	 */
	@FXML
	private void putComposition(MouseEvent event) {
		putRelationship(new UML_Composition());

	}

	/**
	 * A function utilized by JavaFX that will zoom in on the UML Elements
	 *
	 * @param event The ZoomEvent that triggered this function
	 */
	@FXML
	private void zoom(ZoomEvent event) {
		items.setScaleX(items.getScaleX() * event.getZoomFactor());
		items.setScaleY(items.getScaleY() * event.getZoomFactor());
	}

	/**
	 * A function utilized by JavaFX that opens a save dialog and prints a 
	 * UML diagram to a PDF
	 * 
	 * @param event The ActionEvent that triggered this function
	 */
	/*
	 * idea from: https://stackoverflow.com/questions/23590974/how-to-take-snapshot-from-node-which-is-not-on-the-scene
	 */
	@FXML
	void print(ActionEvent event) {
		var out = new FileChooser().showSaveDialog(items.getScene().getWindow());
		System.out.println(out);
		
		var tempX = items.getScaleX();
		var tempY = items.getScaleY();
		items.setScaleX(1.0);
		items.setScaleY(1.0);
		var img = items.snapshot(new SnapshotParameters(), null);
		items.setScaleX(tempX);
		items.setScaleY(tempY);


		try {
			ImageIO.write(SwingFXUtils.fromFXImage(img, null), "png", out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * A function utilized by JavaFX that opens a save dialog and saves the 
	 * UML diagram to .storm file
	 * 
	 * @param event The ActionEvent that triggered this function
	 */
	@FXML
	private void save(ActionEvent event) {
		var filepath = new FileChooser().showSaveDialog(items.getScene().getWindow()).getAbsolutePath();
		filepath += ".storm";

		controller.save(filepath);
	}
	
	/**
	 * A function utilized by JavaFX that opens a open dialog and opens a
	 * .storm file
	 * 
	 * @param event The ActionEvent that triggered this function
	 */
	@FXML
	private void load(ActionEvent event) {
		var filepath = new FileChooser().showOpenDialog(items.getScene().getWindow()).getAbsolutePath();
		controller.load(filepath);
	}

	/**
	 * A helper utilized for putting a Relationship on the screen. This handles
	 * setting up an EventFilter to catch which Class Boxes were clicked
	 *
	 * @param rel The relationship that is having its source and target set
	 */
	private void putRelationship(UML_Relationship rel) {
		// A lambda cannot be used because we need the "this" context
		items.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Node n = (Node) e.getTarget();
				
				System.out.println(n.getStyleClass());
				
				if (!n.getStyleClass().contains("classbox-mask"))
					items.removeEventFilter(MouseEvent.MOUSE_CLICKED, this);
				else
				{
					while (!(n instanceof StackPane))
						n = n.getParent();

					if (!rel.hasSource())
						rel.putSource(controller.get(Integer.parseInt(n.getId()), UML_Thing.class));
					else {
						rel.putTarget(controller.get(Integer.parseInt(n.getId()), UML_Thing.class));
						items.removeEventFilter(MouseEvent.MOUSE_CLICKED, this);
						drawer.draw(controller.put(rel));
					}
				}
			}
		});
	}

	/**
	 * Unfocuses a previously focused element via a callback so that a new element
	 * can be put in focus
	 *
	 * @param onUnfocus A callback to be called that will unfocus an element the
	 *                  next time 'focus' is called
	 */
	private void focus(Runnable onUnfocus) {
		if (this.onUnfocus != null)
			this.onUnfocus.run();

		this.onUnfocus = onUnfocus;
	}
	
	/**
	 * Draws a UML Element to the view
	 * 
	 * @param elem The UML_Element to draw
	 */
	public void draw(UML_Element elem) {
		drawer.draw(elem);
	}
	
	/**
	 * Clears all UML Elements from the view
	 */
	public void clear() {
		items.getChildren().clear();
		inspect.clear();
	}

	/**
	 * A helper class that handles drawing of all UML Elements. This class should be
	 * used as a SINGLETON, but due to Java conventions, this design is not
	 * possible. If multiple instances of this class are made and used it will act
	 * as if only one were made (albeit it would not make sense while multiple
	 * instances were made so that practice should be avoided)
	 */
	private class Drawer {
		/**
		 * Draws a UML_Element to the screen
		 *
		 * @param elem The element (and more importantly, its properties) that should be
		 *             drawn
		 */
		public void draw(UML_Element elem) {
			if (lookup(elem) != null)
				delete(elem);

			// This should never throw...
			Node n = null;
			if (elem instanceof UML_ClassBox)
				n = make((UML_ClassBox) elem);
			else if (elem instanceof UML_Dependency)
				n = make((UML_Dependency) elem);
			else if (elem instanceof UML_Generalization)
				n = make((UML_Generalization) elem);
			else if (elem instanceof UML_Aggregation)
				n = make((UML_Aggregation) elem);
			else if (elem instanceof UML_Composition)
				n = make((UML_Composition) elem);
			else if (elem instanceof UML_Association)
				n = make((UML_Association) elem);
			

			n.setId(elem.getKey().toString());

			add(n);

			if (elem instanceof UML_ClassBox) {
				n.toFront();

				inspect.update((UML_ClassBox) elem);
			}
			if (elem instanceof UML_Relationship)
				n.toBack();
		}

		/**
		 * Returns the JavaFX node that represents a UML_Element
		 *
		 * @param elem The UML_Element to find in the view
		 * @return The JavaFX Node that represents the given UML_Element
		 */
		private Node lookup(UML_Element elem) {
			return items.lookup('#' + elem.getKey().toString());
		}

		/**
		 * Adds a Node to the screen
		 *
		 * @param n The Node to add
		 */
		private void add(Node n) {
			items.getChildren().add(n);
		}

		/**
		 * Deletes the JavaFX node from the screen that represents a UML_Element
		 *
		 * @param elem The UML_Element that is displayed in the view
		 *
		 *             ** BUG ** Lines connected to classboxes are not deleted when the
		 *             classbox is deleted. This should be fixed by adding an
		 *             ArrayList<UML_Relationship> to UML_ClassBox and iterating through
		 *             the list to delete relationships
		 */
		private void delete(UML_Element elem) {
			items.getChildren().remove(lookup(elem));
		}

		/**
		 * Makes a StackPane containing all the components and bindings needed to
		 * represent the given UML_ClassBox
		 *
		 * @param cbox The UML_ClassBox to represent
		 * @return A StackPane that represents a Class Box
		 */
		private StackPane make(UML_ClassBox cbox) {
			var content = new VBox();

			/*
			 *  If you put the styling at the end, the rest of the code blocks the css from
			 *  coming through
			 */
			
			content.getStyleClass().add("classbox");

			var name = new Text(cbox.getName());
			name.wrappingWidthProperty().bind(cbox.widthProperty());
			name.textProperty().bind(cbox.nameProperty());
			name.setFill(Color.web("#FFFFFF"));

			content.getChildren().addAll(name, new Separator());

			for (var attr : cbox.getAttributes()) {
				var attrVisibility = new Text(attr.getVisibility());
				// Fancy binding stops null from being output when empty
				attrVisibility.textProperty().bind(
					Bindings
					.when(attr.visibilityProperty().isEmpty())
					.then(" ")
					.otherwise(attr.visibilityProperty().concat(" "))
				);
				attrVisibility.setFill(Color.web("#ee0290"));

				var attrName = new Text(attr.getName());
				// Fancy binding stops null from being output when empty
				attrName.textProperty().bind(
					Bindings
					.when(attr.nameProperty().isEmpty())
					.then("")
					.otherwise(attr.nameProperty().concat(": "))
				);
				attrName.setFill(Color.web("#FFFFFF"));
				var attrType = new Text(attr.getType());
				attrType.textProperty().bind(attr.typeProperty());
				attrType.setFill(Color.web("#FFFFFF"));
				
				var entry = new HBox(attrVisibility, attrName, attrType);
				entry.setId(cbox.getKey().toString() + '-' + attr.getKey().toString());
				
				content.getChildren().add(entry);
			}

			content.getChildren().add(new Separator());

			for (var op : cbox.getOperations()) {
				var opVisibility = new Text(op.getVisibility());
				// Fancy binding stops null from being output when empty
				opVisibility.textProperty().bind(
					Bindings
					.when(op.visibilityProperty().isEmpty())
					.then(" ")
					.otherwise(op.visibilityProperty().concat(" "))
				);
				opVisibility.setFill(Color.web("#ee0290"));
				var opSignature = new Text(op.getSignature());
				opSignature.textProperty().bind(op.signatureProperty());
				opSignature.setFill(Color.web("#FFFFFF"));
				
				var entry = new HBox(opVisibility, opSignature);
				entry.setId(cbox.getKey().toString() + '-' + op.getKey().toString());

				content.getChildren().add(entry);
			}

			content.getChildren().add(new Separator());

			var extra = new Text(cbox.getExtra());
			extra.wrappingWidthProperty().bind(cbox.widthProperty());
			extra.textProperty().bind(cbox.extraProperty());
			extra.setFill(Color.web("#FFFFFF"));

			content.getChildren().add(extra);

			var r = new Rectangle(cbox.getWidth(), cbox.getHeight());
			r.setFill(Color.WHITE);
			r.widthProperty().bind(cbox.widthProperty());
			r.heightProperty().bind(cbox.heightProperty());
			
			// Mask used when creating relationships
			var mask = new Rectangle(cbox.getWidth(), cbox.getHeight());
			mask.setFill(Color.TRANSPARENT);
			mask.widthProperty().bind(r.widthProperty());
			mask.heightProperty().bind(r.heightProperty());
			mask.getStyleClass().add("classbox-mask");

			var resize = new Rectangle(10, 10);
			resize.setFill(Color.WHITE);
			// This needs to rest on top of the normal mask to be dragged
			resize.getStyleClass().add("classbox-mask");

			resize.setOnMousePressed((press) -> {
				var clickStartX = press.getSceneX();
				var clickStartY = press.getSceneY();
				var rOriginalWidth = r.getWidth();
				var rOriginalHeight = r.getHeight();

				resize.setOnMouseDragged((drag) -> {
					var newWidth = rOriginalWidth + drag.getSceneX() - clickStartX;
					var newHeight = rOriginalHeight + drag.getSceneY() - clickStartY;

					if (newWidth >= 150)
						cbox.setWidth(newWidth);

					if (newHeight >= 150)
						cbox.setHeight(newHeight);

					drag.consume();
				});

				resize.setOnMouseReleased((release) -> {
					resize.setOnMouseDragged(null);
					resize.setOnMouseReleased(null);

					release.consume();
				});

				press.consume();
			});

			var box = new StackPane(r, content, mask, resize);
			box.layoutXProperty().bind(cbox.xProperty());
			box.layoutYProperty().bind(cbox.yProperty());

			StackPane.setAlignment(resize, Pos.BOTTOM_RIGHT);

			box.setOnMousePressed((press) -> {
				var clickStartX = press.getSceneX();
				var clickStartY = press.getSceneY();
				var boxOriginX = box.getLayoutX();
				var boxOriginY = box.getLayoutY();

				box.setOnMouseDragged((drag) -> {
					cbox.setX(boxOriginX + (drag.getSceneX() - clickStartX));
					cbox.setY(boxOriginY + (drag.getSceneY() - clickStartY));

					drag.consume();
				});

				box.setOnMouseReleased((release) -> {
					box.setOnMouseDragged(null);
					box.setOnMouseReleased(null);

					release.consume();
				});

				press.consume();
			});

			box.setOnMouseClicked((e) -> {
				focus(() -> {
					content.setBorder(new javafx.scene.layout.Border(new BorderStroke(Color.web("6002EE"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
				});

				content.setBorder(new javafx.scene.layout.Border(new BorderStroke(Color.web("EE6002"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

				inspect.update(cbox);

				box.toFront();

				e.consume();
			});

			focus(() -> {
				content.setBorder(new javafx.scene.layout.Border(new BorderStroke(Color.web("6002EE"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			});
			
			content.setBorder(new javafx.scene.layout.Border(new BorderStroke(Color.web("EE6002"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

			return box;
		}
		
		/**
		 * Connects (binds) an arrow to a line so that it rotates properly
		 * 
		 * @param line The line to connect the arrow to
		 * @param arrow The arrow to connect
		 */
		private void connectArrow(Line line, Polygon arrow) {
			arrow.layoutXProperty().bind(line.endXProperty());
			arrow.layoutYProperty().bind(line.endYProperty());
			
			DoubleBinding xDist = line.startXProperty().subtract(line.endXProperty());
			DoubleBinding yDist = line.startYProperty().subtract(line.endYProperty());
			DoubleBinding theta = Bindings.createDoubleBinding(() -> {
				return Math.toDegrees(Math.atan(yDist.get() / xDist.get()));
			}, yDist, xDist);
			
			arrow.rotateProperty()
			.bind(
				Bindings
				.when( // Top left
					line.startXProperty().lessThan(line.endXProperty())
					.and(line.startYProperty().lessThan(line.endYProperty()))
				)
				.then(theta.subtract(90))
				.otherwise(
					Bindings
					.when( // Bottom left
						line.startXProperty().lessThan(line.endXProperty())
						.and(line.startYProperty().greaterThan(line.endYProperty()))
					)
					.then(theta.subtract(90))
					.otherwise(
						Bindings
						.when( // Bottom right
							line.startXProperty().greaterThan(line.endXProperty())
							.and(line.startYProperty().greaterThan(line.endYProperty()))
						)
						.then(theta.add(90))
						.otherwise( // Top right
							Bindings.createDoubleBinding(() -> {
								return (theta.get() + 90);
							}, theta)
						)
					)
				)
			);
		}

		/**
		 * Makes a Group containing all the components and bindings needed to represent
		 * the given UML_Dependency
		 *
		 * @param dep The UML_Dependency to represent
		 * @return A Group that represents a Dependency
		 */
		private Group make(UML_Dependency dep) {
			var rel = make((UML_Relationship) dep);

			var line = (Line) rel.getChildren().get(1);
			var arrow = new Polygon(0.0, 0.0, -10.0, -10.0, 0.0, 0.0, 10.0, -10.0, 0.0, 0.0);
			
			connectArrow(line, arrow);
			
			rel.getChildren().add(arrow);
			
			rel.getStyleClass().add("dependency");
			
			return rel;
		}

		/**
		 * Makes a Group containing all the components and bindings needed to represent
		 * the given UML_Generalization
		 *
		 * @param gen The UML_Generalization to represent
		 * @return A Group that represents a Generalization
		 */
		private Group make(UML_Generalization gen) {
			var rel = make((UML_Relationship) gen);

			var line = (Line) rel.getChildren().get(1);
			var arrow = new Polygon(0.0, 0.0, -10.0, -10.0, 10.0, -10.0);
			
			connectArrow(line, arrow);

			rel.getChildren().add(arrow);

			rel.getStyleClass().add("generalization");

			return rel;
		}

		/**
		 * Makes a Group containing all the components and bindings needed to represent
		 * the given UML_Aggregation
		 *
		 * @param agg The UML_Aggregation to represent
		 * @return A Group that represents a Aggregation
		 */
		private Group make(UML_Aggregation agg) {
			var rel = make((UML_Relationship) agg);
			
			var line = (Line) rel.getChildren().get(1);
			var arrow = new Polygon(0.0, 0.0, -10.0, -10.0, 0.0, -20.0, 10.0, -10.0);
			
			connectArrow(line, arrow);

			rel.getChildren().add(arrow);

			rel.getStyleClass().add("aggregation");

			return rel;
		}

		/**
		 * Makes a Group containing all the components and bindings needed to represent
		 * the given UML_Association
		 *
		 * @param assoc The UML_Association to represent
		 * @return A Group that represents a Association
		 */
		private Group make(UML_Association assoc) {
			var rel = make((UML_Relationship) assoc);

			var line = (Line) rel.getChildren().get(1);
			var arrow = new Polygon(0.0, 0.0, -10.0, -10.0, 0.0, 0.0, 10.0, -10.0, 0.0, 0.0);
			
			connectArrow(line, arrow);
			
			rel.getChildren().add(arrow);
			
			rel.getStyleClass().add("association");
			
			return rel;
		}

		/**
		 * Makes a Group containing all the components and bindings needed to represent
		 * the given UML_Composition
		 *
		 * @param comp The UML_Composition to represent
		 * @return A Group that represents a Composition
		 */
		private Group make(UML_Composition comp) {
			var rel = make((UML_Relationship) comp);
			
			var line = (Line) rel.getChildren().get(1);
			var arrow = new Polygon(0.0, 0.0, -10.0, -10.0, 0.0, -20.0, 10.0, -10.0);
			
			connectArrow(line, arrow);

			rel.getChildren().add(arrow);

			rel.getStyleClass().add("composition");
			
			return rel;
		}

		/**
		 * Makes a Group containing all the components and bindings needed to represent
		 * the given UML_Relationship
		 *
		 * @param rel The UML_Relationship to represent
		 * @return A Group that represents a Relationship
		 */
		private Group make(UML_Relationship rel) {
			var l = new Line(rel.getSource().getX(), rel.getSource().getY(), rel.getTarget().getX(),
					rel.getTarget().getY());
			
			/*
			 * This next chunk of code is a massive group of math that calculates where
			 * the arrow of a line should be placed at in relation to a relationships
			 * target
			 */
			DoubleBinding xDist = rel.getSource().centerXProperty().subtract(rel.getTarget().centerXProperty());
			DoubleBinding yDist = rel.getTarget().centerYProperty().subtract(rel.getSource().centerYProperty());
			DoubleBinding theta = Bindings.createDoubleBinding(() -> {
				return Math.toDegrees(Math.atan(yDist.get() / xDist.get()));
			}, yDist, xDist);
			
			var source = rel.getSource();
			var target = rel.getTarget();
			
			DoubleBinding boundTheta = Bindings.createDoubleBinding(() -> {
				return Math.toDegrees(Math.atan((target.heightProperty().get() / 2) / (target.widthProperty().get() / 2)));
			}, target.widthProperty(), target.heightProperty());
			
			/*
			 *  The subtract and add 20s in some of the return lines is padding so that arrows do not
			 *  get covered by the Class Box they point to
			 */
			l.endXProperty()
			.bind(
				Bindings
				.when(source.centerXProperty().lessThan(target.centerXProperty()))
				.then(
					Bindings
					.when(theta.lessThan(boundTheta).and(theta.greaterThan(boundTheta.negate())))
					.then(target.centerXProperty().subtract(target.widthProperty().divide(2)).subtract(20))
					.otherwise(
						Bindings.createDoubleBinding(() -> {
							return target.centerXProperty().get() - ((target.heightProperty().get() * Math.tan(Math.toRadians(90 - Math.abs(theta.get())))) / 2);
						}, target.heightProperty(), theta, target.centerXProperty())
					)
				).otherwise(
					Bindings
					.when(theta.lessThan(boundTheta).and(theta.greaterThan(boundTheta.negate())))
					.then(target.centerXProperty().add(target.widthProperty().divide(2)).add(20))
					.otherwise(
						Bindings.createDoubleBinding(() -> {
							return target.centerXProperty().get() + ((target.heightProperty().get() * Math.tan(Math.toRadians(90 - Math.abs(theta.get())))) / 2);
						}, target.heightProperty(), theta, target.centerXProperty())
					)
				)
			);
			
			/*
			 *  The subtract and add 20s in some of the return lines is padding so that arrows do not
			 *  get covered by the Class Box they point to
			 */
			l.endYProperty()
			.bind(
				Bindings
				.when(source.centerYProperty().lessThan(target.centerYProperty()))
				.then(
					Bindings
					.when(theta.greaterThan(boundTheta).or(theta.lessThan(boundTheta.negate())))
					.then(target.centerYProperty().subtract(target.heightProperty().divide(2)).subtract(20))
					.otherwise(
						Bindings.createDoubleBinding(() -> {
							return target.centerYProperty().get() - ((target.widthProperty().get() * Math.tan(Math.toRadians(Math.abs(theta.get())))) / 2);
						}, target.widthProperty(), theta, target.centerYProperty())
					)
				).otherwise(
					Bindings
					.when(theta.greaterThan(boundTheta).or(theta.lessThan(boundTheta.negate())))
					.then(target.centerYProperty().add(target.heightProperty().divide(2)).add(20))
					.otherwise(
						Bindings.createDoubleBinding(() -> {
							return target.centerYProperty().get() + ((target.widthProperty().get() * Math.tan(Math.toRadians(Math.abs(theta.get())))) / 2);
						}, target.widthProperty(), theta, target.centerYProperty())
					)
				)
			);
			
			l.startXProperty().bind(rel.getSource().centerXProperty());
			l.startYProperty().bind(rel.getSource().centerYProperty());

			var text = "";
			if (rel instanceof UML_Association)
			{
				text += ((UML_Association) rel).getSourceMultiplictyLower();
				text += "..";
				text += ((UML_Association) rel).getSourceMultiplictyUpper();
				text += "\t";
				text += rel.getName();
				text += "\t";
				text += ((UML_Association) rel).getTargetMultiplictyLower();
				text += "..";
				text += ((UML_Association) rel).getTargetMultiplictyUpper();
			}
			else
				text = rel.getName();
			
			var name = new Label(text);
			
			if (rel instanceof UML_Association)
			{
				name.textProperty().bind(
					Bindings.createStringBinding(
						() -> {
							var newText = "";
							newText += ((UML_Association) rel).getSourceMultiplictyLower();
							newText += "..";
							newText += ((UML_Association) rel).getSourceMultiplictyUpper();
							newText += "\t";
							newText += rel.hasName() ? rel.getName() : "";
							newText += "\t";
							newText += ((UML_Association) rel).getTargetMultiplictyLower();
							newText += "..";
							newText += ((UML_Association) rel).getTargetMultiplictyUpper();
							return newText;
						}, 
						rel.nameProperty(), 
						((UML_Association) rel).sourceMultiplictyLowerProperty(), 
						((UML_Association) rel).sourceMultiplictyUpperProperty(), 
						((UML_Association) rel).targetMultiplictyLowerProperty(), 
						((UML_Association) rel).targetMultiplictyUpperProperty()
					)
				);
			}
			else
				name.textProperty().bind(rel.nameProperty());

			var width = rel.getSource().centerXProperty().subtract(rel.getTarget().centerXProperty());
			var height = rel.getSource().centerYProperty().subtract(rel.getTarget().centerYProperty());

			// Does not handle divide by 0
			name.rotateProperty().bind(Bindings.createDoubleBinding(() -> {
				return Math.toDegrees(Math.atan(height.get() / width.get()));
			}, width, height));

			name.layoutXProperty().bind(l.startXProperty().add(l.endXProperty().subtract(l.startXProperty()).divide(2))
					.subtract(name.widthProperty().divide(2)));
			// The add 10 is just a padding
			name.layoutYProperty().bind(l.startYProperty().add(l.endYProperty().subtract(l.startYProperty()).divide(2))
					.subtract(name.heightProperty().divide(2).add(10)));

			var g = new Group(name, l);
			
			g.setOnMouseClicked((e) -> {
				focus(() -> {
					g.setOpacity(1.0);
				});

				g.setOpacity(0.8);

				inspect.update(rel);

				e.consume();
			});
			
			return g;
		}
	}

	/**
	 * A helper class that handles interaction with a UML Element inspector. Unlike
	 * the Drawer class, there may be instances where multiple instances of an
	 * Inspector could be useful.
	 */
	private class Inspector {
		/**
		 * Clears all the children from the inspector
		 */
		public void clear() {
			inspector.getChildren().clear();
		}

		/**
		 * Creates a node containing a label of the given text
		 *
		 * @param text The text to be in the label
		 * @return A Node representing the label
		 */
		private Label label(String text) {
			Label l = new Label(text);
			l.getStyleClass().add("inspector_labels");
			return l;
		}

		/**
		 * Creates a node that contains an editable field that will change the name of a
		 * UML_Element
		 *
		 * @param elem The UML_Element whose name is paired with this field
		 * @return A Node representing the field
		 */
		private Node name(UML_Element elem) {
			var name = new TextField(elem.getName());
			name.setOnKeyTyped((e) -> {
				elem.putName(name.getText());
			});
			return name;
		}

		/**
		 * Creates a node that is a button that will delete the given UML_Element
		 *
		 * @param elem The UML_Element to delete
		 * @return A Node representing the delete button
		 */
		private Button delete(UML_Element elem, String text) {
			return button(text, (e) -> {
				controller.remove(elem.getKey());
				UML_View.this.drawer.delete(elem);

				clear();
			});
		}

		/**
		 * Creates a button with the name of the given text that carries out a given
		 * action when clicked
		 *
		 * @param text     The text to display in the button
		 * @param onAction The action to carry out when the button is clicked
		 * @return A Node representing the button
		 */
		private Button button(String text, EventHandler<ActionEvent> onAction) {
			var button = new Button(text);
			button.getStyleClass().add("inspector_buttons");
			button.setOnAction(onAction);

			return button;
		}

		/**
		 * Adds a Node to the inspector
		 *
		 * @param n The Node to add
		 */
		private void addComponent(Node n) {
			inspector.getChildren().add(n);
		}
		
		/**
		 * Creates a spacer for spacing elements out in the inspector. This
		 * should not really be needed but for some reason the VBox will not
		 * space things itself.
		 * 
		 * @return A rectangle for spacing
		 */
		private Rectangle spacer(int height) {
			var r = new Rectangle(1, height);
			r.getStyleClass().add("spacer");
			return r;
		}

		/**
		 * Update the inspector to show and be able to modify the values of the given
		 * Class Box
		 *
		 * @param cbox The Class Box to pair with the inspector
		 */
		public void update(UML_ClassBox cbox) {
			clear();
					
			var vName = new VBox(label("Name"), 
					  name(cbox));
			vName.setSpacing(5);
			
			addComponent(vName);
			addComponent(spacer(20));
			
			addComponent(button("Add Attribute", (e) -> {
				cbox.putAttribute(new UML_Attribute());

				drawer.draw(cbox);
			}));
			
			addComponent(spacer(5));
			
			var attrVisLabel = label("Visibility");
			attrVisLabel.setMinWidth(70);
			
			var attrNameLabel = label("Name");
			attrNameLabel.setMinWidth(70);
			
			var attrTypeLabel = label("Type");
			attrTypeLabel.setMinWidth(70);

			var attrTitles = new HBox(attrVisLabel, attrNameLabel, attrTypeLabel);
			attrTitles.setSpacing(3);
			
			addComponent(attrTitles);

			// Go through attributes
			for (var attr : cbox.getAttributes()) {
				var choices = new ArrayList<String>();
				choices.add("+");
				choices.add("o");
				choices.add("-");
				choices.add("");

				var choose = new ChoiceBox<String>(FXCollections.observableArrayList(choices));
				choose.setMinWidth(70);
				choose.setMinHeight(30);
				if (attr.getVisibility() == null)
					choose.setValue("");
				else
					choose.setValue(attr.getVisibility());

				choose.setOnAction((e) -> {
					attr.putVisibility(choose.getValue());
				});

				var attrName = new TextField(attr.getName());
				attrName.setMinWidth(70);
				attrName.setMinHeight(30);
				attrName.setOnKeyTyped((e) -> {
					attr.putName(attrName.getText());
				});

				var attrType = new TextField(attr.getType());
				attrType.setMinWidth(70);
				attrType.setMinHeight(30);
				attrType.setOnKeyTyped((e) -> {
					attr.putType(attrType.getText());
				});
				
				var entry = new VBox(spacer(5));
				
				var hEntry = new HBox(3);
				
				var del = button("X", (e) -> {
					cbox.removeAttribute(attr.getKey());
					inspector.getChildren().remove(entry);
					
					var displayCbox = ((StackPane) items.lookup('#' + cbox.getKey().toString()));
					((VBox) displayCbox.getChildren().get(1)).getChildren().remove(displayCbox.lookup('#' + cbox.getKey().toString() + '-' + attr.getKey().toString()));
				});
				del.setMinHeight(30);
				del.setMinWidth(30);
				
				
				hEntry.getChildren().addAll(choose, attrName, attrType, del);
				
				entry.getChildren().add(hEntry);

				addComponent(entry);
			}
			
			addComponent(spacer(20));
						
			addComponent(button("Add Operation", (e) -> {
				cbox.putOperation(new UML_Operation());

				drawer.draw(cbox);
			}));
			
			addComponent(spacer(5));
			
			var opVisLabel = label("Visibility");
			var opSigLabel = label("Signature");
			
			var opTitles = new HBox(opVisLabel, opSigLabel);
			opTitles.setSpacing(3);

			addComponent(opTitles);

			// Go through operations
			for (var op : cbox.getOperations()) {
				var choices = new ArrayList<String>();
				choices.add("+");
				choices.add("o");
				choices.add("-");
				choices.add("");

				var choose = new ChoiceBox<String>(FXCollections.observableArrayList(choices));
				choose.setMinWidth(70);
				choose.setMinHeight(30);
				if (op.getVisibility() == null)
					choose.setValue("");
				else
					choose.setValue(op.getVisibility());

				choose.setOnAction((e) -> {
					op.putVisibility(choose.getValue());
				});

				var opSignature = new TextField(op.getSignature());
				opSignature.setMinWidth(150);
				opSignature.setMinHeight(30);
				opSignature.setOnKeyTyped((e) -> {
					op.putSignature(opSignature.getText());
				});
				
				var entry = new VBox(spacer(5));
						
				var hEntry = new HBox(choose, opSignature);
				hEntry.setSpacing(3);
				
				var del = button("X", (e) -> {
					cbox.removeOperation(op.getKey());
					inspector.getChildren().remove(entry);
					
					var displayCbox = ((StackPane) items.lookup('#' + cbox.getKey().toString()));
					((VBox) displayCbox.getChildren().get(1)).getChildren().remove(displayCbox.lookup('#' + cbox.getKey().toString() + '-' + op.getKey().toString()));
				});
				del.setMinWidth(30);
				del.setMinHeight(30);
				
				hEntry.getChildren().add(del);
				
				entry.getChildren().add(hEntry);

				addComponent(entry);
			}			
			
			addComponent(spacer(20));
			
			var extra_label = label("Extra");
			TextArea extra = new TextArea(cbox.getExtra());
			extra.setOnKeyTyped((e) -> {
				cbox.putExtra(extra.getText());
			});
			extra.setMinHeight(200);
			
			inspector.getChildren().addAll(extra_label, extra);
			
			addComponent(spacer(20));
			
			var del = delete(cbox, "Delete Classbox");
			
			addComponent(del);
		}

		/**
		 * Update the inspector to show and be able to modify the values of the given
		 * Relationship
		 *
		 * @param dep The Relationship to pair with the inspector
		 */
		public void update(UML_Relationship rel) {
			clear();
			
			var vName = new VBox(label("Name"), 
					  name(rel));
			vName.setSpacing(5);
			addComponent(vName);
			
			addComponent(spacer(20));
			
			if (rel instanceof UML_Association) {
				var sourceMultiplictyLower = new TextField(((UML_Association) rel).getSourceMultiplictyLower());
				sourceMultiplictyLower.setOnKeyTyped((e) -> {
					((UML_Association) rel).putSourceMultiplictyLower(sourceMultiplictyLower.getText());
				});
				var vSML = new VBox(label("Source Multiplicty Lower"), sourceMultiplictyLower);
				vSML.setSpacing(5);
				addComponent(vSML);
				addComponent(spacer(20));
				
				var sourceMultiplictyUpper = new TextField(((UML_Association) rel).getSourceMultiplictyUpper());
				sourceMultiplictyUpper.setOnKeyTyped((e) -> {
					((UML_Association) rel).putSourceMultiplictyUpper(sourceMultiplictyUpper.getText());
				});
				var vSMU = new VBox(label("Source Multiplicty Upper"), sourceMultiplictyUpper);
				vSMU.setSpacing(5);
				addComponent(vSMU);
				addComponent(spacer(20));
				
				var targetMultiplictyLower = new TextField(((UML_Association) rel).getTargetMultiplictyLower());
				targetMultiplictyLower.setOnKeyTyped((e) -> {
					((UML_Association) rel).putTargetMultiplictyLower(targetMultiplictyLower.getText());
				});
				var vTML = new VBox(label("Target Multiplicty Lower"), targetMultiplictyLower);
				vTML.setSpacing(5);
				addComponent(vTML);
				addComponent(spacer(20));
				
				var targetMultiplictyUpper = new TextField(((UML_Association) rel).getTargetMultiplictyUpper());
				targetMultiplictyUpper.setOnKeyTyped((e) -> {
					((UML_Association) rel).putTargetMultiplictyUpper(targetMultiplictyUpper.getText());
				});
				var vTMU = new VBox(label("Target Multiplicty Upper"), targetMultiplictyUpper);
				vTMU.setSpacing(5);
				addComponent(vTMU);
				addComponent(spacer(20));
			}
			addComponent(delete(rel, "Delete Relationship"));
		}
	}
}
