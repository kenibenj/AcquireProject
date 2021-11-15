/**
 * Founder class that handles the formation of hotel chains in the game
 *
 * @author Michael, Emily, Benjamin
 *
 * @since 1.0.0
 */

package AcquireProject;

import lombok.Getter;

import java.util.List;

public class Founder {
    private HotelChain foundedChain;
    @Getter private List<Tile> chainTiles;


    public Founder(List<Tile> tiles){
        chainTiles = tiles;
    }




}
