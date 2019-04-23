package storming.uml_editor.model.relationships.associations;

import org.json.JSONObject;

import storming.uml_editor.model.things.UML_Thing;

/**
 * The default constructor for a UML Composition
 */
public class UML_Composition extends UML_Association {
	/**
	 * The default constructor for a UML Composition
	 */
	public UML_Composition() {
		super();
	}
	
	/**
	 * Constructs the composition with a source and target
	 * 
	 * @param source The source UML Thing
	 * @param target The target UML Thing
	 */
	public UML_Composition(UML_Thing source, UML_Thing target) {
		super(source, target);
	}
	
	/**
	 * Constructs the composition with a name, source, and target
	 * 
	 * @param name The name for the composition
	 * @param source The source UML Thing
	 * @param target The target UML Thing
	 */
	public UML_Composition(String name, UML_Thing source, UML_Thing target) {
		super(name, source, target);
	}

	/**
	 * Returns a JSONObject representing the UML Composition
	 * 
	 * @return A JSONObject representing the UML Composition
	 */
	public JSONObject toJSON() {
		var json = new JSONObject();
		json.put("type", "composition");
		json.put("name", getName());
		json.put("source", source.getKey());
		json.put("target", target.getKey());
		json.put("sourceMultiplicityLower", getSourceMultiplictyLower());
		json.put("sourceMultiplicityUpper", getSourceMultiplictyUpper());
		json.put("targetMultiplicityLower", getTargetMultiplictyLower());
		json.put("targetMultiplicityUpper", getTargetMultiplictyUpper());
		return json;
	}
}
