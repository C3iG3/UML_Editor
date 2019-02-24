package storming.uml_editor.model.things;

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
	 * Constructs the structural thing with a name
	 * 
	 * @param name The name for the structural thing
	 */
	protected UML_StructuralThing(String name) {
		super(name);
	}
}
