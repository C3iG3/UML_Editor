package storming.uml_editor.model.things.classbox;

/**
 * A class that represents a UML Attribute
 */
public class UML_Attribute {
	protected Character visibility = null;
	protected String name = null, type = null;
	
	private Long key = null;
	
	/**
	 * The default constructor for a UML Attribute
	 */
	public UML_Attribute() {}
	
	/**
	 * Constructs the attribute with a name
	 * 
	 * @param name The name for the attribute
	 */
	public UML_Attribute(String name) {
		this(null, name, null);
	}
	
	/**
	 * Constructs the attribute with a name and type
	 * 
	 * @param name The name for the attribute
	 * @param type The type of the attribute
	 */
	public UML_Attribute(String name, String type) {
		this(null, name, type);
	}
	
	/**
	 * Constructs the attribute with a visibility, name, and type
	 * 
	 * @param visibility The visibility of the attribute
	 * @param name The name for the attribute
	 * @param type The type of the attribute
	 */
	public UML_Attribute(Character visibility, String name, String type) {
		putVisibility(visibility);
		putName(name);
		putType(type);
	}
	
	/**
	 * Gets the visibility of the attribute
	 * 
	 * @return
	 *	The visibility of the attribute, if it has one;
	 *	null otherwise
	 */
	public Character getVisibility() {
		return visibility;
	}
	
	/**
	 * Puts a visibility on the attribute
	 * 
	 * @param visibility The visibility to put on the attribute
	 * @return
	 * 	The previous visibility, if it had one;
	 * 	null otherwise
	 */
	public Character putVisibility(Character visibility) {
		var temp = this.visibility;
		this.visibility = visibility;
		return temp;
	}
	
	/**
	 * Removes the visibility of the attribute
	 * 
	 * @return
	 * 	The removed visibility, if it had one;
	 * 	null otherwise
	 */
	public Character removeVisibility() {
		return putVisibility(null);
	}
	
	/**
	 * Checks if this attribute has a visibility. This does NOT check if the
	 * 	visibility is empty, but only if it is null
	 * 
	 * @return
	 * 	TRUE if there is a visibility;
	 * 	FALSE otherwise
	 */
	public boolean hasVisibility() {
		return visibility != null;
	}
	
	/**
	 * Gets the name of the attribute
	 * 
	 * @return
	 * 	The name of the attribute, if it has one;
	 * 	null otherwise
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Puts a name on this attribute
	 * 
	 * @param name The name for the attribute
	 * @return
	 * 	The previous name of the attribute, if it had one;
	 * 	null otherwise
	 */
	public String putName(String name) {
		var temp = this.name;
		this.name = name;
		return temp;
	}
	
	/**
	 * Removes the name from this attribute
	 * 
	 * @return
	 * 	The removed name, if there was one;
	 * 	null otherwise
	 */
	public String removeName() {
		return putName(null);
	}
	
	/**
	 * Checks if this attribute has a name. This does NOT check if the
	 * 	name is empty, but only if it is null
	 * 
	 * @return
	 * 	TRUE if there is a name;
	 * 	FALSE otherwise
	 */
	public boolean hasName() {
		return name != null;
	}
	
	/**
	 * Gets the type of this attribute
	 * 
	 * @return
	 * 	The type, if there is one;
	 * 	null otherwise
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Puts a type on this attribute
	 * 
	 * @param type The type for the attribute
	 * @return
	 * 	The previous type, if there was one;
	 * 	null otherwise
	 */
	public String putType(String type) {
		var temp = this.type;
		this.type = type;
		return temp;
	}
	
	/**
	 * Removes the type from this attribute
	 * 
	 * @return
	 * 	The previous type, if there was one;
	 * 	null otherwise
	 */
	public String removeType() {
		return putType(null);
	}
	
	/**
	 * Checks if this attribute has a type. This does NOT check if the
	 * 	type is empty, but only if it is null
	 * 
	 * @return
	 * 	TRUE if there is a type;
	 * 	FALSE otherwise
	 */
	public boolean hasType() {
		return type != null;
	}
	
	/**
	 * Gets the key of this attribute. If the key is null this attribute is not owned by a class box.
	 * This key is NOT recognized by the model, it is ONLY recognized by the owning class box
	 * 
	 * @return 
	 * 	The attribute's key, if it has one;
	 * 	null otherwise
	 */
	public Long getKey() {
		return key;
	}
	
	/**
	 * Sets the key of this attribute. Should only ever be called (and be accessible) by a class box
	 * 
	 * @param newKey The key for the attribute
	 */
	void setKey(long newKey) {
		key = newKey;
	}
}
