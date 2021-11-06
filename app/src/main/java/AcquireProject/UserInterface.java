package AcquireProject;

import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Setter;

public class UserInterface {

    @Setter private Stage stage;

    public UserInterface(Stage stage){
        this.stage = stage;
    }

    public UserInterface(){
        this(new Stage());
    }

    public void changeScene(Scene newScene){
        this.stage.setScene(newScene);
    }

}
