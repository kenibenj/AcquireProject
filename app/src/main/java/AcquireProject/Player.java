/*
 * Created by Emily Elzinga
 *
 * 11/5/2021
 */

package AcquireProject;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String playerName;
    private int balance;
    private ArrayList<String> playerTiles;

    Player(String name) {
        playerName = name;
        int balance = 6000;
        playerTiles = new ArrayList<>();
    }

    public void addTile(String tile){
        playerTiles.add(tile);
    }
}
