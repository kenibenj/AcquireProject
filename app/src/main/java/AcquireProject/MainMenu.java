package AcquireProject;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import lombok.Getter;

public class MainMenu {

    @Getter private Scene scene;

    public MainMenu(){

        this.scene = makeScene();

    }



    private Scene makeScene(){

        Group group = new Group();

        Scene scene = new Scene(group, 800, 600, Color.WHITE);
        scene.getStylesheets().add("/stylesheet.css");

        Text title = new Text(10, 20, "ACQUIRE");
        title.setId("fancytext");

        Button startButton = new Button("Start");

        group.getChildren().add(title);
        //group.getChildren().add(startButton);

        return scene;
    }

}
