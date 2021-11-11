package UserInterface;

import AcquireProject.Game;
import javafx.scene.layout.VBox;

public abstract class ActionMenu {

    protected VBox menu;

    Game game;
    GameUI ui;

    public ActionMenu(Game game, GameUI ui){
        this.game = game;
        this.ui = ui;
    }

    public void updateMenu(){}

    public VBox getMenu(){
        return menu;
    }

}
