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
	
	/*
	 * Description: Testing newly constructed model. 
	 */
	@Test
	void testUML_Model() {
		UML_Model emptyModel = new UML_Model(null); 
		// keys start at 0
		assertTrue(emptyModel.get(0)== null, "There should be no elements in the hashmap");
		
	}

	/*
	 * Testing: put, get, size
	 * Description: The goal is to verify that when elems are added to the model, we are able to retrieve those 
	 * elements via it's respective Key. Keys (which are IDs for each elem) start at 0. 
	 */
	@Test
	void testModelAttrs() {
		UML_Model putModel = new UML_Model(null);
		
		// constructing class box elems to place into them model.
		UML_ClassBox elemOne = new UML_ClassBox("class box 1"); 
		UML_ClassBox elemTwo = new UML_ClassBox("class box 2"); 
		UML_ClassBox elemThree = new UML_ClassBox("class box 3"); 
		UML_ClassBox elemFour = new UML_ClassBox("class box 4"); 
		// Construction without name attribute 
		UML_ClassBox elemFive = new UML_ClassBox(); 
		UML_ClassBox elemSix = new UML_ClassBox(); 
		
		putModel.put(elemOne);
		// we put one element into the model so we should get back the same element
		assertTrue(putModel.get(0) == elemOne, "The first elem.");
		
		putModel.put(elemTwo);
		assertTrue(putModel.get(1) == elemTwo, "The second elem is key two.");
		// verify that there are no key collision or overwriting
		assertTrue(putModel.get(1) != elemOne && putModel.get(0) != elemTwo, "The first elem should not be obtained with the second elem's key.");
		
		// Testing return value of the put method
		var E3 = putModel.put(elemThree);
		var E4 = putModel.put(elemFour);
		
		assertTrue(E3 == elemThree, "Return value of E3 should be element three.");
		assertTrue(E4 != elemThree, "Return value of E4 should not be element three.");
		
		// Verifying elements added to the model without the name parameter do not misbehave
		putModel.put(elemFive);
		putModel.put(elemSix);
		
		assertTrue(putModel.get(5) == elemSix, "The seventh elem is obatined obtained with the 6th key.");
		assertTrue(putModel.get(4) != elemSix, "The sixth elem should not be equal to the seventh elem.");
		
		// Invalid element request
		assertTrue(putModel.get(10) == null, "There should be no element indexed at 9.");
		
		//TODO Add tests about model size
		
		
	}
	
	/*
	 * Testing: remove 
	 * Description: There will be four elements in the model. After removing an element the model should keep it's integrity
	 * with respect to the other elements. 
	 */
	@Test
	void testRemove() {
		UML_Model removeModel = new UML_Model(null);
		
		UML_ClassBox elemOne = new UML_ClassBox("class box 1"); 
		UML_ClassBox elemTwo = new UML_ClassBox("class box 2"); 
		UML_ClassBox elemThree = new UML_ClassBox("class box 3"); 
		UML_ClassBox elemFour = new UML_ClassBox("class box 4"); 
		
		removeModel.put(elemOne);
		removeModel.put(elemTwo);
		removeModel.put(elemThree);
		removeModel.put(elemFour);
		
		// removing element Three, instance of that key:value should be gone
		removeModel.remove(2);
		assertTrue(removeModel.get(2) == null, "There should be no more instance of <key:value> 2:elemThree.");
		assertTrue(removeModel.get(0) == elemOne , "Structure of other keys should remain");
		assertTrue(removeModel.get(1) == elemTwo , "Structure of other keys should remain");
		assertTrue(removeModel.get(3) == elemFour , "Structure of other keys should remain");
		
		// && removeModel.get(0) == elemOne && removeModel.get(3) == elemFour
		
		removeModel.remove(0);
		assertTrue(removeModel.get(0) == null, "Instance of <key:value> 0:elemOne sould be removed.");
		
		// removing rest of the model
		removeModel.remove(1);
		removeModel.remove(3);
		
		//TODO should be able to test the size.
		
		// adding new element check key integrity
		UML_ClassBox elemFive = new UML_ClassBox("class box 5"); 
		removeModel.put(elemFive);
		assertTrue(removeModel.get(4) == elemFive, "The next key grabbed for element five should be 4");
		
		
	}
	
	//TODO 
	/*
	 * Test: classbox constructors 
	 */
	@Test
	void testClassBoxCtor() {
		
	}
	
	/* 
	 * Testing: getAttribute (and collection version), putAttribute, removeAttribute, hasAttributes, 
	 * countAttributes, 
	 * Description: 
	 */
	@Test
	void testClassBoxAttrs() {
		UML_Model m = new UML_Model(null);
		
		UML_ClassBox elemOne = new UML_ClassBox(); 
		UML_ClassBox elemTwo = new UML_ClassBox(); 
		UML_ClassBox elemThree = new UML_ClassBox(); 
		UML_ClassBox elemFour = new UML_ClassBox(); 
		
		m.put(elemOne);
		m.put(elemTwo);
		m.put(elemThree);
		m.put(elemFour);
		
		
		
	}
	
	

}
