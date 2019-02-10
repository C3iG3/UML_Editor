package storming.uml_editor.model.things;

import storming.uml_editor.model.UML_Model_Element;

public abstract class UML_Model_Thing extends UML_Model_Element {
	protected UML_Model_Thing() {
		this("");
	}
	
	protected UML_Model_Thing(String name) {
		super(name);
	}
}
