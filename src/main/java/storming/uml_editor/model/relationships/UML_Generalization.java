package storming.uml_editor.model.relationships;

import org.json.JSONObject;

import storming.uml_editor.model.things.UML_Thing;
import storming.uml_editor.model.things.classbox.UML_Operation;

/**
 * The default constructor for a UML Generalization
 */
public class UML_Generalization extends UML_Relationship {
	/**
	 * The default constructor for a UML Generalization
	 */
	public UML_Generalization() {
		super();
	}
	
	/**
	 * Constructs the generalization with a source and target
	 * 
	 * @param source The source UML Thing
	 * @param target The target UML Thing
	 */
	public UML_Generalization(UML_Thing source, UML_Thing target) {
		super(source, target);
	}

	/**
	 * Constructs the generalization with a name, source, and target
	 * 
	 * @param name The name for the generalization
	 * @param source The source UML Thing
	 * @param target The target UML Thing
	 */
	public UML_Generalization(String name, UML_Thing source, UML_Thing target) {
		super(name, source, target);
	}
	
	/**
	 * Returns a JSONObject representing the UML Generalization
	 * 
	 * @return A JSONObject representing the UML Generalization
	 */
	public JSONObject toJSON() {
		var json = new JSONObject();
		json.put("type", "generalization");
		json.put("name", getName());
		json.put("source", source.getKey());
		json.put("target", target.getKey());
		return json;
	}
}
