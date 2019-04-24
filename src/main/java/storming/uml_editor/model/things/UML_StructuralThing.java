package storming.uml_editor.model.things;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;

/**
 * A class that represents a UML Structural Thing
 */
public abstract class UML_StructuralThing extends UML_Thing {
	/**
	 * The default constructor for a UML Structural Thing
	 */
	protected UML_StructuralThing() {
		super();
	}
	
	/**
	 * Constructs the Structural Thing with a name
	 * 
	 * @param name The name for the structural thing
	 */
	protected UML_StructuralThing(String name) {
		super(name);
	}
	
	/**
	 * Constructs the Structural Thing with coordinates and size properties
	 * 
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @param width The width
	 * @param height The height
	 */
	protected UML_StructuralThing(double x, 
								  double y,
								  double width,
								  double height) {
		super(x, y, width, height);
	}
	
	/**
	 * Constructs the Structural Thing with a name, coordinates, and size properties
	 * 
	 * @param name The name
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @param width The width
	 * @param height The height
	 */
	protected UML_StructuralThing(String name, 
						double x, 
						double y,
						double width,
						double height) {
		super(name, x, y, width, height);
	}
	
	/**
	 * Returns the center x-coordinate as a JavaFX property
	 * @return The center x-coordinate as a JavaFX property
	 */
	public abstract DoubleBinding centerXProperty();
	
	/**
	 * Returns the center y-coordinate as a JavaFX property
	 * @return The center y-coordinate as a JavaFX property
	 */
	public abstract DoubleBinding centerYProperty();
}
