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
 * Gives the options which tile to place in each turn
 *
 * @author Michael Collier
 *
 * @since 1.0.0
 */

package UserInterface;

import AcquireProject.Founder;
import AcquireProject.Game;
import AcquireProject.Merger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;
import java.util.Objects;

public class PlaceTileMenu extends ActionMenu{
    
    public PlaceTileMenu(GameUI ui){
        super(ui);
    }

    @Override
    public VBox getMenu() {
        return menu;
    }

    @Override
    public void updateMenu() {
        menu = new VBox();
        menu.getStyleClass().add("actionMenu");

        Text title = new Text("Which tile would you like to place?");
        menu.getChildren().add(title);

        GridPane tileGrid = new GridPane();
        tileGrid.setStyle("-fx-padding: 20");
        List<String> tileNames = ui.getGame().getCurrentPlayerTiles();

        int numberOfColumns = 3;

        int numberOfPlayableTiles = 0;

        for(int i = 0; i < tileNames.size(); i++){
            Button t = new Button(tileNames.get(i));
            if(ui.getGame().moveIsLegal(i)) numberOfPlayableTiles++;
            t.setDisable(!ui.getGame().moveIsLegal(i));
            t.getStyleClass().add("tile");
            t.getStyleClass().add("playedTile");

            int index = i;

            t.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int tileIndex = index;
                    ui.getGame().placeTile(tileIndex);
                    ui.getGame().addTileToCurrentPlayer();
                    ui.updateGameBoard();

                    Founder needFounder = ui.getGame().foundNeeded();

                    if(!Objects.isNull(needFounder)){
                        ui.changeActionMenu(GameUI.FOUND_CHAIN);
                        return;
                    }


                    if(ui.getGame().mergeNeeded()){
                        ui.changeActionMenu(GameUI.MERGING);
                        return;
                    }

                    ui.changeActionMenu(GameUI.BUY_STOCK);

                    if(ui.getGame().gameCanEnd()){
                        ui.changeActionMenu(GameUI.END_GAME);
                    }
                }
            });

            tileGrid.add(t, i % numberOfColumns, i / numberOfColumns, 1, 1);
        }

        menu.getChildren().add(tileGrid);

        if(numberOfPlayableTiles == 0){
            Button getNewHandButton = new Button("Get a new hand");
            getNewHandButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    ui.getGame().giveCurrentPlayerNewHand();
                    ui.changeActionMenu(GameUI.PLACE_TILE);
                }
            });
            menu.getChildren().add(getNewHandButton);
        }

    }
}
