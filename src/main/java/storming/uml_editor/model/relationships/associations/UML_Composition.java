package storming.uml_editor.model.relationships;

import storming.uml_editor.model.things.UML_Model_Thing;

public class UML_Model_Composition extends UML_Model_Association {
	public UML_Model_Composition(UML_Model_Thing source, UML_Model_Thing target) {
		this("", source, target);
	}
	
	public UML_Model_Composition(String name, UML_Model_Thing source, UML_Model_Thing target) {
		super(name, source, target);
		// TODO Auto-generated constructor stub
	}

}
