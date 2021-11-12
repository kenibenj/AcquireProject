package UserInterface;

import AcquireProject.Game;
import javafx.scene.layout.VBox;

/**
 * an action menu is switched out to change the options that a user is given during each step of the turn cycle
 *
 * @author Michael Collier
 *
 * @since 1.0.0
 */
public abstract class ActionMenu {

    protected VBox menu;

    Game game;
    GameUI ui;

    public ActionMenu(Game game, GameUI ui){
        this.game = game;
        this.ui = ui;
        this.menu = new VBox();
    }

    public void updateMenu(){}

    public VBox getMenu(){
        return menu;
    }

}
