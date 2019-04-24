package storming.uml_editor.model.things.classbox;

import org.json.JSONObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * A class that represents a UML Operation
 */
public class UML_Operation {
	protected StringProperty visibility = new SimpleStringProperty(null),
							 signature  = new SimpleStringProperty(null);
	
	private Long key = null;
	
	/**
	 * The default constructor for a UML Operation
	 */
	public UML_Operation() {}
	
	/**
	 * Constructs the operation with a signature
	 * 
	 * @param signature The signature of the operation
	 */
	public UML_Operation(String signature) {
		this(null, signature);
	}
	
	/**
	 * Constructs the operation with a visibility and signature
	 * 
	 * @param visibility The visibility of the operation
	 * @param signature The signature of the operation
	 */
	public UML_Operation(String visibility, String signature) {
		putVisibility(visibility);
		putSignature(signature);
	}
	
	/**
	 * Gets the visibility of the operation
	 * 
	 * @return
	 *	The visibility of the operation, if it has one;
	 *	null otherwise
	 */
	public String getVisibility() {
		return visibility.get();
	}
	
	/**
	 * Puts a visibility on the operation
	 * 
	 * @param visibility The visibility to put on the operation
	 * @return
	 * 	The previous visibility, if it had one;
	 * 	null otherwise
	 */
	public String putVisibility(String visibility) {
		var temp = getVisibility();
		this.visibility.set(visibility);
		return temp;
	}
	
	/**
	 * Removes the visibility of the operation
	 * 
	 * @return
	 * 	The removed visibility, if there is one;
	 * 	null otherwise
	 */
	public String removeVisibility() {
		return putVisibility(null);
	}
	
	/**
	 * Checks if this operation has a visibility. This does NOT check if the
	 * 	visibility is empty, but only if it is null
	 * 
	 * @return
	 * 	TRUE if there is a visibility;
	 * 	FALSE otherwise
	 */
	public boolean hasVisibility() {
		return getVisibility() != null;
	}
	
	/**
	 * Returns the visibility as a JavaFX property
	 * @return The visibility as a JavaFX property
	 */
	public StringProperty visibilityProperty() {
		return visibility;
	}
	
	/**
	 * Gets the signature of the operation
	 * 
	 * @return
	 * 	The signature, if there is one;
	 * 	null otherwise
	 */
	public String getSignature() {
		return signature.get();
	}
	
	/**
	 * Puts a signature on this operation
	 * 
	 * @param signature The signature to put on the operation
	 * @return
	 * 	The previous operation, if there was one;
	 * 	null otherwise
	 */
	public String putSignature(String signature) {
		var temp = getSignature();
		this.signature.set(signature);
		return temp;
	}
	
	/**
	 * Removes the signature from this operation
	 * 
	 * @return
	 * 	The removed signature, if there is one;
	 * 	null otherwise
	 */
	public String removeSignature() {
		return putSignature(null);
	}
	
	/**
	 * Checks if the operation has a signature. This does NOT check if the
	 * 	signature is empty, but only if it is null
	 * 
	 * @return
	 * 	TRUE if there is a signature;
	 * 	FALSE otherwise
	 */
	public boolean hasSignature() {
		return getSignature() != null;
	}
	
	/**
	 * Returns the signature as a JavaFX property
	 * @return The signature as a JavaFX property
	 */
	public StringProperty signatureProperty() {
		return signature;
	}
	
	/**
	 * Gets the key of this operation. If the key is null this operation is not owned by a class box.
	 * This key is NOT recognized by the model, it is ONLY recognized by the owning class box
	 * 
	 * @return 
	 * 	The operation's key, if it has one;
	 * 	null otherwise
	 */
	public Long getKey() {
		return key;
	}
	
	/**
	 * Returns a JSONObject representing the UML Operation
	 * 
	 * @return A JSONObject representing the UML Operation
	 */
	public JSONObject toJSON() {
		var json = new JSONObject();
		json.put("visibility", getVisibility());
		json.put("signature", getSignature());
		return json;
	}
	
	/**
	 * Returns a UML Operation represented by the given JSON
	 * 
	 * @return A UML Operation
	 */
	public static UML_Operation fromJSON(JSONObject json)
	{
		var op = new UML_Operation();
		op.putVisibility(json.optString("visibility"));
		op.putSignature(json.optString("signature"));
		
		return op;
	}
	
	/**
	 * Sets the key of this operation. Should only ever be called (and be accessible) by a class box
	 * 
	 * @param newKey The key for the operation
	 */
	void setKey(long newKey) {
		key = newKey;
	}
}
