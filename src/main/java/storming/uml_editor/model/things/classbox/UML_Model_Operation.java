package storming.uml_editor.model.things.classbox;

public class UML_Model_Operation {
	private char visibility;
	private String signature;
	private Long key = null;
	
	public UML_Model_Operation() {
		this(' ', "");
	}
	
	public UML_Model_Operation(char visibility) {
		this(visibility, "");
	}
	
	public UML_Model_Operation(String signature) {
		this(' ', signature);
	}
	
	public UML_Model_Operation(char visibility, String signature) {
		this.visibility = visibility;
		this.signature = signature;
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
	
	public String getSignature() {
		return signature;
	}
	
	public void putSignature(String signature) {
		this.signature = signature;
	}
	
	public String removeSignature() {
		var temp = signature;
		signature = "";
		return temp;
	}
	
	public long getKey() {
		return key;
	}
	
	void setKey(long newKey) {
		key = newKey;
	}
}
