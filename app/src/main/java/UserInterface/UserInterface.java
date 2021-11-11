package UserInterface;

import AcquireProject.Game;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Setter;

public class UserInterface {

    @Setter private Stage stage;

    private MainMenu mainMenu;
    private GameUI gameui;

    public UserInterface(Stage stage){
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

}
