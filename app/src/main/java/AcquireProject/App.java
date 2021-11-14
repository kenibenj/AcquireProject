/**
 * Application class that runs the main method of the program
 *
 * @author Benjamin, Emily, Michael
 *
 * @since 1.0.0
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
