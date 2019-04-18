package storming.uml_editor.model.relationships;

import org.json.JSONObject;

import storming.uml_editor.model.things.UML_Thing;

/**
 * The default constructor for a UML Dependency
 */
public class UML_Dependency extends UML_Relationship {
	/**
	 * The default constructor for a UML Dependency
	 */
	public UML_Dependency() {
		super();
	}
	
	/**
	 * Constructs the dependency with a source and target
	 * 
	 * @param source The source UML Thing
	 * @param target The target UML Thing
	 */
	public UML_Dependency(UML_Thing source, UML_Thing target) {
		super(source, target);
	}
	
	/**
	 * Constructs the dependency with a name, source, and target
	 * 
	 * @param name The name for the dependency
	 * @param source The source UML Thing
	 * @param target The target UML Thing
	 */
	public UML_Dependency(String name, UML_Thing source, UML_Thing target) {
		super(name, source, target);
	}
	
	/**
	 * Returns a JSONObject representing the UML Dependency
	 * 
	 * @return A JSONObject representing the UML Dependency
	 */
	public JSONObject toJSON() {
		var json = new JSONObject();
		json.put("type", "dependency");
		json.put("name", getName());
		json.put("source", source.getKey());
		json.put("target", target.getKey());
		return json;
	}
}
