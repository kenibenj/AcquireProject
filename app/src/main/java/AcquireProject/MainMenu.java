package AcquireProject;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import lombok.Getter;

public class MainMenu {

    @Getter private Scene scene;
    private UserInterface ui;

    public MainMenu(UserInterface ui){

        this.scene = makeScene();
        this.ui = ui;

    }



    private Scene makeScene(){

        BorderPane border = new BorderPane();

        Scene scene = new Scene(border, 800, 600, Color.WHITE);
        //scene.getStylesheets().add("/stylesheet.css");

        Text title = new Text();
        title.setText("Acquire Main Menu");

        border.setTop(title);

        VBox buttons = new VBox();

        buttons.getChildren().add(makeStartButton());
        buttons.getChildren().add(makeLoadButton());
        buttons.getChildren().add(makeExitButton());

        border.setCenter(buttons);

        return scene;
    }

    private Button makeStartButton(){
        Button startButton = new Button("Start");

        startButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    ui.goToGame();
                }
            }
        );

        return startButton;
    }

    private Button makeLoadButton(){
        Button loadButton = new Button("Load");

        loadButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                }
            }
        );

        return loadButton;
    }

    private Button makeExitButton(){
        Button exitButton = new Button("Exit");

        exitButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.exit(0);
                }
            }
        );

        return exitButton;
    }

}
