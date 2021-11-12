package UserInterface;

import AcquireProject.Game;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;

public class FoundChainMenu extends ActionMenu{

    public FoundChainMenu(Game game, GameUI ui){
        super(game, ui);
    }

    @Override
    public VBox getMenu() {
        return menu;
    }

    @Override
    public void updateMenu() {
        menu = new VBox();

        Text title = new Text("Which hotel chain would you like to found?");
        menu.getChildren().add(title);

        List<String> names = game.getUnfoundedChains();

        for(int i = 0; i < names.size(); i++){
            Button b = new Button(names.get(i));
            menu.getChildren().add(b);
        }

    }

}
