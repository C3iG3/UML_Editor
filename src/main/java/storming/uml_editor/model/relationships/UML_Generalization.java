package storming.uml_editor.model.relationships;

import storming.uml_editor.model.things.UML_Thing;

/**
 * The default constructor for a UML Generalization
 */
public class UML_Generalization extends UML_Relationship {
	/**
	 * The default constructor for a UML Generalization
	 */
	public UML_Generalization() {
		super();
	}
	
	/**
	 * Constructs the generalization with a source and target
	 * 
	 * @param source The source UML Thing
	 * @param target The target UML Thing
	 */
	public UML_Generalization(UML_Thing source, UML_Thing target) {
		super(source, target);
	}

	/**
	 * Constructs the generalization with a name, source, and target
	 * 
	 * @param name The name for the generalization
	 * @param source The source UML Thing
	 * @param target The target UML Thing
	 */
	public UML_Generalization(String name, UML_Thing source, UML_Thing target) {
		super(name, source, target);
	}
}
