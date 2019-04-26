package storming.uml_editor.model;

import java.util.HashMap;

import org.json.JSONObject;

import storming.uml_editor.controller.UML_Controller;

/**
 * A model for a UML editor
 */
public class UML_Model {
	@SuppressWarnings("unused")
	private UML_Controller controller = null;
	
	private HashMap<Long, UML_Element> elements = new HashMap<>();

	private long currKey = 0;

	/**
	 * Constructs a UML_Model object
	 *
	 * @param controller The controller of this model
	 */
	public UML_Model(UML_Controller controller) {
		this.controller = controller;
	}

	/**
	 * Gets an element from the model given a key
	 *
	 * @param key The key for the object
	 * @return The element if the key is in the model; null otherwise
	 */
	public UML_Element get(long key) {
		return elements.get(key);
	}

	/**
	 * Puts an element into the model
	 *
	 * @param e The element to put into the model
	 * @return The element put into the model
	 */
	public <T extends UML_Element> T put(T elem) {
		elem.setKey(getNextKey());
		elements.put(elem.getKey(), elem);
		return elem;
	}
	
	/**
	 * Puts an element into the model with a specific key
	 * This can cause HUGE issues, please be careful when using this
	 *
	 * @param e The element to put into the model
	 * @param key The key to put the element at
	 * @return The element put into the model
	 */
	public <T extends UML_Element> T put(T elem, long key) {
		if (key > currKey)
			currKey = key;
		return put(elem);
	}

	/**
	 * Removes an element from the model
	 *
	 * @param key The key of the element to remove
	 * @return The removed element if the key is in the model; null otherwise
	 */
	public UML_Element remove(long key) {
		return elements.remove(key);
	}

	/**
	 * Returns a JSONObject that represents the whole model
	 * 
	 * @return A JSONObject of the model
	 */
	public JSONObject toJSON() {
		var json = new JSONObject();
		
		elements.forEach((key, val) -> {
			json.put(key.toString(), val.toJSON());
		});
		
		return json;
	}

	/**
	 * Gets the next unique key. Used to assign keys to new elements
	 *
	 * @return The next unique key
	 */
	private long getNextKey() {
		return currKey++;
	}
}
