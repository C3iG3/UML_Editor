package storming.uml_editor.model.relationships;

import storming.uml_editor.model.UML_Element;
import storming.uml_editor.model.things.UML_Thing;

/**
 * A class that represents a UML Relationship
 */
public abstract class UML_Relationship extends UML_Element {
	protected UML_Thing source = null;
	protected UML_Thing target = null;
	
	/**
	 * The default constructor for a UML Relationship
	 */
	protected UML_Relationship() {
		super();
	}
	
	/**
	 * Constructs the relationship with a source and target
	 * 
	 * @param source The source UML Thing
	 * @param target The target UML Thing
	 */
	protected UML_Relationship(UML_Thing source, UML_Thing target) {
		this(null, source, target);
	}
	
	/**
	 * Constructs the relationship with a name, source, and target
	 * 
	 * @param name The name for the relationship
	 * @param source The source UML Thing
	 * @param target The target UML Thing
	 */
	protected UML_Relationship(String name, UML_Thing source, UML_Thing target) {
		super(name);
		
		putSource(source);
		putTarget(target);
	}

	/**
	 * Gets the source for the relationship
	 * 
	 * @return
	 * 	The source, if there is one;
	 * 	null otherwise
	 */
	public UML_Thing getSource() {
		return source;
	}
	
	/**
	 * Puts a source for this relationship
	 * 
	 * @param source The source UML Thing
	 * @return
	 * 	The previous source if there was one;
	 * 	null otherwise
	 */
	public UML_Thing putSource(UML_Thing source) {
		var temp = this.source;
		this.source = source;
		return temp;
	}
	
	/**
	 * Removes the source for this relationship
	 * 
	 * @return
	 * 	The removed source, if there is one;
	 * 	null otherwise
	 */
	public UML_Thing removeSource() {
		return putSource(null);
	}
	
	/**
	 * Checks if this relationship has a source
	 * 
	 * @return
	 * 	TRUE if there is a source;
	 * 	FALSE otherwise
	 */
	public boolean hasSource() {
		return source != null;
	}
	
	/**
	 * Gets the target for the relationship
	 * 
	 * @return
	 * 	The target, if there is one;
	 * 	null otherwise
	 */
	public UML_Thing getTarget() {
		return target;
	}
	
	/**
	 * Puts a target for this relationship
	 * 
	 * @param target The target UML Thing
	 * @return
	 * 	The previous target if there was one;
	 * 	null otherwise
	 */
	public UML_Thing putTarget(UML_Thing target) {
		var temp = this.target;
		this.target = target;
		return temp;
	}
	
	/**
	 * Removes the target for this relationship
	 * 
	 * @return
	 * 	The removed target, if there is one;
	 * 	null otherwise
	 */
	public UML_Thing removeTarget() {
		return putTarget(null);
	}
	
	/**
	 * Checks if this relationship has a target
	 * 
	 * @return
	 * 	TRUE if there is a target;
	 * 	FALSE otherwise
	 */
	public boolean hasTarget() {
		return target != null;
	}
}
