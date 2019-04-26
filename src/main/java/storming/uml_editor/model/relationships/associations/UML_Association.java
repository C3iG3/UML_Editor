package storming.uml_editor.model.relationships.associations;

import org.json.JSONObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import storming.uml_editor.model.relationships.UML_Relationship;
import storming.uml_editor.model.things.UML_Thing;

/**
 * The default constructor for a UML Association
 */
public class UML_Association extends UML_Relationship {
	protected StringProperty sourceMultiplicityLower = new SimpleStringProperty("*");
	protected StringProperty sourceMultiplicityUpper = new SimpleStringProperty("*");
	protected StringProperty targetMultiplicityLower = new SimpleStringProperty("*");
	protected StringProperty targetMultiplicityUpper = new SimpleStringProperty("*");
	
	/**
	 * The default constructor for a UML Association
	 */
	public UML_Association() {
		super();
	}
	
	/**
	 * Constructs the association with a source and target
	 * 
	 * @param source The source UML Thing
	 * @param target The target UML Thing
	 */
	public UML_Association(UML_Thing source, UML_Thing target) {
		super(source, target);
	}
	
	/**
	 * Constructs the association with a name, source, and target
	 * 
	 * @param name The name for the association
	 * @param source The source UML Thing
	 * @param target The target UML Thing
	 */
	public UML_Association(String name, UML_Thing source, UML_Thing target) {
		super(name, source, target);
	}
	
	/**
	 * Gets the lower bound of the source's multiplicity of the element
	 * 
	 * @return
	 * 	The lower bound of the source's multiplicity of the element, if it has one;
	 * 	"*" otherwise
	 */
	public String getSourceMultiplictyLower() {
		return sourceMultiplicityLower.get();
	}
	
	/**
	 * Puts a lower bound of the source's multiplicity on this element
	 * 
	 * @param name The lower bound of the source's multiplicity for the element
	 * @return
	 * 	The previous lower bound of the source's multiplicity of the element, if it had one;
	 * 	"*" otherwise
	 */
	public String putSourceMultiplictyLower(String lower) {
		var temp = this.sourceMultiplicityLower.get();
		this.sourceMultiplicityLower.set(lower);
		return temp;
	}
	
	/**
	 * Removes the lower bound of the source's multiplicity from this element and
	 * sets it back to "*"
	 * 
	 * @return
	 * 	The removed lower bound of the source's multiplicity, if there was one;
	 * 	"*" otherwise
	 */
	public String removeSourceMultiplictyLower() {
		return putSourceMultiplictyLower("*");
	}
	
	/**
	 * Returns the lower bound of the source's multiplicity as a JavaFX property
	 * @return The lower bound of the source's multiplicity as a JavaFX property
	 */
	public StringProperty sourceMultiplictyLowerProperty() {
		return sourceMultiplicityLower;
	}
	
	/**
	 * Gets the upper bound of the source's multiplicity of the element
	 * 
	 * @return
	 * 	The upper bound of the source's multiplicity of the element, if it has one;
	 * 	"*" otherwise
	 */
	public String getSourceMultiplictyUpper() {
		return sourceMultiplicityUpper.get();
	}
	
	/**
	 * Puts a upper bound of the source's multiplicity on this element
	 * 
	 * @param name The upper bound of the source's multiplicity for the element
	 * @return
	 * 	The previous upper bound of the source's multiplicity of the element, if it had one;
	 * 	"*" otherwise
	 */
	public String putSourceMultiplictyUpper(String upper) {
		var temp = this.sourceMultiplicityUpper.get();
		this.sourceMultiplicityUpper.set(upper);
		return temp;
	}
	
	/**
	 * Removes the upper bound of the source's multiplicity from this element and
	 * sets it back to "*"
	 * 
	 * @return
	 * 	The removed upper bound of the source's multiplicity, if there was one;
	 * 	"*" otherwise
	 */
	public String removeSourceMultiplictyUpper() {
		return putSourceMultiplictyUpper("*");
	}
	
	/**
	 * Returns the upper bound of the source's multiplicity as a JavaFX property
	 * @return The upper bound of the source's multiplicity as a JavaFX property
	 */
	public StringProperty sourceMultiplictyUpperProperty() {
		return sourceMultiplicityUpper;
	}
	
	/**
	 * Gets the lower bound of the target's multiplicity of the element
	 * 
	 * @return
	 * 	The lower bound of the target's multiplicity of the element, if it has one;
	 * 	"*" otherwise
	 */
	public String getTargetMultiplictyLower() {
		return targetMultiplicityLower.get();
	}
	
	/**
	 * Puts a lower bound of the target's multiplicity on this element
	 * 
	 * @param name The lower bound of the target's multiplicity for the element
	 * @return
	 * 	The previous lower bound of the target's multiplicity of the element, if it had one;
	 * 	"*" otherwise
	 */
	public String putTargetMultiplictyLower(String lower) {
		var temp = this.targetMultiplicityLower.get();
		this.targetMultiplicityLower.set(lower);
		return temp;
	}
	
	/**
	 * Removes the lower bound of the target's multiplicity from this element and
	 * sets it back to "*"
	 * 
	 * @return
	 * 	The removed lower bound of the target's multiplicity, if there was one;
	 * 	"*" otherwise
	 */
	public String removeTargetMultiplictyLower() {
		return putTargetMultiplictyLower("*");
	}
	
	/**
	 * Returns the lower bound of the target's multiplicity as a JavaFX property
	 * @return The lower bound of the target's multiplicity as a JavaFX property
	 */
	public StringProperty targetMultiplictyLowerProperty() {
		return targetMultiplicityLower;
	}
	
	/**
	 * Gets the upper bound of the target's multiplicity of the element
	 * 
	 * @return
	 * 	The upper bound of the target's multiplicity of the element, if it has one;
	 * 	"*" otherwise
	 */
	public String getTargetMultiplictyUpper() {
		return targetMultiplicityUpper.get();
	}
	
	/**
	 * Puts a upper bound of the target's multiplicity on this element
	 * 
	 * @param name The upper bound of the target's multiplicity for the element
	 * @return
	 * 	The previous upper bound of the target's multiplicity of the element, if it had one;
	 * 	"*" otherwise
	 */
	public String putTargetMultiplictyUpper(String upper) {
		var temp = this.targetMultiplicityUpper.get();
		this.targetMultiplicityUpper.set(upper);
		return temp;
	}
	
	/**
	 * Removes the upper bound of the target's multiplicity from this element and
	 * sets it back to "*"
	 * 
	 * @return
	 * 	The removed upper bound of the target's multiplicity, if there was one;
	 * 	"*" otherwise
	 */
	public String removetargetMultiplictyUpper() {
		return putTargetMultiplictyUpper("*");
	}
	
	/**
	 * Returns the upper bound of the target's multiplicity as a JavaFX property
	 * @return The upper bound of the target's multiplicity as a JavaFX property
	 */
	public StringProperty targetMultiplictyUpperProperty() {
		return targetMultiplicityUpper;
	}
	
	/**
	 * Returns a JSONObject representing the UML Association
	 * 
	 * @return A JSONObject representing the UML Association
	 */
	public JSONObject toJSON() {
		var json = new JSONObject();
		json.put("type", "association");
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
