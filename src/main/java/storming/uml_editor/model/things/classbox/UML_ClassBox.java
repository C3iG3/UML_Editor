package storming.uml_editor.model.things.classbox;

import java.util.HashMap;

import storming.uml_editor.model.things.UML_Model_StructuralThing;

public class UML_Model_ClassBox extends UML_Model_StructuralThing {
	// TODO Should be switched to HashSets
	private HashMap<Long, UML_Model_Operation> attributes;
	private HashMap<Long, UML_Model_Attribute> operations;
	private String extra;
	private long attrKey = 0, opKey = 0;

	public UML_Model_ClassBox() {
		this("");
	}
	
	public UML_Model_ClassBox(String name) {
		super(name);
		
		this.attributes = new HashMap<>();
		this.operations = new HashMap<>();
	}

	public UML_Model_Operation getAttribute(long key) {
		return attributes.get(key);
	}
	
	public long putAttribute(UML_Model_Operation attr) {
		attr.setKey(getNextAttrKey());
		attributes.put(attr.getKey(), attr);
		return attr.getKey();
	}
	
	public UML_Model_Operation removeAttribute(long key) {
		return attributes.remove(key);
	}
	
	public UML_Model_Attribute getOperation(long key) {
		return operations.get(key);
	}
	
	public long putOperation(UML_Model_Attribute op) {
		op.setKey(getNextOpKey());
		operations.put(op.getKey(), op);
		return op.getKey();
	}
	
	public UML_Model_Attribute removeOperation(long key) {
		return operations.remove(key);
	}
	
	public String getExtra() {
		return extra;
	}
	
	public void putExtra(String extra) {
		this.extra = extra;
	}
	
	public String removeExtra() {
		String temp = extra;
		extra = "";
		return temp;
	}
	
	private long getNextAttrKey() {
		return attrKey++;
	}
	
	private long getNextOpKey() {
		return opKey++;
	}
}
