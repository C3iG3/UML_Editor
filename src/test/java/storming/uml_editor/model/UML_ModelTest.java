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
		
		// testing return of what should be the first element, indexed at 0 <start of the keys> 
		putModel.put(elemOne);
		assertEquals(elemOne, putModel.get(0));
		
		// testing collisions and overwrites with two elements
		putModel.put(elemTwo);
		assertEquals(elemTwo, putModel.get(1));
		assertTrue(putModel.get(1) != elemOne && putModel.get(0) != elemTwo, "The first elem should not be obtained with the second elem's key.");
		
		// Testing return value of the put method
		var E3 = putModel.put(elemThree);
		var E4 = putModel.put(elemFour);
		assertEquals(E3, elemThree, "Return value of E3 should be element three.");
		assertNotEquals(E4, elemThree, "E4 should not be elemThree");
		
		// Invalid element request
		assertTrue(putModel.get(10) == null, "There should be no element indexed at 9.");
	}
	
	/**
	 * Testing: remove
	 * 
	 * Description: Utilizing class boxes to test the robustness of the remove method. 
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
		assertEquals(null ,removeModel.get(2), "There should be no more instance of <key:value> 2:elemThree.");
		assertEquals(elemOne, removeModel.get(0), "Structure of other keys should remain");
		assertEquals(elemTwo ,removeModel.get(1), "Structure of other keys should remain");
		assertEquals(elemFour ,removeModel.get(3), "Structure of other keys should remain");
		
		// testing null object
		removeModel.remove(0);
		assertTrue(removeModel.get(0) == null, "Instance of <key:value> 0:elemOne sould be removed.");
		
		// removing rest of the model
		removeModel.remove(1);
		removeModel.remove(3);
		
		// new element added, new element key should not use historical keys. 
		UML_ClassBox elemFive = new UML_ClassBox("class box 5"); 
		removeModel.put(elemFive);
		assertEquals(elemFive, removeModel.get(4), "The next key grabbed for element five should be 4");
	}
	
	/**
	 * Test: class box constructors 
	 * 
	 * Description: Verifying the class attributes name, x, y, width and height - generalized by the UML_Thing - are can be set via the
	 * UML_ClassBox ctor and retrieved properly using UML_Thing methods. 
	 */
	@Test
	void testClassBoxCtor() {
		
		// constructor parameters ( X,Y,Width, Height) 
		UML_ClassBox elemOne = new UML_ClassBox(10.0, 15.0, 25.0, 20.0);
		var height = elemOne.getHeight();
		assertTrue(height == 20.0, "Height should be 20.0, a double but was:, " + height);
		var width = elemOne.getWidth();
		assertTrue(width == 25.0, "width should be 25.0, a double but was: " + width);
		var x = elemOne.getX();
		assertTrue(x == 10.0, "X should be 10.0, a double but was: " + x);
		var y = elemOne.getY();
		assertTrue(y == 15.0, "Y should be a 15.0, a double but was: " + y);
	}
	
	/**
	 * Testing: class box setters
	 * 
	 * Description: The purpose of these test cases is to verify the generalized UML_Thing super getters and setters 
	 * work with the generalized class box. Each parameter has it's own test case. 
	 */
	@Test
	void testClassBoxSG() {
		UML_ClassBox elemOne = new UML_ClassBox(10.0, 15.0, 25.0, 20.0);
		elemOne.setHeight(5.0);
		assertEquals(elemOne.getHeight(), 5.0, "Expected height to be 5.0, but was: " + elemOne.getHeight());
		elemOne.setWidth(5.0);
		assertEquals(elemOne.getWidth(), 5.0, "Expected width to be 5.0, but was: " + elemOne.getWidth());
		elemOne.setX(5.0);
		assertEquals(elemOne.getX(), 5.0, "Expected X to be 5.0, but was: " + elemOne.getX());
		elemOne.setY(5.0);
		assertEquals(elemOne.getY(), 5.0, "Expected Y to be 5.0, but was: " + elemOne.getY());	
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
		assertEquals("ID", elemOne.getAttribute(0).getName(), "First elem should have a key of 0 AND the name should be ID");
		assertEquals("int", elemOne.getAttribute(0).getType(), "First elem should have a key of 0 AND the type of int");
		
		// put name to replace current name
		elemOne.getAttribute(0).putName("Loan Issued"); 
		assertEquals("Loan Issued", elemOne.getAttribute(0).getName(), "New attr name should be Loan Issued but"); 
		elemOne.getAttribute(0).putType("boolean");
		assertEquals( "boolean",elemOne.getAttribute(0).getType(), "new attr should be of type boolean"); 
		 
		// Testing visibility + parameter 
		UML_Attribute bookAttr = new UML_Attribute(null ,"ID", "int");
		elemTwo.putAttribute(bookAttr);
		
		// first attribute of this element should still be 0
		assertEquals(bookAttr ,elemTwo.getAttribute(0), "class box elem 0 should be bookAttr");
		assertTrue(elemTwo.getAttribute(0).getVisibility() == null, "class box visibility should be null");
		assertTrue(elemTwo.getAttribute(0).putVisibility(null) == null, "modifying visibility when already null should remain null");
		
		// counting attributes
		assertEquals(1, elemOne.countAttributes(), "elemOne class box should have only 1 atttribute");
		elemOne.removeAttribute(0);
		assertEquals(0, elemOne.countAttributes(), "elemOne class box should have 0 attributes");
		
		// count operations
		UML_Operation customerOp = new UML_Operation();
		elemThree.putOperation(customerOp);
		assertEquals(1, elemThree.countOperations(), "one operation should be in class box elemThree");
		assertEquals(customerOp, elemThree.getOperation(0), "first op in elemThree should be customerOp");
		
		// verifying signatures
		elemThree.getOperation(0).putSignature("bool addBool(bool)");
		assertTrue(elemThree.getOperation(0).getSignature() == "bool addBool(bool)", "elemThree's first operation should have a signature of \'boolean\'");
		assertTrue(elemThree.getOperation(0).hasSignature() == true, "elemThree should have a signature");
		
		// remove signature
		UML_Operation bookOp = new UML_Operation("t", "int addBook(int, int)");
		elemFour.putOperation(bookOp);
		assertTrue(elemFour.getOperation(0).removeSignature() != null, "elemFour's remove operation method should return old signature");	
	}
	
	/**
	 * Testing: Relationships, dependency, generalization, relationship
	 * 
	 * Description: creation of a small model tested and modified to verify robustness of relationships between class box 
	 * objects
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
		assertEquals(romeo ,d1.getSource(), "Source of d1 should be:" + romeo + "but got: " + d1.getSource());
		assertEquals(juliet ,d1.getTarget(), "Target of d1 should be: " + juliet + " but got: " + d1.getSource());
		
		// verifying class, name and boolean source and target. methods
		assertEquals(UML_Generalization.class ,g1.getClass(), "class of g1 should be of type uml_generalizaion but was: " + g1.getClass());
		assertTrue(g1.hasName(), "g1 should have a name");
		assertTrue(g2.hasSource(), "g2 should have a source");
		assertTrue(a1.hasTarget(), "target of a1 should be true");
		
		// robustness testing of UML_Relationship functions applied to sub-class relationships. 
		assertEquals( "a1", a1.putName("association1"), "previous name should be returned with update");
		assertEquals("association1" ,a1.getName(), "name should be updated, but was: " + a1.getName());
		assertEquals( romeo ,a1.putSource(people), "should return previous source romeo");
		assertEquals(people ,a1.getSource(), "should return new source people");
		
		// testing relationship modifications
		g1.putTarget(roalty);
		g2.putTarget(roalty);
		assertEquals(roalty, g1.getTarget(), "updated generalization should refelct roalty"); 
		assertEquals(roalty, g2.getTarget(), "updated generalization should refelct roalty"); 
	}
	
	
	/**
	 * Testing: controller, model
	 * 
	 * Description: Verifying the integration of the controller and model using a simple model that contains a few class boxes,
	 * each with a few attributes and operations. 
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
		assertEquals(customer ,cont.get(0, UML_ClassBox.class), "first element placed into model via controller should be: " + customer + " but was: " + cont.get(0, UML_ClassBox.class) );
		assertEquals(employee, cont.get(1, UML_ClassBox.class), "second element placed into model via controller should be employee: " + employee +  "but was: " + cont.get(1, UML_ClassBox.class));
		
		// testing controller removal of an element
		cont.remove(0);
		assertEquals(null, cont.get(0, UML_ClassBox.class), "customer should be removed from the model");	
		
	}
}
