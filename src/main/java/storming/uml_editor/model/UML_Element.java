package storming.uml_editor.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The most general UML element that is to be inherited by all other UML element types
 */
public abstract class UML_Element {
	protected StringProperty name = new SimpleStringProperty(null);
	private Long key = null;
	
	/**
	 * The default constructor for a UML element
	 */
	protected UML_Element() {}
	
	/**
	 * Constructs the element with a name
	 * 
	 * @param name The name for the element
	 */
	protected UML_Element(String name) {
		putName(name);
	}
	
	/**
	 * Gets the name of the element
	 * 
	 * @return
	 * 	The name of the element, if it has one;
	 * 	null otherwise
	 */
	public String getName() {
		return name.get();
	}
	
	/**
	 * Puts a name on this element
	 * 
	 * @param name The name for the element
	 * @return
	 * 	The previous name of the element, if it had one;
	 * 	null otherwise
	 */
	public String putName(String name) {
		var temp = this.name.get();
		this.name.set(name);
		return temp;
	}
	
	/**
	 * Removes the name from this element
	 * 
	 * @return
	 * 	The removed name, if there was one;
	 * 	null otherwise
	 */
	public String removeName() {
		return putName(null);
	}
	
	/**
	 * Checks if this element has a name. This does NOT check if the
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
	 * Gets an element's key. If the key is null this element is not owned by a model
	 * 
	 * @return 
	 * 	The element's key, if it has one;
	 * 	null otherwise
	 */
	public Long getKey() {
		return key;
	}
	
	/**
	 * Sets the key of this element. Should only ever be called (and be accessible) by a model
	 * 
	 * @param newKey The key for the element
	 */
	void setKey(long newKey) {
		key = newKey;
	}
}
