/**
 * Gives the options for trading or selling stock when merging
 *
 * @author Michael Collier
 *
 * @since 1.0.0
 */

package UserInterface;

import AcquireProject.Game;
import AcquireProject.Merger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
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
        menu.getStyleClass().add("actionMenu");

        if(Objects.isNull(currentMerger)){
            currentMerger = game.getCurrentMerger();
            currentMerger.giveShareholderBonus();
        }



        Text title = new Text("Merging " + currentMerger.getAcquiredChain().getName() + " into " +
                currentMerger.getAcquiringChain().getName());
        menu.getChildren().add(title);

        Text prompt = new Text(currentMerger.getPlayerName() + " you still have " + currentMerger.getPlayerStockCount() +
                " stock in " + currentMerger.getAcquiredChain().getName());
        menu.getChildren().add(prompt);

        Button sellButton = new Button("Sell a Stock for: $" + currentMerger.getStockPrice());
        if(currentMerger.getPlayerStockCount() < 1){
            sellButton.setDisable(true);
        }
        menu.getChildren().add(sellButton);
        sellButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                currentMerger.sellStock();
                ui.changeActionMenu(GameUI.MERGING);
            }
        });

        Button tradeButton = new Button("Trade two stock for one in " + currentMerger.getAcquiringChain().getName());
        if(currentMerger.getPlayerStockCount() < 2){
            tradeButton.setDisable(true);
        }
        menu.getChildren().add(tradeButton);
        tradeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                currentMerger.tradeStock();
                ui.changeActionMenu(GameUI.MERGING);
            }
        });

        Button holdButton = new Button("Hold the remaining stock");
        menu.getChildren().add(holdButton);
        holdButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                currentMerger.goToNextPlayer();
                if(currentMerger.morePlayersToHandle()){
                    ui.changeActionMenu(GameUI.MERGING);
                }else if(game.mergeNeeded()){
                    currentMerger.mergeChains();
                    ui.updateGameBoard();
                    currentMerger = null;
                    ui.changeActionMenu(GameUI.MERGING);
                }else {
                    currentMerger.mergeChains();
                    ui.updateGameBoard();
                    ui.changeActionMenu(GameUI.BUY_STOCK);
                    currentMerger = null;
                }
            }
        });


    }

}
