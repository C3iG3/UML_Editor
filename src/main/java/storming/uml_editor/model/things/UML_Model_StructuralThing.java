package storming.uml_editor.model.things;

public abstract class UML_Model_StructuralThing extends UML_Model_Thing {
	protected UML_Model_StructuralThing() {
		this("");
	}
	
	protected UML_Model_StructuralThing(String name) {
		super(name);
	}
}
