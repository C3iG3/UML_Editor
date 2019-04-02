package storming.uml_editor.model.relationships.associations;

import storming.uml_editor.model.relationships.UML_Relationship;
import storming.uml_editor.model.things.UML_Thing;

/**
 * The default constructor for a UML Association
 */
public class UML_Association extends UML_Relationship {
	/**
	 * The default constructor for a UML Association
	 */
	public UML_Association() {
		super();
	}
	
	/**
	 * Constructs the association with a source and target
	 * 
	 * @param source The source UML Thing
	 * @param target The target UML Thing
	 */
	public UML_Association(UML_Thing source, UML_Thing target) {
		super(source, target);
	}
	
	/**
	 * Constructs the association with a name, source, and target
	 * 
	 * @param name The name for the association
	 * @param source The source UML Thing
	 * @param target The target UML Thing
	 */
	public UML_Association(String name, UML_Thing source, UML_Thing target) {
		super(name, source, target);
	}
}
