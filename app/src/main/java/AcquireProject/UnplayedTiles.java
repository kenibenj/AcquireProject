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
        for (int i = 1; i < 13; i++) {
            for (char alphabet = 'A'; alphabet <= 'I'; alphabet++) {
                tiles.add(new Tile(String.valueOf(i) + alphabet));
            }
        }
    }

    public ArrayList<Tile> getTiles(){
        return tiles;
    }


    public Tile drawTile(){
        int random = (int)((Math.random()*getTiles().size()));
        Tile returnTile = getTiles().get(random);
        getTiles().remove(random);

        return returnTile;
    }
}
