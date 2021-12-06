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
 * Runs all the user interface elements during gameplay
 *
 * @author Michael Collier
 *
 * @since 1.0.0
 */

package UserInterface;

import AcquireProject.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameUI {

    @Getter private Scene scene;
    @Setter private Game game;

    private BorderPane border;
    private GridPane gameBoard;

    public static final int PLACE_TILE = 0;
    public static final int BUY_STOCK = 1;
    public static final int END_GAME = 2;
    public static final int MERGING = 3;
    public static final int FOUND_CHAIN = 4;
    public static final int ADD_PLAYERS = 5;

    private int currentState = ADD_PLAYERS;

    private List<ActionMenu> actionMenus;

    private UserInterface ui;



    public GameUI(UserInterface ui, Game game){
        this.ui = ui;
        this.game = game;

        this.actionMenus = new ArrayList<ActionMenu>();
        makeActionMenus();

        border = new BorderPane();
        border.getStyleClass().add("gameBorder");

        this.scene = makeScene();
    }

    /**
     * makes the overall scene that holds all the elements displayed during gameplay
     *
     * @return a javafx scene containing all the gameplay ui
     */
    private Scene makeScene(){

        Scene scene = new Scene(border, 800, 600);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("stylesheet.css").toExternalForm());

        Text title = new Text();
        title.setText("Acquire Game");

        Button saveGameButton = new Button("Save");
        saveGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                game.setUIState(currentState);
                ui.saveGame(game);
            }
        });

        Button returnToMainMenuButton = new Button("Main Menu");
        returnToMainMenuButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ui.goToMainMenu();
            }
        });

        HBox menuButtons = new HBox();
        menuButtons.getChildren().add(saveGameButton);
        menuButtons.getChildren().add(returnToMainMenuButton);

        border.setTop(menuButtons);

        border.setCenter(makeGameBoard());

        border.setBottom(makePlayerInfo());

        loadState();

        return scene;
    }

    /**
     * creates the boxes along the bottom of the screen that hold player's names, balances, and stocks
     *
     * @return a HBox containing all the player information
     */
    private HBox makePlayerInfo(){
        HBox playerInfo = new HBox();
        playerInfo.getStyleClass().add("hbox");

        List<String> playerNames = game.getPlayerNames();
        List<Integer> playerBalances = game.getPlayerBalances();
        List<Map<String, Integer>> profiles = game.getPlayerStockProfiles();

        for(int i = 0; i < playerNames.size(); i++){

            VBox info = new VBox();
            info.getStyleClass().add("infoBox");

            Text name = new Text(playerNames.get(i));
            name.getStyleClass().add("mediumText");
            info.getChildren().add(name);

            Text balance = new Text("$" + playerBalances.get(i).toString());


            GridPane finance = new GridPane();
            finance.add(balance, 0, 0, 2, 1);

            Map<String, Integer> stockProfile = profiles.get(i);


            List<String> keys = StockProfiler.getChains();
            for(int j = 0; j < keys.size(); j++){
                Label stock = new Label(keys.get(j).charAt(0) + ": " + stockProfile.get(keys.get(j)).toString());
                stock.getStyleClass().add("stockDisplay");
                stock.getStyleClass().add(keys.get(j));
                finance.add(stock, (j+2)%3, (j+2)/3, 1, 1);
            }

            info.getChildren().add(finance);

            playerInfo.getChildren().add(info);

        }

        return playerInfo;
    }

    /**
     * allows outside classes to ask the ui to refactor the player information display
     */
    public void updatePlayerInfo(){
        border.setBottom(makePlayerInfo());
    }

    /**
     * makes the board of tiles to be displayed in the center of the screen
     *
     * @return a GridPane of elements describing the game board squares
     */
    private GridPane makeGameBoard(){
        gameBoard = new GridPane();

        String[] letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I"};

        for(int i = 0; i < 12; i++){
            for(int j = 0; j < 9; j++){

                Label tile = new Label(Integer.toString(i + 1) + letters[j]);
                tile.getStyleClass().add("tile");
                tile.setAlignment(Pos.CENTER);

                gameBoard.add(tile, i, j, 1, 1);

            }
        }

        return gameBoard;
    }

    /**
     * adds all the action menus to the action menu list
     */
    private void makeActionMenus(){
        this.actionMenus.add(new PlaceTileMenu(this));
        this.actionMenus.add(new BuyStockMenu(this));
        this.actionMenus.add(new EndGameMenu(this));
        this.actionMenus.add(new MergingMenu(this));
        this.actionMenus.add(new FoundChainMenu(this));
        this.actionMenus.add(new AddPlayersMenu(this));
    }

    /**
     * switches between action menus and makes sure they are up-to-date
     *
     * @param menuOption the menu to show
     *
     * @throws IndexOutOfBoundsException if the int passed in does not describe an available menu
     */
    public void changeActionMenu(int menuOption) throws IndexOutOfBoundsException{
        if(menuOption >= 0 && menuOption < actionMenus.size()){
            actionMenus.get(menuOption).updateMenu();
            updatePlayerInfo();
            currentState = menuOption;
            border.setRight(actionMenus.get(menuOption).getMenu());
            return;
        }
        throw new IndexOutOfBoundsException("The requested menu was not recognized while trying to switch action menus");
    }

    /**
     * updates the game board for any changes in the color of tiles
     */
    public void updateGameBoard(){
        List<Tile> playedTiles = game.getPlayedTiles();

        for(Tile t :playedTiles){
            int xCoord = t.getCoordinates().get(0);
            int yCoord = t.getCoordinates().get(1);

            int index = xCoord * 9 + yCoord;

            gameBoard.getChildren().get(index).getStyleClass().clear();
            gameBoard.getChildren().get(index).getStyleClass().add("tile");

            String style = t.getChainName();
            if(style.length() == 0) {
                gameBoard.getChildren().get(index).getStyleClass().add("playedTile");
            }else{
                gameBoard.getChildren().get(index).getStyleClass().add(style);
            }
        }
    }

    /**
     * loads the state that was saved in the game
     */
    public void loadState(){
        changeActionMenu(game.getUIState());
        updateGameBoard();
    }

    protected Game getGame(){
        return this.game;
    }

}
