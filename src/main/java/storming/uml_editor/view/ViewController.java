package storming.uml_editor.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class ViewController {
	// Add the buttons in
	@FXML
	private MenuBar MenuBar;

	@FXML
	private Menu File;

	@FXML
	private MenuItem New;

	@FXML
	private MenuItem Open;

	@FXML
	private MenuItem Save;

	@FXML
	private MenuItem SaveAs;

	@FXML
	private MenuItem Close;

	@FXML
	private Menu Edit;

	@FXML
	private MenuItem Undo;

	@FXML
	private MenuItem Cut;

	@FXML
	private MenuItem Copy;

	@FXML
	private MenuItem Paste;

	@FXML
	private Button ClassBox;

	@FXML
	private Button Select;

	@FXML
	private Button Delete;

	@FXML
	private Button Comment;

	@FXML
	private Button Association;

	@FXML
	private Button Generalization;

	@FXML
	private Button Dependency;

	@FXML
	private Button Aggregation;

	@FXML
	private Button Composition;

	@FXML
	void EditPaste(ActionEvent event) {
		System.out.println("You Have Clicked The Edit Menu Paste Button\n");
	}

	@FXML
	void buttonClickAggregation(ActionEvent event) {
		System.out.println("You Have Clicked The Aggregation Button\n");
	}

	@FXML
	void buttonClickAssociation(ActionEvent event) {
		System.out.println("You Have Clicked The Association Button\n");
	}

	@FXML
	void buttonClickClassBox(ActionEvent event) {
		System.out.println("You Have Clicked The Class Box Button\n");
	}

	@FXML
	void buttonClickComment(ActionEvent event) {
		System.out.println("You Have Clicked The Comment Button\n");
	}

	@FXML
	void buttonClickComposition(ActionEvent event) {
		System.out.println("You Have Clicked The Composition Button\n");
	}

	@FXML
	void buttonClickDelete(ActionEvent event) {
		System.out.println("You Have Clicked The Delete Button\n");
	}

	@FXML
	void buttonClickDependency(ActionEvent event) {
		System.out.println("You Have Clicked The Dependency Button\n");
	}

	@FXML
	void buttonClickGeneralization(ActionEvent event) {
		System.out.println("You Have Clicked The Generalization Button\n");
	}

	@FXML
	void buttonClickSelect(ActionEvent event) {
		System.out.println("You Have Clicked The Select Button\n");
	}

	@FXML
	void editCopy(ActionEvent event) {
		System.out.println("You Have Clicked The Edit Menu Copy Button\n");
	}

	@FXML
	void editCut(ActionEvent event) {
		System.out.println("You Have Clicked The Edit Menu Cut Button\n");
	}

	@FXML
	void editUndo(ActionEvent event) {
		System.out.println("You Have Clicked The Edit Menu Undo Button\n");
	}

	@FXML
	void fileClose(ActionEvent event) {
		//System.out.println("You Have Clicked The File Menu Close Button\n");
		boolean confirm = false;
		confirm = ConfirmationBox.show("Are you sure you want to quit?", "Confirmation", "Yes", "No");
		if (confirm) {
			UML_View.btnClose_Click();
		}
	}

	@FXML
	void fileNew(ActionEvent event) {
		System.out.println("You Have Clicked The File Menu New Button\n");
	}

	@FXML
	void fileOpen(ActionEvent event) {
		System.out.println("You Have Clicked The File Menu Open Button\n");
	}

	@FXML
	void fileSavAs(ActionEvent event) {
		System.out.println("You Have Clicked The File Menu Save As Button\n");
	}

	@FXML
	void fileSave(ActionEvent event) {
		System.out.println("You Have Clicked The File Menu Save Button\n");
	}
}
