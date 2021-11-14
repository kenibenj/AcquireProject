/**
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


    private static List<Tile> allTiles = new ArrayList<>();

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
