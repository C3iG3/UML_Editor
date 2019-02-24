package storming.uml_editor.model.things;

import storming.uml_editor.model.UML_Element;

/**
 * A class that represents a UML Thing
 */
public abstract class UML_Thing extends UML_Element {
	/**
	 * The default constructor for a UML Thing
	 */
	protected UML_Thing() {
		super();
	}
	
	/**
	 * Constructs the thing with a name
	 * 
	 * @param name The name for the thing
	 */
	protected UML_Thing(String name) {
		super(name);
	}
}
