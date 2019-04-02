package storming.uml_editor.model;

import java.util.HashMap;

import storming.uml_editor.controller.UML_Controller;

/**
 * A model for a UML editor
 */
public class UML_Model {
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
	 * @return 
	 * 	The element if the key is in the model; 
	 * 	null otherwise
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
	 * Removes an element from the model
	 * 
	 * @param key The key of the element to remove
	 * @return 
	 * 	The removed element if the key is in the model;
	 * 	null otherwise
	 */
	public UML_Element remove(long key) {
		return elements.remove(key);
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
