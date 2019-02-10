package storming.uml_editor.model.things.classbox;

public class UML_Model_Attribute {
	private char visibility;
	private String name, type;
	private Long key = null;
	
	public UML_Model_Attribute() {
		this(' ', "", "");
	}
	
	public UML_Model_Attribute(char visibility) {
		this(visibility, "", "");
	}
	
	public UML_Model_Attribute(String name) {
		this(' ', name, "");
	}
	
	public UML_Model_Attribute(String name, String type) {
		this(' ', name, type);
	}
	
	public UML_Model_Attribute(char visibility, String name, String type) {
		this.visibility = visibility;
		this.name = name;
		this.type = type;
	}
	
	public char getVisibility() {
		return visibility;
	}
	
	public void putVisibility(char visibility) {
		this.visibility = visibility;
	}
	
	public char removeVisibility() {
		var temp = visibility;
		visibility = ' ';
		return temp;
	}
	
	public String getName() {
		return name;
	}
	
	public void putName(String name) {
		this.name = name;
	}
	
	public String removeName() {
		var temp = name;
		name = "";
		return temp;
	}
	
	public String getType() {
		return type;
	}
	
	public void putType(String type) {
		this.type = type;
	}
	
	public String removeType() {
		var temp = type;
		type = "";
		return temp;
	}
	
	public long getKey() {
		return key;
	}
	
	void setKey(long newKey) {
		key = newKey;
	}
}
