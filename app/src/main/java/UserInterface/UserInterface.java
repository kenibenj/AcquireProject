/**
 * MIT License
 *
 * Copyright (c) 2021 Michael Collier, Emily Elzinga, Benjamin Keninger
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
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
