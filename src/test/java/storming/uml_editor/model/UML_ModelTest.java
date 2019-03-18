/*
 * NOTE: Added to the build path so there could be some issues. 
 * 
 * 
 * 
 */


package storming.uml_editor.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import storming.uml_editor.model.things.classbox.UML_ClassBox;

class UML_ModelTest {
	
	/**
	 * Description: Testing newly constructed model. 
	 */
	@Test
	void testUML_Model() {
		UML_Model emptyModel = new UML_Model(null); 
		// keys start at 0
		assertTrue(emptyModel.get(0)== null, "There should be no elements in the hashmap");
		
	}

	/**
	 * Testing: put, get, size
	 * 
	 */
	@Test
	void testModelAttrs() {
		UML_Model putModel = new UML_Model(null);
		
		// constructing class box elems to place into them model.
		UML_ClassBox elemOne = new UML_ClassBox("class box 1"); 
		UML_ClassBox elemTwo = new UML_ClassBox("class box 2"); 
		UML_ClassBox elemThree = new UML_ClassBox("class box 3"); 
		UML_ClassBox elemFour = new UML_ClassBox("class box 4"); 
		UML_ClassBox elemFive= new UML_ClassBox("class box 5"); 
		// Construction without name attribute 
		UML_ClassBox elemSix = new UML_ClassBox(); 
		UML_ClassBox elemSeven = new UML_ClassBox(); 
		
		putModel.put(elemOne);
		// we put one element into the model so we should get back the same element
		assertTrue(putModel.get(0) == elemOne, "The first elem");
		
		putModel.put(elemTwo);
		assertTrue(putModel.get(1) == elemTwo, "The second elem is key two");
		// verify that there are no key collision or overwriting
		assertTrue(putModel.get(1) != elemOne && putModel.get(0) != elemTwo, "The first elem should not be obtained with the second elem's key");
		
		var E3 = putModel.put(elemThree);
		var E4 = putModel.put(elemFour);
		var E5 = putModel.put(elemFive);
		var E6 = putModel.put(elemSix);
		var E7 = putModel.put(elemSeven);
		
		assertTrue(putModel.get(6) == elemSeven, "The seventh elem is obatined obtained with the 6th key");
		assertTrue(putModel.get(5) != elemSeven, "The sixth elem should not be equal to the seventh elem");
		
		//TODO 
		
	}
	


	@Test
	void testRemove() {
		UML_Model removeModel = new UML_Model(null);
	}
	
	@Test
	void testClassBoxAttrs() {
		
	}
	
	

}
