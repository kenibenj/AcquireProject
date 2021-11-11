package UserInterface;

import AcquireProject.Game;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class GameUI {

    @Getter private Scene scene;
    @Setter private Game game;

    public static final int PLACE_TILE = 0;
    public static final int BUY_STOCK = 1;
    public static final int END_GAME = 2;
    public static final int MERGING = 3;
    public static final int FOUND_CHAIN = 4;

    private List<VBox> actionBoxMenus;

    private UserInterface ui;



    public GameUI(UserInterface ui, Game game){
        this.ui = ui;
        this.game = game;

        this.actionBoxMenus = new ArrayList<VBox>();
        makeActionBoxMenus();

        this.scene = makeScene();
    }

    private Scene makeScene(){
        BorderPane border = new BorderPane();

        Scene scene = new Scene(border, 800, 600);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("stylesheet.css").toExternalForm());

        Text title = new Text();
        title.setText("Acquire Game");

        border.setTop(title);

        border.setCenter(makeGameBoard());

        border.setBottom(makePlayerInfo());

        //TEMPORARY// make sure to add actual switching code for action menus
        border.setRight(actionBoxMenus.get(MERGING));

        return scene;
    }

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

    private GridPane makeGameBoard(){
        GridPane gameBoard =new GridPane();

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

    private void makeActionBoxMenus(){
        this.actionBoxMenus.add(makePlaceTileMenu());
        this.actionBoxMenus.add(makeBuyStockMenu());
        this.actionBoxMenus.add(makeEndGameMenu());
        this.actionBoxMenus.add(makeMergingMenu());
        this.actionBoxMenus.add(makeFoundChainMenu());
    }

    private VBox makePlaceTileMenu(){
        VBox menu = new VBox();

        Text title = new Text("Which tile would you like to place?");
        menu.getChildren().add(title);

        GridPane tileGrid = new GridPane();
        List<String> tileNames = game.getCurrentPlayerTiles();

        int numberOfColumns = 3;

        for(int i = 0; i < tileNames.size(); i++){
            Button t = new Button(tileNames.get(i));
            tileGrid.add(t, i % numberOfColumns, i / numberOfColumns, 1, 1);
        }

        menu.getChildren().add(tileGrid);

        return menu;
    }

    private VBox makeBuyStockMenu(){
        VBox menu = new VBox();

        Text title = new Text("Buy stock");
        menu.getChildren().add(title);

        Text prompt = new Text("Stock left to buy:" + game.getNumberOfStockLeftToBuy());
        menu.getChildren().add(prompt);

        List<String> stocks = game.getAvailableStocks();

        for(int i = 0; i < stocks.size(); i++){
            Button s = new Button(stocks.get(i));
            menu.getChildren().add(s);
        }

        Button continueButton = new Button("Continue");
        menu.getChildren().add(continueButton);



        return menu;
    }

    private VBox makeEndGameMenu(){
        VBox menu = new VBox();

        Text title = new Text("Would you like to end the game?");
        menu.getChildren().add(title);

        Button endGameButton = new Button("End Game");
        Button continueButton = new Button("Continue Playing");

        menu.getChildren().add(endGameButton);
        menu.getChildren().add(continueButton);

        return menu;
    }

    private VBox makeMergingMenu(){
        VBox menu = new VBox();

        Text title = new Text("Merging");
        menu.getChildren().add(title);

        String playerName = game.getMergingPlayerName();
        int playerStock = game.getMergingPlayerStockAmount();

        Text prompt = new Text(playerName + ": you currently have " + playerStock + " in the acquired chain\n " +
                "Would you like to:");
        menu.getChildren().add(prompt);

        Spinner sellAmount = new Spinner(0, 5, 0);
        Spinner tradeAmount = new Spinner(0, 5, 0);
        menu.getChildren().add(sellAmount);
        menu.getChildren().add(tradeAmount);

        return menu;
    }

    private VBox makeFoundChainMenu(){
        VBox menu = new VBox();

        Text title = new Text("Which hotel chain would you like to found?");
        menu.getChildren().add(title);

        List<String> names = game.getUnfoundedChains();

        for(int i = 0; i < names.size(); i++){
            Button b = new Button(names.get(i));
            menu.getChildren().add(b);
        }

        return menu;
    }

    public void changeActionMenu(int menuOption) throws IndexOutOfBoundsException{
        if(menuOption >= 0 && menuOption < actionBoxMenus.size()){
            //change border right to menu
            return;
        }
        throw new IndexOutOfBoundsException("The requested menu was not recognized while trying to switch action menus");
    }


}
