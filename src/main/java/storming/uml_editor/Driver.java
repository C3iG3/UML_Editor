/*
 * The ONLY reason this class is used is to fix issues with packaging the JAR.
 * 	Due to how JavaFX interacts with its dependencies and other complicated things,
 * 	the 'main' that is run by the JAR MUST be outside the class JavaFX extends
 */

package storming.uml_editor;

import storming.uml_editor.view.UML_View;

public class Driver {
	public static void main(String[] args) {
		UML_View.main(args);
	}
}