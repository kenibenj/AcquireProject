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
 * Gives the options for adding new players at the beginning of the game
 *
 * @author Michael Collier
 *
 * @since 1.0.0
 */

package UserInterface;

import AcquireProject.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AddPlayersMenu extends ActionMenu{

    private int numberOfPlayers;
    private static final int MAX_PLAYERS = 6;

    public AddPlayersMenu(GameUI ui){
        super(ui);
        numberOfPlayers = 0;
    }

    @Override
    public void updateMenu(){
        this.menu = new VBox();
        menu.getStyleClass().add("actionMenu");

        Text title = new Text("Add Players");
        menu.getChildren().add(title);

        Text prompt = new Text("Enter the name of the next Player");
        menu.getChildren().add(prompt);

        TextField name = new TextField();
        menu.getChildren().add(name);

        Button continueButton = new Button("Add next player");
        menu.getChildren().add(continueButton);

        Button finishButton = new Button("Start the game");
        finishButton.setDisable(true);
        menu.getChildren().add(finishButton);

        continueButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!name.getCharacters().isEmpty()) {
                    ui.getGame().addPlayer(name.getCharacters().toString());
                    name.clear();
                    numberOfPlayers++;
                    if (numberOfPlayers >= MAX_PLAYERS - 1) {
                        continueButton.setDisable(true);
                    }
                    finishButton.setDisable(false);
                    
                    name.requestFocus();
                }
            }
        });

        finishButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(!name.getCharacters().isEmpty()){
                    ui.getGame().addPlayer(name.getCharacters().toString());
                    name.clear();
                    numberOfPlayers++;
                    if (numberOfPlayers >= MAX_PLAYERS - 1) {
                        continueButton.setDisable(true);
                    }
                }

                ui.getGame().goToNextPlayer();
                ui.updatePlayerInfo();
                ui.changeActionMenu(ui.PLACE_TILE);
            }
        });
    }

}
