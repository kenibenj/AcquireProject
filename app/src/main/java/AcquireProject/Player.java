/*
 * Created by Emily Elzinga
 *
 * 11/5/2021
 */

package AcquireProject;

import java.util.ArrayList;
import java.util.List;

public class Player {

    Player(String name) {
        String player_name = name;
        int balance = 6000;
        ArrayList<String> player_tiles = new ArrayList<>();
        player_tiles.add(UnplayedTiles.drawTiles);
        player_tiles.add(UnplayedTiles.drawTiles);
        player_tiles.add(UnplayedTiles.drawTiles);
        player_tiles.add(UnplayedTiles.drawTiles);
        player_tiles.add(UnplayedTiles.drawTiles);
        player_tiles.add(UnplayedTiles.drawTiles);
    }
}
