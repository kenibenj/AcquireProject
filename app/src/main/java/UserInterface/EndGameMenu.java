/**
 * Gives the options for ending the game
 *
 * @author Michael Collier
 *
 * @since 1.0.0
 */

package UserInterface;

import AcquireProject.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class EndGameMenu extends ActionMenu{

    String winnerMessage = "";

    public EndGameMenu(Game game, GameUI ui){
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

        Text title = new Text("Would you like to end the game?");
        menu.getChildren().add(title);

        Button endGameButton = new Button("End Game");
        Button continueButton = new Button("Continue Playing");

        endGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                game.endGame();
                winnerMessage = game.getWinner();
                ui.updatePlayerInfo();
                ui.changeActionMenu(GameUI.END_GAME);
            }
        });

        continueButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ui.changeActionMenu(GameUI.BUY_STOCK);
            }
        });

        if(winnerMessage.equals("")) {
            menu.getChildren().add(endGameButton);
            menu.getChildren().add(continueButton);
        }else{
            Text message = new Text(winnerMessage);
            menu.getChildren().add(message);
        }

    }

}
