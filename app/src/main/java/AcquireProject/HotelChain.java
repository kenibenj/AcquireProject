/**
 * @author Emily Elzinga
 * @version v0.1.0
 * @since 11/7/2021
 */


package AcquireProject;

import java.util.ArrayList;
import java.util.List;

public class HotelChain {
    private int tier;
    private List<Tile> tiles;
    private String name;

    public HotelChain(int tier, String name) {
        this.tier = tier;
        this.tiles = new ArrayList<>();
        this.name = name;
    }
    public String getName(){return name;}
    public List<Tile> getTiles(){ return tiles;}
    public void addTile(Tile tile){
        tiles.add(tile);
    }
    public int getStockPrice(){
        return 1;
    }
    public int getMajorityShareholderBonus(){
        return 1;
    }
    public int getMinorityShareholderBonus(){
        return 1;
    }
    public Player getMajorityShareholder(){
        return new Player("");
    }
    public Player getMinorityShareholder() {
        return new Player("");
    }
    public Boolean isSafe(){
        return true;
    }
}
