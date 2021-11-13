package UserInterface;

import AcquireProject.Game;
import AcquireProject.Merger;
import javafx.scene.control.Spinner;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Objects;

public class MergingMenu extends ActionMenu{

    private Merger currentMerger = null;

    public MergingMenu(Game game, GameUI ui){
        super(game, ui);
    }

    @Override
    public VBox getMenu() {
        return menu;
    }

    @Override
    public void updateMenu(){
        menu = new VBox();

        if(Objects.isNull(currentMerger)){
            currentMerger = game.getCurrentMerger();
            currentMerger.giveShareholderBonus();
        }

        Text title = new Text("Merging");
        menu.getChildren().add(title);

        String playerName = game.getMergingPlayerName();
        int playerStock = game.getMergingPlayerStockAmount();

        Text prompt = new Text(playerName + ": you currently have " + playerStock + " in the acquired chain\n " +
                "Would you like to:");
        menu.getChildren().add(prompt);

        Spinner sellAmount = new Spinner(0, playerStock, 0);
        Spinner tradeAmount = new Spinner(0, playerStock, 0, 2);
        menu.getChildren().add(sellAmount);
        menu.getChildren().add(tradeAmount);

    }

}
