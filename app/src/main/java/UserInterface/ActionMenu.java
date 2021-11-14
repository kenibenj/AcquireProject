/**
 * an action menu is switched out to change the options that a user is given during each step of the turn cycle
 *
 * @author Michael Collier
 *
 * @since 1.0.0
 */

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
        this.menu = new VBox();
    }

    /**
     * updates the elements in the menu to reflect current gameplay
     */
    public void updateMenu(){}

    /**
     * @return the menu contained in a VBox
     */
    public VBox getMenu(){
        return menu;
    }

}
