package storming.uml_editor.model.things.classbox;

import org.json.JSONObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * A class that represents a UML Attribute
 */
public class UML_Attribute {
	protected StringProperty visibility = new SimpleStringProperty(null),
							 name = new SimpleStringProperty(null), 
							 type = new SimpleStringProperty(null);
	
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
	public UML_Attribute(String visibility, String name, String type) {
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
	public String getVisibility() {
		return visibility.get();
	}
	
	/**
	 * Puts a visibility on the attribute
	 * 
	 * @param visibility The visibility to put on the attribute
	 * @return
	 * 	The previous visibility, if it had one;
	 * 	null otherwise
	 */
	public String putVisibility(String visibility) {
		var temp = this.visibility.get();
		this.visibility.set(visibility);
		return temp;
	}
	
	/**
	 * Removes the visibility of the attribute
	 * 
	 * @return
	 * 	The removed visibility, if it had one;
	 * 	null otherwise
	 */
	public String removeVisibility() {
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
		return getVisibility() != null;
	}
	
	/**
	 * Returns the visibility as a JavaFX property
	 * @return The visibility as a JavaFX property
	 */
	public StringProperty visibilityProperty() {
		return visibility;
	}
	
	/**
	 * Gets the name of the attribute
	 * 
	 * @return
	 * 	The name of the attribute, if it has one;
	 * 	null otherwise
	 */
	public String getName() {
		return name.get();
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
		var temp = this.name.get();
		this.name.set(name);
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
		return getName() != null;
	}
	
	/**
	 * Returns the name as a JavaFX property
	 * @return The name as a JavaFX property
	 */
	public StringProperty nameProperty() {
		return name;
	}
	
	/**
	 * Gets the type of this attribute
	 * 
	 * @return
	 * 	The type, if there is one;
	 * 	null otherwise
	 */
	public String getType() {
		return type.get();
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
		var temp = this.type.get();
		this.type.set(type);
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
		return getType() != null;
	}
	
	/**
	 * Returns the type as a JavaFX property
	 * @return The type as a JavaFX property
	 */
	public StringProperty typeProperty() {
		return type;
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
	 * Returns a JSONObject representing the UML Attribute
	 * 
	 * @return A JSONObject representing the UML Attribute
	 */
	public JSONObject toJSON() {
		var json = new JSONObject();
		json.put("name", getName());
		json.put("visibility", getVisibility());
		json.put("type", getType());
		return json;
	}
	
	/**
	 * Returns a UML Attribute represented by the given JSON
	 * 
	 * @return A UML Attribute
	 */
	public static UML_Attribute fromJSON(JSONObject json)
	{
		var attr = new UML_Attribute();
		attr.putName(json.optString("name"));
		attr.putVisibility(json.optString("visibility"));
		attr.putType(json.optString("type"));
		
		return attr;
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
