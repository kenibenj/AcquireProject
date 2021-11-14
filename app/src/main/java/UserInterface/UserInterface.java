/**
 * proveds the functionality to switch between scenes in the ui
 *
 * @author Michael Collier
 *
 * @since 1.0.0
 */

package UserInterface;

import AcquireProject.Game;
import AcquireProject.Loader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Setter;

import java.io.IOException;

public class UserInterface {

    @Setter private Stage stage;

    private MainMenu mainMenu;
    private GameUI gameui;
    private Loader loader;

    public UserInterface(Stage stage){
        loader = new Loader();
        this.stage = stage;
        this.mainMenu = new MainMenu(this);
        Game game = new Game();
        this.gameui = new GameUI(this, game);
    }

    public UserInterface(){
        this(new Stage());
    }

    private void changeScene(Scene newScene){
        this.stage.setScene(newScene);
    }

    /**
     * changes the scene to the game
     */
    public void goToGame(){
        changeScene(gameui.getScene());
    }

    /**
     * changes the scene to the main menu
     */
    public void goToMainMenu(){
        changeScene(mainMenu.getScene());
    }

    /**
     * loads a game from the file
     */
    public void loadGame(){
        gameui.setGame(loader.loadGame());
        gameui.loadState();
    }

    /**
     * saves a game to the file
     *
     * @param game the game to be saved
     */
    public void saveGame(Game game){
        try {
            loader.saveGame(game);
        }catch(IOException e){
            System.out.println("Problem when saving the game");
            e.printStackTrace();
        }
    }

}
