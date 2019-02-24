package storming.uml_editor.model.things.classbox;

import java.util.HashMap;

import storming.uml_editor.model.things.UML_StructuralThing;

/**
 * A class that represents a UML Class Box
 */
public class UML_ClassBox extends UML_StructuralThing {
	protected HashMap<Long, UML_Attribute> attributes = new HashMap<>();
	protected HashMap<Long, UML_Operation> operations = new HashMap<>();
	protected String extra = null;
	
	private long currKey = 0;

	/**
	 * The default constructor for a UML Class Box
	 */
	public UML_ClassBox() {
		super();
	}
	
	/**
	 * Constructs the class box with a name
	 * 
	 * @param name The name for the class box
	 */
	public UML_ClassBox(String name) {
		super(name);
	}

	/**
	 * Gets an attribute from this class box by key
	 * 
	 * @param key The key of the attribute
	 * @return
	 * 	The attribute if the key exists for this class box;
	 * 	null otherwise
	 */
	public UML_Attribute getAttribute(long key) {
		return attributes.get(key);
	}
	
	/**
	 * Puts an attribute into this class box
	 * 
	 * @param attr The attribute to add
	 * @return The attribute added
	 */
	public UML_Attribute putAttribute(UML_Attribute attr) {
		attr.setKey(getNextKey());
		attributes.put(attr.getKey(), attr);
		return attr;
	}
	
	/**
	 * Removes an attribute from this class box
	 * 
	 * @param key The key of the attribute
	 * @return
	 * 	The attribute removed if the key exists for this class box;
	 * 	null otherwise
	 */
	public UML_Attribute removeAttribute(long key) {
		return attributes.remove(key);
	}
	
	/**
	 * Checks if this class box has any attributes
	 * 
	 * @return
	 * 	TRUE if the class box has attributes;
	 * 	FALSE otherwise
	 */
	public boolean hasAttributes() {
		return countAttributes() != 0;
	}
	
	/**
	 * Returns how many attributes this class box has
	 * 
	 * @return The number of attributes this class box has
	 */
	public long countAttributes() {
		return attributes.size();
	}
	
	/**
	 * Gets an operation from this class box by key
	 * 
	 * @param key The key of the operation
	 * @return
	 * 	The operation if the key exists for this class box;
	 * 	null otherwise
	 */
	public UML_Operation getOperation(long key) {
		return operations.get(key);
	}
	
	/**
	 * Puts an operation into this class box
	 * 
	 * @param op The operation to add
	 * @return The operation added
	 */
	public UML_Operation putOperation(UML_Operation op) {
		op.setKey(getNextKey());
		operations.put(op.getKey(), op);
		return op;
	}
	
	/**
	 * Removes an operation from this class box
	 * 
	 * @param key The key of the operation
	 * @return
	 * 	The operation removed if the key exists for this class box;
	 * 	null otherwise
	 */
	public UML_Operation removeOperation(long key) {
		return operations.remove(key);
	}
	
	/**
	 * Checks if this class box has any operations
	 * 
	 * @return
	 * 	TRUE if the class box has operations;
	 * 	FALSE otherwise
	 */
	public boolean hasOperations() {
		return countOperations() != 0;
	}
	
	/**
	 * Returns how many operations this class box has
	 * 
	 * @return The number of operations this class box has
	 */
	public long countOperations() {
		return operations.size();
	}
	
	/**
	 * Gets the extra data for this class box
	 * 
	 * @return
	 * 	The extra data, if there is any;
	 * 	null otherwise
	 */
	public String getExtra() {
		return extra;
	}
	
	/**
	 * Puts extra data for this class box. This will overwrite previous extra data
	 * 
	 * @param extra The data to add
	 * @return
	 * 	The data replaced, if there was any;
	 * 	null otherwise
	 */
	public String putExtra(String extra) {
		var temp = this.extra;
		this.extra = extra;
		return temp;
	}
	
	/**
	 * Removes extra data for this class box
	 * 
	 * @return
	 * 	The data removed, if there was any;
	 * 	null otherwise
	 */
	public String removeExtra() {
		return putExtra(null);
	}	
	
	/**
	 * Checks if this class box has extra data. This does NOT check if the
	 * 	extra data is empty, but only if it is null
	 * 
	 * @return
	 * 	TRUE if the class box has extra data;
	 * 	FALSE otherwise
	 */
	public boolean hasExtra() {
		return this.extra != null;
	}
	
	/**
	 * Gets the next unique key. Used to assign keys to new attributes and operations.

	 * @return The next unique key
	 */
	/*
	 * Attributes and operations will not have overlapping keys. However, this is not
	 * 	necessary but is easier to implement
	 */
	private long getNextKey() {
		return currKey++;
	}
}
