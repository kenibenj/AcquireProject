/*
 * Created by Emily Elzinga
 *
 * 11/5/2021
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


    public void foundChain(){

    }

}
