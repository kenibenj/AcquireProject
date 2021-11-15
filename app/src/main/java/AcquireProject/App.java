/**

 * A remake of the Acquire board game in java
 *
 * @author Michael Collier
 * @author Emily Elzinga
 * @author Benjamin Keninger
>>>>>>> 88fb7b2a25f93406ef6f57beb56b7fb2ad339b54
 */

package AcquireProject;

import UserInterface.UserInterface;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application{

    static UserInterface ui = new UserInterface();

    public static void main(String[] args) {

        Application.launch();

    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Acquire");
        stage.show();
        ui.setStage(stage);
        ui.goToMainMenu();
    }



}
