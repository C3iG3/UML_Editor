package storming.uml_editor.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import storming.uml_editor.controller.UML_Controller;

public class UML_View extends Application {
	private UML_Controller controller;
	
	public static Stage stage;
	
	public static void main(String[] args) {
    	launch(args);
    }
	
	public UML_View() {
		this.controller = new UML_Controller(this);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		
		Parent root = FXMLLoader.load(getClass().getResource("/try.fxml"));
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("Storming UML Editor");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void btnClose_Click()
    {
        boolean confirm = false;
        confirm = ConfirmationBox.show(
			"Are you sure you want to quit?", "Confirmation",
			"Yes", "No"
		);
        
        if (confirm)
        {
			// Place any code needed to save files or
			// close resources here.
			stage.close();
		}
	}
}
