package storming.uml_editor.view;

import java.util.ArrayList;
import java.util.function.Consumer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.scene.text.TextFlow;
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
	
	public static void main(String[] args) {
    	launch(args);
    }
	
	public UML_View() {
		this.controller = new UML_Controller(this);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
//		Parent root = ;
		
		primaryStage.setTitle("Storming UML Editor");
		primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/try.fxml"))));
		primaryStage.show();
	}
		
	@FXML
	private void putClassBox(MouseEvent event) {
		double width = 150;
		double height = 200;
		
		double x = scroll.getHvalue() * items.getWidth() - (width / 2);
		double y = scroll.getVvalue() * items.getHeight() - (height / 2);
		
		drawer.draw(controller.put(new UML_ClassBox(x, y, width, height)));
	}
	
//	DESIGN see how we start at the leaves and then call the more general functions, we bubble up then trickle down
	
	@FXML
	private void putDependency(MouseEvent event) {		
		putRelationship(new UML_Dependency());	
	}
	
	@FXML
	private void putGeneralization(MouseEvent event) {
		putRelationship(new UML_Generalization());	
	}
	
	@FXML
	private void putAggregation(MouseEvent event) {
		putRelationship(new UML_Aggregation());	
	}
	
	@FXML
	private void putAssociation(MouseEvent event) {
		putRelationship(new UML_Association());	
	}
	
	@FXML
	private void putComposition(MouseEvent event) {
		putRelationship(new UML_Composition());	
	}
	
	@FXML
	private void zoom(ZoomEvent event) {
		items.setScaleX(items.getScaleX() * event.getZoomFactor());
		items.setScaleY(items.getScaleY() * event.getZoomFactor());
	}
	
