package storming.uml_editor.controller;

import storming.uml_editor.model.UML_Element;
import storming.uml_editor.model.UML_Model;
import storming.uml_editor.view.UML_View;

/**
 * A controller class for a UML editor
 */
public class UML_Controller {
	private UML_View view;
	private UML_Model model;
	
	/**
	 * Constructs a UML_Controller object
	 * 
	 * @param view The view that the controller will manage
	 */
	public UML_Controller(UML_View view) {
		this.view = view;
		model = new UML_Model(this);
	}
	
	/**
	 * Gets an element from the model. This element must be cast once retrieved
	 * 
	 * @param key The key of the object being requested
	 * @return The requested object as a UML_Element; null if the object does not exist in the model
	 */
	public UML_Element get(long key) {
		try {
			return model.get(key);
		}
		catch (NullPointerException ex) {
			return null;
		}
	}
	
	/**
	 * Gets an element from the model and applies the requested cast
	 * 
	 * @param clazz The type of element being requested
	 * @param key The key of the element being requested
	 * @return 
	 * 	The requested element as the type clazz; 
	 * 	null if the key is not in the model;
	 *	null if the requested cast fails
	 */
	public <T extends UML_Element> T get(long key, Class<T> clazz) {
		try {
			return clazz.cast(get(key));
		}
		catch (ClassCastException | NullPointerException ex) {
			return null;
		}
	}
	
	/**
	 * Puts a new element into the model
	 * 
	 * @param elem The element to add to the model
	 * @return The element added to the model
	 */
	public <T extends UML_Element> T put(T elem) {
		return model.put(elem);
	}
	
	/** 
	 * Removes an element from the model
	 *
	 * @param key The key of the object to remove
	 * @return 
	 * 	The element that was removed; 
	 * 	null if the key is not in the model
	 */
	public UML_Element remove(long key) {
		return model.remove(key);
	}
	
	/**
	 * TODO When file loading is added
	 */
	public void draw(UML_Element elem) {
		
	}
	
	/**
	 * TODO When file loading is added
	 *
	 * Load an existing UML file given a file path
	 * 
	 * @return 
	 * 	TRUE on success; 
	 * 	FALSE on failure
	 */
	public boolean load(String filepath) {
		return false;
	}
	
	/**  
	 * TODO When file loading is added
	 * 
	 * Save the current state of the model to a given file path
	 * 
	 * @return 
	 * 	TRUE on success; 
	 * 	FALSE on failure
	 */
	public boolean save(String filepath) {
		return false;
	}
}
