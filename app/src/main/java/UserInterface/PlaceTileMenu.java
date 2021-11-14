/**
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
    
    public PlaceTileMenu(Game game, GameUI ui){
        super(game, ui);
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
        List<String> tileNames = game.getCurrentPlayerTiles();

        int numberOfColumns = 3;

        int numberOfPlayableTiles = 0;

        for(int i = 0; i < tileNames.size(); i++){
            Button t = new Button(tileNames.get(i));
            if(game.moveIsLegal(i)) numberOfPlayableTiles++;
            t.setDisable(!game.moveIsLegal(i));
            t.getStyleClass().add("tile");
            t.getStyleClass().add("playedTile");

            int index = i;

            t.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int tileIndex = index;
                    game.placeTile(tileIndex);
                    game.addTileToCurrentPlayer();
                    ui.updateGameBoard();

                    Founder needFounder = game.foundNeeded();

                    if(!Objects.isNull(needFounder)){
                        ui.changeActionMenu(GameUI.FOUND_CHAIN);
                        return;
                    }


                    if(game.mergeNeeded()){
                        ui.changeActionMenu(GameUI.MERGING);
                        return;
                    }

                    ui.changeActionMenu(GameUI.BUY_STOCK);

                    if(game.gameCanEnd()){
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
                    game.giveCurrentPlayerNewHand();
                    ui.changeActionMenu(GameUI.PLACE_TILE);
                }
            });
            menu.getChildren().add(getNewHandButton);
        }

    }
}
