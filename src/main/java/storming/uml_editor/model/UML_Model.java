package storming.uml_editor.model;

import java.util.HashMap;

/*
 * TODO HashMaps need to be changed to HashSets in this class and the attribute and operation classes
 * TODO testing
 */

import storming.uml_editor.controller.UML_Controller;

public class UML_Model {
	private UML_Controller controller;
	
	// TODO should be using a HashSet since the elements contain the id now...
	private HashMap<Long, UML_Model_Element> elements;
	
	private long currKey = 0;
	
	public UML_Model(UML_Controller controller) {
		this.controller = controller;
		this.elements = new HashMap<>();
	}
	
	public UML_Model_Element get(long key) {
		return elements.get(key);
	}
	
	public long put(UML_Model_Element e) {
		e.setKey(getNextKey());
		elements.put(e.getKey(), e);
		return e.getKey();
	}
	
	public UML_Model_Element remove(long key) {
		return elements.remove(key);
	}

	private long getNextKey() {
		return currKey++;
	}
}
