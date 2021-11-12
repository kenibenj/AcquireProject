package UserInterface;

import AcquireProject.Founder;
import AcquireProject.Game;
import AcquireProject.Tile;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


/**
 * Runs all the user interface elements during gameplay
 *
 * @author Michael Collier
 *
 * @since 1.0.0
 */
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

    private List<ActionMenu> actionMenus;

    private UserInterface ui;



    public GameUI(UserInterface ui, Game game){
        this.ui = ui;
        this.game = game;

        this.actionMenus = new ArrayList<ActionMenu>();
        makeActionMenus();

        border = new BorderPane();

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

        border.setTop(title);

        border.setCenter(makeGameBoard());

        border.setBottom(makePlayerInfo());

        actionMenus.get(ADD_PLAYERS).updateMenu();
        border.setRight(actionMenus.get(ADD_PLAYERS).getMenu());

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

        for(int i = 0; i < playerNames.size(); i++){

            VBox info = new VBox();
            info.getStyleClass().add("infoBox");

            Text name = new Text(playerNames.get(i));
            Text balance = new Text("$" + playerBalances.get(i).toString());

            info.getChildren().add(name);
            info.getChildren().add(balance);

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

                VBox tile = new VBox();
                tile.getStyleClass().add("tile");

                Text name = new Text(Integer.toString(i + 1) + letters[j]);

                tile.getChildren().add(name);

                gameBoard.add(tile, i, j, 1, 1);

            }
        }

        return gameBoard;
    }

    /**
     * adds all the action menus to the action menu list
     */
    private void makeActionMenus(){
        this.actionMenus.add(new PlaceTileMenu(game, this));
        this.actionMenus.add(new BuyStockMenu(game, this));
        this.actionMenus.add(new EndGameMenu(game, this));
        this.actionMenus.add(new MergingMenu(game, this));
        this.actionMenus.add(new FoundChainMenu(game, this));
        this.actionMenus.add(new AddPlayersMenu(game, this));
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
            border.setRight(actionMenus.get(menuOption).getMenu());
            return;
        }
        throw new IndexOutOfBoundsException("The requested menu was not recognized while trying to switch action menus");
    }

    public void updateGameBoard(){
        List<Tile> playedTiles = game.getPlayedTiles();

        for(Tile t :playedTiles){
            int xCoord = t.getCoordinates().get(0);
            int yCoord = t.getCoordinates().get(1);

            int index = xCoord * 9 + yCoord;

            String style = t.getChainName();
            if(style == "") {
                gameBoard.getChildren().get(index).getStyleClass().add("playedTile");
            }else{
                gameBoard.getChildren().get(index).getStyleClass().add(style);
            }
        }
    }

    public void chooseChainToFound(Founder founder){
        changeActionMenu(FOUND_CHAIN);
    }

}
