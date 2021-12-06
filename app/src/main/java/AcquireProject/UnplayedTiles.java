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
 * UnplayedTile class that creates and holds a list of all Tile objects and gives them out as needed
 *
 * @author Benjamin, Emily, Michael
 *
 * @since 1.0.0
 */

package AcquireProject;

import java.util.ArrayList;
import java.util.List;

public  class UnplayedTiles {


    private List<Tile> allTiles = new ArrayList<>();

    /**
     * Constructor that creates a list of all 108 Tile objects in the game
     */
     UnplayedTiles() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 9; j++) {
                ArrayList<Integer> coords = new ArrayList<>();
                coords.add(i);
                coords.add(j);
                allTiles.add(new Tile(coords));
            }
        }
    }

    /**
     * alternate constructor for tests
     */


    UnplayedTiles(List<Tile> sampleTiles){
        this.allTiles = sampleTiles;
    }

    /**
     * method that retrieves the list of unplayed tiles
     *
     * @return list of unplayed Tile objects
     */
    public List<Tile> getTiles(){
        return allTiles;
    }

    /**
     * method that randomly removes and returns a tile from the list of unplayed tiles
     *
     * @return the Tile object that was selected from the list
     */
    public List<Tile> drawTile(){
        List<Tile> tile = new ArrayList<>();

        if(allTiles.size() > 0) {
            int random = (int) ((Math.random() * getTiles().size()));
            tile.add(getTiles().remove(random));
        }

        return tile;
    }

    /**
     * method that randomly removes 6 tiles from the unplayed tile list and returns these 6 as a list
     *
     * @return list of 6 tiles
     */
    public ArrayList<Tile> drawStartingTiles(){
         ArrayList<Tile> startingTiles = new ArrayList<Tile>();
         for(int i =0;i<6;i++) {
             int random = (int) ((Math.random() * getTiles().size()));
             Tile returnTile = getTiles().get(random);
             getTiles().remove(random);
             startingTiles.add(returnTile);
         }
         return startingTiles;
    }
}
