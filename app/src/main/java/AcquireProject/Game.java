package AcquireProject;

import java.util.ArrayList;
import java.util.List;

/**
 * A facade class to control the game classes
 */
public class Game {

    public Game(){

    }

    /**
     * A method to switch the scene back to the main menu
     */
    public void returnToMainMenu(){

    }

    /**
     * Puts together a list of the players names
     *
     * @return a list of player names in turn order
     */
    public List<String> getPlayerNames(){
        List<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");
        return names;
    }


}
