package UserInterface;

import AcquireProject.Game;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;

public class PlaceTileMenu extends ActionMenu{
    
    public PlaceTileMenu(Game game, GameUI ui){
        super(game, ui);
        createMenu();
    }

    @Override
    public void updateMenu() {

    }

    @Override
    public VBox getMenu() {
        return menu;
    }

    private void createMenu(){
        menu = new VBox();

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
    }
}
