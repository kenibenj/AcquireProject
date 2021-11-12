package UserInterface;

import AcquireProject.Game;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class EndGameMenu extends ActionMenu{

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

        Text title = new Text("Would you like to end the game?");
        menu.getChildren().add(title);

        Button endGameButton = new Button("End Game");
        Button continueButton = new Button("Continue Playing");

        menu.getChildren().add(endGameButton);
        menu.getChildren().add(continueButton);

    }

}