//	It currently is a bug to not click on two class boxes when drawing a rel
	private void putRelationship(UML_Relationship rel) {
		// A lambda cannot be used because we need the "this" context
		items.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Node n = (Node) e.getTarget();
				
				while (!(n instanceof StackPane))
					n = n.getParent();
				
				if (!rel.hasSource())
					rel.putSource(controller.get(Integer.parseInt(n.getId()), UML_Thing.class));
				else
				{
					rel.putTarget(controller.get(Integer.parseInt(n.getId()), UML_Thing.class));
					items.removeEventFilter(MouseEvent.MOUSE_CLICKED, this);
					drawer.draw(controller.put(rel));
				}
			}
		});	
	}
	
	private void focus(Runnable onUnfocus) {
		if (this.onUnfocus != null)
			this.onUnfocus.run();
		
		this.onUnfocus = onUnfocus;
	}
	
	private class Drawer {		
		public void draw(UML_Element elem) {
			if (lookup(elem) != null)
				delete(elem);
			
//			This should never throw...
			Node n = null;
			if (elem instanceof UML_ClassBox)
				n = make((UML_ClassBox) elem);
			else if (elem instanceof UML_Dependency)
				n = make((UML_Dependency) elem);
			else if (elem instanceof UML_Generalization)
				n = make((UML_Generalization) elem);
			else if (elem instanceof UML_Aggregation)
				n = make((UML_Aggregation) elem);
			else if (elem instanceof UML_Association)
				n = make((UML_Association) elem);
			else if (elem instanceof UML_Composition)
				n = make((UML_Composition) elem);
			
			n.setId(elem.getKey().toString());
			
			add(n);
			
			if (elem instanceof UML_ClassBox)
			{
				n.toFront();

				inspect.update((UML_ClassBox) elem);
			}
			if (elem instanceof UML_Relationship)
				n.toBack();
		}
		
		private Node lookup(UML_Element elem) {
			return items.lookup('#' + elem.getKey().toString());
		}
		
		private void add(Node n) {
			items.getChildren().add(n);
		}
		
		// Lines are not deleted when a classbox is deleted
		private void delete(UML_Element elem) {
			items.getChildren().remove(lookup(elem));
		}
		
		private StackPane make(UML_ClassBox cbox) {
			var content = new VBox();
			
			var name = new Text(cbox.getName());
			name.wrappingWidthProperty().bind(cbox.widthProperty());
			name.textProperty().bind(cbox.nameProperty());
			
			content.getChildren().addAll(name, new Separator());
			
			for (var attr : cbox.getAttributes()) {
				var attrVisibility = new Text(attr.getVisibility());
				attrVisibility.textProperty().bind(attr.visibilityProperty());
				
				var attrName = new Text(attr.getName());
				attrName.textProperty().bind(attr.nameProperty());
				
				var attrType = new Text(attr.getType());
				attrType.textProperty().bind(attr.typeProperty());
				
				content.getChildren().add(new HBox(attrVisibility, attrName, attrType));
			}
			
			content.getChildren().add(new Separator());
			
			for (var op : cbox.getOperations()) {
				var opVisibility = new Text(op.getVisibility());
				opVisibility.textProperty().bind(op.visibilityProperty());
				
				var opSignature = new Text(op.getSignature());
				opSignature.textProperty().bind(op.signatureProperty());
				
				content.getChildren().add(new HBox(opVisibility, opSignature));
			}
			
			content.getChildren().add(new Separator());
			
			var extra = new Text(cbox.getExtra());
			extra.wrappingWidthProperty().bind(cbox.widthProperty());
			extra.textProperty().bind(cbox.extraProperty());
			
			content.getChildren().add(extra);
			
			var r = new Rectangle(cbox.getWidth(), cbox.getHeight());	
			r.setFill(Color.WHITE);
			r.widthProperty().bind(cbox.widthProperty());
			r.heightProperty().bind(cbox.heightProperty());
			
			var resize = new Rectangle(10, 10);
			resize.setFill(Color.DARKGRAY);
			
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
			
			var box = new StackPane(r, content, resize);
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
					r.setStroke(Color.BLACK);
				});
				
				r.setStroke(Color.DARKTURQUOISE);

				inspect.update(cbox);
				
				box.toFront();
				
				e.consume();
			});
			
			box.getStyleClass().add("classbox");
			
			focus(() -> {
				r.setStroke(Color.BLACK);
			});
			
			return box;
		}
		
		private Group make(UML_Dependency dep) {
			var rel = make((UML_Relationship) dep);
			rel.getStyleClass().add("dependency");
			
			return rel;
		}
		
		private Group make(UML_Generalization gen) {
			var rel = make((UML_Relationship) gen);
			rel.getStyleClass().add("generalization");
			
			return rel;
		}
		
		private Group make(UML_Aggregation gen) {
			var rel = make((UML_Relationship) gen);
			rel.getStyleClass().add("aggregation");
			
			return rel;
		}
		
		private Group make(UML_Association gen) {
			var rel = make((UML_Relationship) gen);
			rel.getStyleClass().add("association");
			
			return rel;
		}
		
		private Group make(UML_Composition gen) {
			var rel = make((UML_Relationship) gen);
			rel.getStyleClass().add("composition");
			
			return rel;
		}
		
		private Group make(UML_Relationship rel) {
			var l = new Line(rel.getSource().getX(),
			 		 rel.getSource().getY(),
			 		 rel.getTarget().getX(),
			 		 rel.getTarget().getY());

			l.startXProperty().bind(rel.getSource().centerXProperty());
			l.startYProperty().bind(rel.getSource().centerYProperty());
			l.endXProperty().bind(rel.getTarget().centerXProperty());
			l.endYProperty().bind(rel.getTarget().centerYProperty());
			
			l.setOnMouseClicked((e) -> {
				focus(() -> {
					l.setStroke(Color.BLACK);
				});
				
				l.setStroke(Color.DARKTURQUOISE);
			
				inspect.update(rel);
				
				e.consume();
			});
			
			var name = new Label(rel.getName());
			name.textProperty().bind(rel.nameProperty());
			
			//Does not handle divide by 0
			var width  = rel.getSource().centerXProperty().subtract(rel.getTarget().centerXProperty());
			var height = rel.getSource().centerYProperty().subtract(rel.getTarget().centerYProperty());
			
			
			//return can be one-lined
			name.rotateProperty().bind(Bindings.createDoubleBinding(
					() -> {
						return Math.toDegrees(Math.atan(height.get() / width.get()));
					}, width, height));
			
			name.layoutXProperty().bind(l.startXProperty().add(l.endXProperty().subtract(l.startXProperty()).divide(2)).subtract(name.widthProperty().divide(2)));
			//That add 10 is just a padding (this really should not be here)
			name.layoutYProperty().bind(l.startYProperty().add(l.endYProperty().subtract(l.startYProperty()).divide(2)).subtract(name.heightProperty().divide(2).add(10)));
			
			return new Group(name, l);
		}
	}
	
	private class Inspector {	
		private void clear() {
			inspector.getChildren().clear();
		}
		
		private Node label(String text) {
			return new Label(text);
		}
		
		private Node title(String text) {
			return label(text);
		}
		
		private Node name(UML_Element elem) {			
			var name = new TextField(elem.getName());
			name.setOnKeyTyped((e) -> {
				elem.putName(name.getText());
			});
			return name;
		}
		
		private Node delete(UML_Element elem) {
			return button("Delete", (e) -> {
				controller.remove(elem.getKey());
				UML_View.this.drawer.delete(elem);
				
				clear();
			});
		}
		
//		Probably could make these return types more specific haha
		private Node button(String text, EventHandler<ActionEvent> onAction) {
			var button = new Button(text);
			button.setOnAction(onAction);
			
			return button;
		}
		
		
		private void addComponent(Node n) {
			inspector.getChildren().add(n);
		}
		
		public void update(UML_ClassBox cbox) {
			clear();
			
			addComponent(title("Class Box"));
			
			addComponent(delete(cbox));
			
			addComponent(new VBox(label("Name"), 
								  name(cbox)));
			
			addComponent(label("Attributes"));
			
			addComponent(button("Add Attribute", (e) -> {
				cbox.putAttribute(new UML_Attribute());
				
				drawer.draw(cbox);
			}));
			
			addComponent(new HBox(label("Visibility"), 
								  label("Name"), 
								  label("Type")));
					
			for (var attr : cbox.getAttributes())
			{
				var choices = new ArrayList<String>();
				choices.add("+");
				choices.add("o");
				choices.add("-");
				choices.add("");
				
				var choose = new ChoiceBox<String>(FXCollections.observableArrayList(choices));
				if (attr.getVisibility() == null)
					choose.setValue("");
				else
					choose.setValue(attr.getVisibility());
				
				choose.setOnAction((e) -> {
					attr.putVisibility(choose.getValue());
				});
				
				var attrName = new TextField(attr.getName());
				attrName.setOnKeyTyped((e) -> {
					attr.putName(attrName.getText());
				});
				
				var attrType = new TextField(attr.getType());
				attrType.setOnKeyTyped((e) -> {
					attr.putType(attrType.getText());
				});
				
				addComponent(new HBox(choose, attrName, attrType));
			}
			
			addComponent(label("Operations"));
			
			addComponent(button("Add Operation", (e) -> {
				cbox.putOperation(new UML_Operation());
				
				drawer.draw(cbox);
			}));
			
			addComponent(new HBox(label("Visibility"), 
					  			  label("Signature")));
			
			for (var op : cbox.getOperations())
			{
				var choices = new ArrayList<String>();
				choices.add("+");
				choices.add("o");
				choices.add("-");
				choices.add("");
				
				var choose = new ChoiceBox<String>(FXCollections.observableArrayList(choices));
				if (op.getVisibility() == null)
					choose.setValue("");
				else
					choose.setValue(op.getVisibility());
				
				choose.setOnAction((e) -> {
					op.putVisibility(choose.getValue());
				});
				
				var opSignature = new TextField(op.getSignature());
				opSignature.setOnKeyTyped((e) -> {
					op.putSignature(opSignature.getText());
				});
				
				addComponent(new HBox(choose, opSignature));
			}
			
			
			Label extra_label = new Label("Extra");
			TextArea extra = new TextArea(cbox.getExtra());
			extra.setOnKeyTyped((e) -> {
				cbox.putExtra(extra.getText());
			});
			
			inspector.getChildren().addAll(extra_label, extra);
		}
		
		public void update(UML_Relationship dep) {
			clear();
			addComponent(delete(dep));
			addComponent(name(dep));
		}
	}
}
