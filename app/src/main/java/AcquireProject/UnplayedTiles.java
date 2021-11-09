/*
 * Created by Benjamin Keninger
 *
 * 11/5/2021
 */

package AcquireProject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UnplayedTiles {

    ArrayList<String> tiles = new ArrayList<>();

     UnplayedTiles() {
        for (int i = 1; i < 13; i++) {
            for (char alphabet = 'A'; alphabet <= 'I'; alphabet++) {
                tiles.add(String.valueOf(i) + alphabet);
            }
        }
    }

    public ArrayList<String> getTiles(){
        return tiles;
    }


    public String drawTile(){
        int random = (int)((Math.random()*getTiles().size()));
        String return_string = getTiles().get(random);
        getTiles().remove(random);

        return return_string;
    }
}
