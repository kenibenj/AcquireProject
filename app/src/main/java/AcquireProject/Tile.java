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
 * @author Emily, Michael, Benjamin
 * @version 1.0.0
 * @since 11/8/2021
 */

package AcquireProject;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tile {

    @Getter private final String tileName;
    @Getter private final List<Integer> coordinates;
    @Getter @Setter private String chainName = "";

    public Tile(List<Integer> coordinates) {
        this.tileName = tileToString(coordinates);
        this.coordinates = coordinates;
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

    /**
     * converts a list of coordinates to a string
     * @param coordinates the coordinates of the tile
     * @return the string name of the tile
     * @author Michael Collier
     */
    private String tileToString(List<Integer> coordinates){
        List<Character> y_coords = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I');
        int xCoord = coordinates.get(0)+1;
        String name = Integer.toString(xCoord);
        name += y_coords.get(coordinates.get(1));
        return name;
    }
}