/**
 * MIT License
 *
 * Copyright (c) 2021 Michael Collier, Emily Elzinga, Benjamin Keninger
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * Runs all the UI elements when in the main menu
 *
 * @author Michael Collier
 *
 * @since 1.0.0
 */

package UserInterface;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import lombok.Getter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainMenu {

    @Getter private Scene scene;
    private UserInterface ui;

    public MainMenu(UserInterface ui){

        this.scene = makeScene();
        this.ui = ui;

    }



    private Scene makeScene(){

        BorderPane border = new BorderPane();
        border.getStyleClass().add("Tower");
        border.setStyle("-fx-padding: 30");

        Scene scene = new Scene(border, 800, 600, Color.WHITE);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("stylesheet.css").toExternalForm());


        /*Label title = new Label();
        title.setText("Acquire");
        title.setStyle("-fx-padding: 40");
        title.getStyleClass().add("largeText");
        title.getStyleClass().add("Tower");
        BorderPane.setAlignment(title, Pos.CENTER);

        border.setTop(title);*/

        try {
            Image logo = new Image(new FileInputStream("src\\main\\resources\\AcquireLogo.png"));
            ImageView logoView = new ImageView(logo);
            BorderPane.setAlignment(logoView, Pos.CENTER);
            border.setTop(logoView);
        }catch(FileNotFoundException e){
            System.out.println("Problem trying to load acquire logo");
            e.printStackTrace();
        }


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
        startButton.getStyleClass().add("mainMenuButton");

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
        loadButton.getStyleClass().add("mainMenuButton");

        loadButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    ui.loadGame();
                    ui.goToGame();
                }
            }
        );

        return loadButton;
    }

    private Button makeExitButton(){
        Button exitButton = new Button("Exit");
        exitButton.getStyleClass().add("largeText");
        exitButton.getStyleClass().add("mainMenuButton");

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
