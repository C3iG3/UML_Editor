package storming.uml_editor.model.things.classbox;

import java.util.Collection;
import java.util.HashMap;

import org.json.JSONObject;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import storming.uml_editor.model.things.UML_StructuralThing;

/**
 * A class that represents a UML Class Box
 */
public class UML_ClassBox extends UML_StructuralThing {
	protected HashMap<Long, UML_Attribute> attributes = new HashMap<>();
	protected HashMap<Long, UML_Operation> operations = new HashMap<>();
	protected StringProperty extra = new SimpleStringProperty(null);
	
	private long currKey = 0;

	/**
	 * The default constructor for a UML Class Box
	 */
	public UML_ClassBox() {
		super();
	}
	
	/**
	 * Constructs the Class Box with a name
	 * 
	 * @param name The name for the Class Box
	 */
	public UML_ClassBox(String name) {
		super(name);
	}
	
	/**
	 * Constructs the Class Box with coordinates and size properties
	 * 
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @param width The width
	 * @param height The height
	 */
	public UML_ClassBox(double x, 
						double y,
						double width,
						double height) {
		super(x, y, width, height);
	}
	
	/**
	 * Constructs the Class Box with a name, coordinates, and size properties
	 * 
	 * @param name The name
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @param width The width
	 * @param height The height
	 */
	protected UML_ClassBox(String name, 
						   double x, 
						   double y,						
						   double width,
						   double height) {
		super(name, x, y, width, height);
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
	 * Gets a Collection of all this class box's attributes
	 * 
	 * @return A collection of attributes
	 */
	public Collection<UML_Attribute> getAttributes() {
		return attributes.values();
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
	 * Gets a Collection of all this class box's operations
	 * 
	 * @return A collection of operations
	 */
	public Collection<UML_Operation> getOperations() {
		return operations.values();
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
		return extra.get();
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
		var temp = this.extra.get();
		this.extra.set(extra);
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
		return getExtra() != null;
	}
	
	/**
	 * Returns the extra as a JavaFX property
	 * @return The extra as a JavaFX property
	 */
	public StringProperty extraProperty() {
		return extra;
	}
	
	/**
	 * Returns the center x-coordinate as a JavaFX property
	 * @return The center x-coordinate as a JavaFX property
	 */
	public DoubleBinding centerXProperty() {
		return xProperty().add(widthProperty().divide(2));
	}
	
	/**
	 * Returns the center y-coordinate as a JavaFX property
	 * @return The center y-coordinate as a JavaFX property
	 */
	public DoubleBinding centerYProperty() {
		return yProperty().add(heightProperty().divide(2));
	}
	
	/**
	 * Returns a JSONObject representing the UML Class Box
	 * 
	 * @return A JSONObject representing the UML Class Box
	 */
	public JSONObject toJSON() {
		var json = new JSONObject();
		json.put("type", "classbox");
		json.put("name", getName());
		json.put("x", getX());
		json.put("y", getY());
		json.put("width", getWidth());
		json.put("height", getHeight());
		
		var attrs = new JSONObject();
		attributes.forEach((key, attr) -> {
			attrs.put(key.toString(), attr.toJSON());
		});
		
		var ops = new JSONObject();
		operations.forEach((key, op) -> {
			ops.put(key.toString(), op.toJSON());
		});
		
		json.put("attributes", attrs);
		json.put("operations", ops);
		json.put("extra", getExtra());
		return json;
	}
	
	/**
	 * Returns a UML Class Box represented by the given JSON
	 * 
	 * @return A UML Class Box
	 */
	public static UML_ClassBox fromJSON(JSONObject json)
	{
		var cbox = new UML_ClassBox();
		cbox.putName(json.optString("name"));
		cbox.setX(json.getDouble("x"));
		cbox.setY(json.getDouble("y"));
		cbox.setWidth(json.getDouble("width"));
		cbox.setHeight(json.getDouble("height"));
		
		var attr_keys = json.getJSONObject("attributes").keys();
		while (attr_keys.hasNext())
			cbox.putAttribute(UML_Attribute.fromJSON(json.getJSONObject("attributes").getJSONObject(attr_keys.next())));
		
		var op_keys = json.getJSONObject("operations").keys();
		while (op_keys.hasNext())
			cbox.putOperation(UML_Operation.fromJSON(json.getJSONObject("operations").getJSONObject(op_keys.next())));
		
		cbox.putExtra(json.optString("extra"));
		
		return cbox;
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
