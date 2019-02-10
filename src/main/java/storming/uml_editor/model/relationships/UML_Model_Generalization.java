package storming.uml_editor.model.relationships;

import storming.uml_editor.model.things.UML_Model_Thing;

public class UML_Model_Generalization extends UML_Model_Relationship {
	public UML_Model_Generalization(UML_Model_Thing source, UML_Model_Thing target) {
		this("", source, target);
		// TODO Auto-generated constructor stub
	}

	public UML_Model_Generalization(String name, UML_Model_Thing source, UML_Model_Thing target) {
		super(name, source, target);
		// TODO Auto-generated constructor stub
	}

}
