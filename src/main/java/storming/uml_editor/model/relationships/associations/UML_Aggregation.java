package storming.uml_editor.model.relationships.associations;

import org.json.JSONObject;

import storming.uml_editor.model.things.UML_Thing;

/**
 * The default constructor for a UML Aggregation
 */
public class UML_Aggregation extends UML_Association {
	/**
	 * The default constructor for a UML Aggregation
	 */
	public UML_Aggregation() {
		super();
	}
	
	/**
	 * Constructs the aggregation with a source and target
	 * 
	 * @param source The source UML Thing
	 * @param target The target UML Thing
	 */
	public UML_Aggregation(UML_Thing source, UML_Thing target) {
		super(source, target);
	}
	
	/**
	 * Constructs the aggregation with a name, source, and target
	 * 
	 * @param name The name for the aggregation
	 * @param source The source UML Thing
	 * @param target The target UML Thing
	 */
	public UML_Aggregation(String name, UML_Thing source, UML_Thing target) {
		super(name, source, target);
	}
	
	/**
	 * Returns a JSONObject representing the UML Aggregation
	 * 
	 * @return A JSONObject representing the UML Aggregation
	 */
	public JSONObject toJSON() {
		var json = new JSONObject();
		json.put("type", "aggregation");
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
