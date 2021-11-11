/**
 * @author Emily Elzinga
 * @version 0.1.0
 * @since 11/8/2021
 */

package AcquireProject;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tile {

    @Getter private final String tileName;
    @Getter private final List<Integer> coordinates;
    private String chainFlag = "";

    public String getTileName() {
        return tileName;
    }

    public List<Integer> getCoordinates() {
        return coordinates;
    }

    public String getChainFlag() {
        return chainFlag;
    }

    public void setChainFlag(String chainFlag) {
        this.chainFlag = chainFlag;
    }


//    public String getTileName() {
//        return tileName;
//    }
//
//    public int[] getCoordinates() {
//        return coordinates;
//    }



    public Tile(String tileName) {
        this.tileName = tileName;
        this.coordinates = tileToCoords();
    }

    /**
     * Converts the string coordinate of a tile to the x-y coordinates used by the game board.
     * @returns a pair of x-y coordinates
     */
    private List<Integer> tileToCoords() {
        List<Character> y_coords = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I');
        List<Integer> coordinates = new ArrayList<>();
        coordinates.add(0,Character.getNumericValue(tileName.charAt(0)) - 1);
        coordinates.add(1,y_coords.indexOf(tileName.charAt(1)));
        return coordinates;
    }
}
