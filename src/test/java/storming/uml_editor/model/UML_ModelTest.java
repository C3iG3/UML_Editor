/*
 * NOTE: This file was added to the build path.
 * 
 * These tests are designed to test the different components of the model
 * as well as how the model interacts with the controller.
 * 
 */


package storming.uml_editor.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import storming.uml_editor.controller.UML_Controller;
import storming.uml_editor.model.relationships.UML_Dependency;
import storming.uml_editor.model.relationships.UML_Generalization;
import storming.uml_editor.model.relationships.associations.UML_Association;
import storming.uml_editor.model.things.classbox.UML_Attribute;
import storming.uml_editor.model.things.classbox.UML_ClassBox;
import storming.uml_editor.model.things.classbox.UML_Operation;

public class UML_ModelTest {
	
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
	 * Description: The goal is to verify that when elements are added to the model, we are able to retrieve those 
	 * elements via respective Key. Keys (which are IDs for each elements) start at 0. 
	 */
	@Test
	void testModelAttrs() {
		UML_Model putModel = new UML_Model(null);
		
		// class boxes to be put into the model 
		UML_ClassBox elemOne = new UML_ClassBox("class box 1"); 
		UML_ClassBox elemTwo = new UML_ClassBox("class box 2"); 
		UML_ClassBox elemThree = new UML_ClassBox("class box 3"); 
		UML_ClassBox elemFour = new UML_ClassBox("class box 4");
		
		// testing one element 
		putModel.put(elemOne);
		assertTrue(putModel.get(0) == elemOne, "The first element.");
		
		// testing collisions and overwrites with two elements
		putModel.put(elemTwo);
		assertTrue(putModel.get(1) == elemTwo, "The second elem is key two.");
		assertTrue(putModel.get(1) != elemOne && putModel.get(0) != elemTwo, "The first elem should not be obtained with the second elem's key.");
		
		// Testing return value of the put method
		var E3 = putModel.put(elemThree);
		var E4 = putModel.put(elemFour);
		assertTrue(E3 == elemThree, "Return value of E3 should be element three.");
		assertTrue(E4 != elemThree, "Return value of E4 should not be element three.");
		
		// Invalid element request
		assertTrue(putModel.get(10) == null, "There should be no element indexed at 9.");
		
		//TODO Add tests about model size	
	}
	
	/**
	 * Testing: remove
	 * Description: Utilizing class boxes 
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
		
		// testing middle edge case - integrity of model should remain with respect to Key:Values/.
		removeModel.remove(2);
		assertTrue(removeModel.get(2) == null, "There should be no more instance of <key:value> 2:elemThree.");
		assertTrue(removeModel.get(0) == elemOne , "Structure of other keys should remain");
		assertTrue(removeModel.get(1) == elemTwo , "Structure of other keys should remain");
		assertTrue(removeModel.get(3) == elemFour , "Structure of other keys should remain");
		
		// testing null object
		removeModel.remove(0);
		assertTrue(removeModel.get(0) == null, "Instance of <key:value> 0:elemOne sould be removed.");
		
		// removing rest of the model
		removeModel.remove(1);
		removeModel.remove(3);
		
		//TODO should be able to test the size.
		
		// new element added, new element key should not use historical keys. 
		UML_ClassBox elemFive = new UML_ClassBox("class box 5"); 
		removeModel.put(elemFive);
		assertTrue(removeModel.get(4) == elemFive, "The next key grabbed for element five should be 4");
	}
	
	//TODO 
	/**
	 * Test: class box constructors 
	 */
	@Test
	void testClassBoxCtor() {
		
	}
	
