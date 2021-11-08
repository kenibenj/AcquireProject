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

    public void createUnplayedTiles() {
        for (int i = 1; i < 13; i++) {
            for (char alphabet = 'A'; alphabet <= 'I'; alphabet++) {
                tiles.add(String.valueOf(i) + alphabet);
            }
        }
    }

    public ArrayList<String> unPlayedTiles(){
        return tiles;
    }


    public String drawTile(){
        int random = (int)((Math.random()*unPlayedTiles().size()));
        String return_string = unPlayedTiles().get(random);
        unPlayedTiles().remove(random);

        return return_string;
    }
}
