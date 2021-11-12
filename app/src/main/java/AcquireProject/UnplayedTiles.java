/*
 * Created by Benjamin Keninger
 *
 * 11/5/2021
 */

package AcquireProject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public  class UnplayedTiles {


    private static ArrayList<Tile> tiles = new ArrayList<>();

     UnplayedTiles() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 9; j++) {
                ArrayList<Integer> coords = new ArrayList<>();
                coords.add(i);
                coords.add(j);
                tiles.add(new Tile(coords));
            }
        }
    }

    public static ArrayList<Tile> getTiles(){
        return tiles;
    }


    public static Tile drawTile(){
        int random = (int)((Math.random()*getTiles().size()));
        Tile returnTile = getTiles().get(random);
        getTiles().remove(random);

        return returnTile;
    }
}
