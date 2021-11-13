package UserInterface;

import AcquireProject.Game;
import AcquireProject.Loader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Setter;

import java.io.IOException;


/**
 * proveds the functionality to switch between scenes in the ui
 *
 * @author Michael Collier
 *
 * @since 1.0.0
 */
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

    public void goToGame(){
        changeScene(gameui.getScene());
    }

    public void goToMainMenu(){
        changeScene(mainMenu.getScene());
    }

    public void loadGame(){
        gameui.setGame(loader.loadGame());
    }

    public void saveGame(Game game){
        try {
            loader.saveGame(game);
        }catch(IOException e){
            System.out.println("Problem when saving the game");
            e.printStackTrace();
        }
    }

}
