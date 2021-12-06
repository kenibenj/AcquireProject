/**
 *
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
 * @author Emily Elzinga
 * @since 11/14/2021
 */

package AcquireProject

import spock.lang.Specification


class UnplayedTilesSpecification extends Specification {

    def "size-check"(){
//        def unplayedTiles0 = new UnplayedTiles();
//        expect:
//        unplayedTiles0.getTiles().size() == 108
    }

    def "return-a-tile-from-the-list"(){
        def unplayedTiles1 = new UnplayedTiles();
        expect:
        unplayedTiles1.drawTile() instanceof List<Tile>
    }

    def "return-a-list-of-six-starting-tiles"(){
        def unplayedTiles2 = new UnplayedTiles();
        expect:
        unplayedTiles2.drawStartingTiles().size() == 6
    }
}