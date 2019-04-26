package storming.uml_editor.controller;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import storming.uml_editor.model.UML_Element;
import storming.uml_editor.model.UML_Model;
import storming.uml_editor.model.relationships.UML_Dependency;
import storming.uml_editor.model.relationships.UML_Generalization;
import storming.uml_editor.model.relationships.UML_Relationship;
import storming.uml_editor.model.relationships.associations.UML_Aggregation;
import storming.uml_editor.model.relationships.associations.UML_Association;
import storming.uml_editor.model.relationships.associations.UML_Composition;
import storming.uml_editor.model.things.UML_Thing;
import storming.uml_editor.model.things.classbox.UML_ClassBox;
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
	 * @return The requested object as a UML_Element; null if the object does not
	 *         exist in the model
	 */
	public UML_Element get(long key) {
		try {
			return model.get(key);
		} catch (NullPointerException ex) {
			return null;
		}
	}

	/**
	 * Gets an element from the model and applies the requested cast
	 *
	 * @param clazz The type of element being requested
	 * @param key   The key of the element being requested
	 * @return The requested element as the type clazz; null if the key is not in
	 *         the model; null if the requested cast fails
	 */
	public <T extends UML_Element> T get(long key, Class<T> clazz) {
		try {
			return clazz.cast(get(key));
		} catch (ClassCastException | NullPointerException ex) {
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
	 * @return The element that was removed; null if the key is not in the model
	 */
	public UML_Element remove(long key) {
		return model.remove(key);
	}

	/**
	 * Draws a UML Element to the view
	 * 
	 * @param elem The UML Element to draw
	 */
	public void draw(UML_Element elem) {
		view.draw(elem);
	}

	/**
	 * Load an existing .storm file from the given file path
	 *
	 * @return 
	 * 	true on success; 
	 * 	false on failure
	 */
	@SuppressWarnings("unchecked")
	public boolean load(String path) {
		view.clear();
		
		// Clear current model
		model = new UML_Model(this);
		
		JSONObject json = null;
		try {
			json = (JSONObject) new JSONParser().parse(new FileReader(path));
		} catch (IOException | ParseException e) {
			return false;
		}
		
		/* 
		 * Class Boxes are always handled first because relationships can only be drawn
		 * if the class boxes already exist. This is inefficient since instead of a T(N)
		 * complexity it is T(2N).
		 */
		
		json.forEach((key, value) -> {
			var obj = new org.json.JSONObject(((org.json.simple.JSONObject) value).toJSONString());
			
			if (obj.getString("type").equals("classbox"))
			{
				draw(put(UML_ClassBox.fromJSON(obj)));
			}
		
			if (!obj.getString("type").equals("classbox"))
			{
				UML_Relationship rel = null;
				
				switch (obj.getString("type")) {
				case "dependency":
					rel = new UML_Dependency();
					break;
				case "generalization":
					rel = new UML_Generalization();
					break;
				case "association":
					rel = new UML_Association();
					break;
				case "aggregation":
					rel = new UML_Aggregation();
					break;
				case "composition":
					rel = new UML_Composition();
					break;
				}
				
				rel.putName(obj.optString("name"));
				rel.putSource((UML_Thing) model.get(obj.getLong("source")));
				rel.putTarget((UML_Thing) model.get(obj.getLong("target")));
				
				if (rel instanceof UML_Association)
				{
					((UML_Association) rel).putSourceMultiplictyLower(obj.optString("sourceMultiplicityLower"));
					((UML_Association) rel).putSourceMultiplictyUpper(obj.optString("sourceMultiplicityUpper"));
					((UML_Association) rel).putTargetMultiplictyLower(obj.optString("targetMultiplicityLower"));
					((UML_Association) rel).putTargetMultiplictyUpper(obj.optString("targetMultiplicityUpper"));
				}
				
				draw(put(rel));
			}
		});
		
		return true;
	}

	/**
	 * Save an existing .storm file to the given file path
	 *
	 * @return 
	 * 	true on success; 
	 * 	false on failure
	 */
	public boolean save(String path) {
		var json = model.toJSON();
		
		try {
			var writer = new FileWriter(path);
			writer.write(json.toString());
			writer.close();
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
}
