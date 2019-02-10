package storming.uml_editor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import storming.uml_editor.controller.UML_Controller;
import storming.uml_editor.model.things.classbox.UML_Model_ClassBox;

public class Driver extends Application {

//    @Override
//    public void start(Stage stage) {
//        String javaVersion = System.getProperty("java.version");
//        String javafxVersion = System.getProperty("javafx.version");
//        Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
//        Scene scene = new Scene(new StackPane(l), 640, 480);
//        stage.setScene(scene);
//        stage.show();
//    }
	
	public void start(Stage stage) {}

    public static void main(String[] args) {
    	var controller = new UML_Controller();
        // launch();
    }

}