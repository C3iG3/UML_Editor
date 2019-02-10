package storming.uml_editor.controller;

import storming.uml_editor.model.UML_Model;
import storming.uml_editor.view.UML_View;

public class UML_Controller {
	UML_Model model;
	UML_View view;
	
	public UML_Controller() {
		model = new UML_Model(this);
		view = new UML_View(this);
	}
}
