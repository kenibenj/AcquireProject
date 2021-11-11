package UserInterface;

import AcquireProject.Game;
import javafx.scene.control.Spinner;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MergingMenu extends ActionMenu{

    public MergingMenu(Game game, GameUI ui){
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

    }

}
