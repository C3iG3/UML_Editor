package storming.uml_editor.model.things;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import storming.uml_editor.model.UML_Element;

public abstract class UML_Thing extends UML_Element {
	protected DoubleProperty x = new SimpleDoubleProperty(0.0), 
			 				 y = new SimpleDoubleProperty(0.0);
	protected DoubleProperty width  = new SimpleDoubleProperty(0.0), 
							 height = new SimpleDoubleProperty(0.0);

	/**
	 * The default constructor for a UML Thing
	 */
	protected UML_Thing() {
		super();
	}

	/**
	 * Constructs the Thing with a name
	 *
	 * @param name The name
	 */
	protected UML_Thing(String name) {
		super(name);
	}
	
	/**
	 * Constructs the Thing with coordinates and size properties
	 * 
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @param width The width
	 * @param height The height
	 */
	protected UML_Thing(double x, 
						double y,
						double width,
						double height) {
		super();
		
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
	}
	
	/**
	 * Constructs the Thing with a name, coordinates, and size properties
	 * 
	 * @param name The name
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @param width The width
	 * @param height The height
	 */
	protected UML_Thing(String name, 
						double x, 
						double y,
						double width,
						double height) {
		super(name);
		
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
	}
	
	/**
	 * Gets the x-coordinate of a Thing
	 * 
	 * @return 
	 * 	The x-coordinate;
	 * 	if there was no x-coordinate set, then 0.0
	 */
	public double getX() {
		return x.get();
	}
	
	/**
	 * Sets the x-coordinate of a Thing
	 * 
	 * @param newX The new x-coordinate
	 * @return 
	 * 	The old x-coordinate;
	 * 	if there was no previous x-coordinate, then 0.0
	 */
	public double setX(double newX) {
		var temp = x.get();
		x.set(newX);
		return temp;
	}
	
	/**
	 * Returns the x-coordinate as a JavaFX property
	 * @return The x-coordinate as a JavaFX property
	 */
	public DoubleProperty xProperty() {
		return x;
	}
	
	public abstract DoubleBinding centerXProperty();

	/**
	 * Gets the y-coordinate of a Thing
	 * 
	 * @return 
	 * 	The y-coordinate;
	 * 	if there was no y-coordinate set, then 0.0
	 */
	public double getY() {
		return y.get();
	}
	
	/**
	 * Sets the y-coordinate of a Thing
	 * 
	 * @param newY The new y-coordinate
	 * @return 
	 * 	The old y-coordinate;
	 * 	if there was no previous y-coordinate, then 0.0
	 */
	public double setY(double newY) {
		var temp = y.get();
		y.set(newY);
		return temp;
	}
	
	/**
	 * Returns the y-coordinate as a JavaFX property
	 * @return The y-coordinate as a JavaFX property
	 */
	public DoubleProperty yProperty() {
		return y;
	}
	
	public abstract DoubleBinding centerYProperty();
	
	/**
	 * Gets the width of a Thing
	 * 
	 * @return
	 * 	The width;
	 * 	if there was no width set, then 0.0
	 */
	public double getWidth() {
		return width.get();
	}
	
	/**
	 * Sets the width of a Thing
	 * 
	 * @param newWidth The new width
	 * @return 
	 * 	The old width;
	 * 	if there was no previous width, then 0.0
	 */
	public double setWidth(double newWidth) {
		var temp = width.get();
		width.set(newWidth);
		return temp;
	}
	
	/**
	 * Returns the width as a JavaFX property
	 * @return The width as a JavaFX property
	 */
	public DoubleProperty widthProperty() {
		return width;
	}
	
	/**
	 * Gets the height of a Thing
	 * 
	 * @return
	 * 	The height;
	 * 	if there was no height set, then 0.0
	 */
	public double getHeight() {
		return height.get();
	}
	
	/**
	 * Sets the height of a Thing
	 * 
	 * @param newHeight The new height
	 * @return 
	 * 	The old height;
	 * 	if there was no previous height, then 0.0
	 */
	public double setHeight(double newHeight) {
		var temp = height.get();
		height.set(newHeight);
		return temp;
	}
	
	/**
	 * Returns the height as a JavaFX property
	 * @return The height as a JavaFX property
	 */
	public DoubleProperty heightProperty() {
		return height;
	}
}