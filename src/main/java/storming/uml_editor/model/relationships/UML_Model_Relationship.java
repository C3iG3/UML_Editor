package storming.uml_editor.model.relationships;

import storming.uml_editor.model.UML_Model_Element;
import storming.uml_editor.model.things.UML_Model_Thing;

public abstract class UML_Model_Relationship extends UML_Model_Element {
	protected UML_Model_Thing source;
	protected UML_Model_Thing target;
	
	protected UML_Model_Relationship(UML_Model_Thing source, UML_Model_Thing target) {
		this("", source, target);
	}
	
	protected UML_Model_Relationship(String name, UML_Model_Thing source, UML_Model_Thing target) {
		super(name);
		
		this.source = source;
		this.target = target;
	}

	public UML_Model_Thing getSource() {
		return source;
	}
	
	public UML_Model_Thing setSource(UML_Model_Thing newSource) {
		var temp = source;
		source = newSource;
		return temp;
	}
	
	public UML_Model_Thing getTarget() {
		return target;
	}
	
	public UML_Model_Thing setTarget(UML_Model_Thing newTarget) {
		var temp = target;
		target = newTarget;
		return temp;
	}
}
