package storming.uml_editor.model;

public abstract class UML_Model_Element {
	protected String name;
	private Long key = null;
	
	protected UML_Model_Element() {
		this("");
	}
	
	protected UML_Model_Element(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public String setName(String newName) {
		String temp = name;
		name = newName;
		return temp;
	}
	
	public long getKey() {
		return key;
	}
	
	// Do NOT set an access specifier, no specifier indicates package-protected
	void setKey(long newKey) {
		key = newKey;
	}
}