	/** 
	 * Testing: getAttribute (and collection version), putAttribute, removeAttribute, hasAttributes, 
	 * countAttributes, putSignature, hasSignature, removeSignature. 
	 * 
	 * Description: There will be four class boxes each contain either an attribute or an operation. These
	 * tests evaluate proper sets and gets for each respective element type.
	 */
	@Test
	void testClassBoxAttrsOp() {
		UML_Model m = new UML_Model(null);
		
		UML_ClassBox elemOne = new UML_ClassBox(); 
		UML_ClassBox elemTwo = new UML_ClassBox(); 
		UML_ClassBox elemThree = new UML_ClassBox(); 
		UML_ClassBox elemFour = new UML_ClassBox(); 
		
		m.put(elemOne);
		m.put(elemTwo);
		m.put(elemThree);
		m.put(elemFour);
		
		// Add and retrieve a class box attribute
		UML_Attribute customerAttr = new UML_Attribute("ID", "int");
		elemOne.putAttribute(customerAttr); 
		assertTrue(elemOne.getAttribute(0).getName() == "ID", "First elem should have a key of 0 AND the name should be ID");
		assertTrue(elemOne.getAttribute(0).getType() == "int", "First elem should have a key of 0 AND the type of int");
		
		// put name to replace current name
		elemOne.getAttribute(0).putName("Loan Issued"); 
		assertTrue(elemOne.getAttribute(0).getName() == "Loan Issued", "New attr name should be Loan Issued but"); 
		elemOne.getAttribute(0).putType("boolean");
		assertTrue(elemOne.getAttribute(0).getType() == "boolean", "new attr should be of type boolean"); 
		
		// Testing visibility + parameter 
		UML_Attribute bookAttr = new UML_Attribute(null ,"ID", "int");
		elemTwo.putAttribute(bookAttr);
		
		// first attribute of this element should still be 0
		assertTrue(elemTwo.getAttribute(0) == bookAttr, "class box elem 0 should be bookAttr");
		assertTrue(elemTwo.getAttribute(0).getVisibility() == null, "class box visibility should be null");
		assertTrue(elemTwo.getAttribute(0).putVisibility(null) == null, "modifying visibility when already null should remain null");
		
		// counting attributes
		assertTrue(elemOne.countAttributes() == 1, "elemOne class box should have only 1 atttribute");
		elemOne.removeAttribute(0);
		assertTrue(elemOne.countAttributes() == 0, "elemOne class box should have 0 attributes");
		
		// count operations
		UML_Operation customerOp = new UML_Operation();
		elemThree.putOperation(customerOp);
		assertTrue(elemThree.countOperations() == 1, "one operation should be in class box elemThree");
		assertTrue(elemThree.getOperation(0) == customerOp, "first op in elemThree should be customerOp");
		
		// verifying signatures
		elemThree.getOperation(0).putSignature("bool addBool(bool)");
		assertTrue(elemThree.getOperation(0).getSignature() == "bool addBool(bool)", "elemThree's first operation should have a signature of \'boolean\'");
		assertTrue(elemThree.getOperation(0).hasSignature() == true, "elemThree should have a signature");
		
		// remove signature
		UML_Operation bookOp = new UML_Operation("t", "int addBook(int, int)");
		elemFour.putOperation(bookOp);
		assertTrue(elemFour.getOperation(0).removeSignature() != null, "elemFour's remove operation method should return old signature");
		
		
		//TODO need to add new code tests 
		
	}
	
	/**
	 * Testing: Relationships, dependency, generalization, relationship
	 * Description: creation of a small model tested and modified to verify robustness of relationships between class box 
	 * objects
	 * 
	 */
	@Test
	void testRelationships() {
		// parent class boxes
		UML_ClassBox roalty = new UML_ClassBox("people");
		UML_ClassBox people = new UML_ClassBox("people");
		
		// children class boxes
		UML_ClassBox romeo = new UML_ClassBox("Romeo"); 
		UML_ClassBox juliet = new UML_ClassBox("Juliet");
		
		// UML_Relationships{parents class} to the following relationship types
		UML_Dependency d1 = new UML_Dependency("d1", romeo, juliet);
		UML_Generalization g1 = new UML_Generalization("g1", romeo, people);
		UML_Generalization g2 = new UML_Generalization("g2", juliet, people);
		UML_Association a1 = new UML_Association("a1", romeo, juliet);
		
		// verify sources and targets methods
		assertTrue(d1.getSource() == romeo, "source of d1 should be romeo");
		assertTrue(d1.getTarget() == juliet, "target of d1 should be juliet");
		
		// verifying class, name and boolen src. and targ. methods
		assertTrue(g1.getClass() == UML_Generalization.class, "class of g1 should be of type uml_generalizaion");
		assertTrue(g1.hasName() == true, "g1 should have a name");
		assertTrue(g2.hasSource() == true, "g2 should have a source");
		assertTrue(a1.hasTarget() == true, "target of a1 should be true");
		
		// robustness testing of UML_Relationship functions applied to sub-class relationships. 
		assertTrue(a1.putName("association1") == "a1", "previous name should be returned with update");
		assertTrue(a1.getName() == "association1", "name updated");
		assertTrue(a1.putSource(people) == romeo, "should return previous source romeo");
		assertTrue(a1.getSource() == people, "should return new source people");
		
		// testing relationship modifications
		g1.putTarget(roalty);
		g2.putTarget(roalty);
		assertTrue(g1.getTarget() == roalty && g2.getTarget() == roalty, "updated generalization should refelct roalty"); 
	}
	
	
	/**
	 * 
	 */
	@Test
	void testControllerAndModel() {
		UML_Controller cont = new UML_Controller(null);
		
		// attributes
		UML_Attribute age = new UML_Attribute("age", "int");
		UML_Attribute id = new UML_Attribute("id", "int");
		
		// operations
		UML_Operation buy = new UML_Operation("t", "bool buy(item)");
		UML_Operation sell = new UML_Operation("t", "bool sell(item)");
		
		
		//setting up a simple class box with attributes and operations 
		UML_ClassBox customer = new UML_ClassBox("customer");
		customer.putAttribute(age);
		customer.putAttribute(id);
		customer.putOperation(buy);
		
		//setting up a simple class box with attributes and operations 
		UML_ClassBox employee = new UML_ClassBox("employee");
		customer.putAttribute(age);
		customer.putAttribute(id);
		customer.putOperation(sell);
		
		// testing putting elements into the controller
		cont.put(customer);
		cont.put(employee);
		assertTrue(cont.get(0, UML_ClassBox.class) == customer, "first element placed into model via controller is customer");
		assertTrue(cont.get(1, UML_ClassBox.class) == employee, "second element placed into model via controller is employee");
		
		// testing controller removal of an element
		cont.remove(0);
		assertTrue(cont.get(0, UML_ClassBox.class) == null, "customer should be removed from the model");	
		
	}
}
