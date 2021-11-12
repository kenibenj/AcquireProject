package UserInterface;

import UserInterface.UserInterface;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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
        scene.getStylesheets().add(getClass().getClassLoader().getResource("stylesheet.css").toExternalForm());


        Text title = new Text();
        title.setText("Acquire");
        title.getStyleClass().add("largeText");
        title.setTextAlignment(TextAlignment.CENTER);

        border.setTop(title);

        VBox buttons = new VBox();
        buttons.getStyleClass().add("buttonBox");
        buttons.setAlignment(Pos.BASELINE_CENTER);

        buttons.getChildren().add(makeStartButton());
        buttons.getChildren().add(makeLoadButton());
        buttons.getChildren().add(makeExitButton());

        border.setCenter(buttons);

        return scene;
    }

    private Button makeStartButton(){
        Button startButton = new Button("Start");
        startButton.getStyleClass().add("largeText");

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
        loadButton.getStyleClass().add("largeText");

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
        exitButton.getStyleClass().add("largeText");

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
