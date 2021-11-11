package UserInterface;

import AcquireProject.Game;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class GameUI {

    @Getter private Scene scene;
    @Setter private Game game;

    private BorderPane border;

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

    private Scene makeScene(){

        Scene scene = new Scene(border, 800, 600);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("stylesheet.css").toExternalForm());

        Text title = new Text();
        title.setText("Acquire Game");

        border.setTop(title);

        border.setCenter(makeGameBoard());

        border.setBottom(makePlayerInfo());

        border.setRight(actionMenus.get(ADD_PLAYERS).getMenu());

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

    public void updatePlayerInfo(){
        border.setBottom(makePlayerInfo());
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

    private void makeActionMenus(){
        this.actionMenus.add(new PlaceTileMenu(game, this));
        this.actionMenus.add(new BuyStockMenu(game, this));
        this.actionMenus.add(new EndGameMenu(game, this));
        this.actionMenus.add(new MergingMenu(game, this));
        this.actionMenus.add(new FoundChainMenu(game, this));
        this.actionMenus.add(new AddPlayersMenu(game, this));
    }

    public void changeActionMenu(int menuOption) throws IndexOutOfBoundsException{
        if(menuOption >= 0 && menuOption < actionMenus.size()){
            actionMenus.get(menuOption).updateMenu();
            border.setRight(actionMenus.get(menuOption).getMenu());
            return;
        }
        throw new IndexOutOfBoundsException("The requested menu was not recognized while trying to switch action menus");
    }


}
