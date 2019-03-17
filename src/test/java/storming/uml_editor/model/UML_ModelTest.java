/*
 * NOTE: Added to the build path so there could be some issues. 
 * 
 * 
 * 
 */


package storming.uml_editor.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UML_ModelTest {
	
	/**
	 * Description: Testing newly constructed model. 
	 */
	@Test
	void testUML_Model() {
		UML_Model emptyModel = new UML_Model(null); 
		// 0 is the instance of the first (key:value)
		assertTrue(emptyModel.get(0)== null, "There should be no elements in the hashmap");
		
	}

	
	@Test
	void testPut() {
		UML_Model putModel = new UML_Model(null);
	}
	
	@Test
	void testGet() {
		UML_Model getModel = new UML_Model(null);
	}


	@Test
	void testRemove() {
		UML_Model removeModel = new UML_Model(null);
	}

}
