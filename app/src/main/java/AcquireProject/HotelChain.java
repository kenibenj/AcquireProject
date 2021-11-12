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

    private final int SAFE_SIZE = 11;

    public static final int TIER_ONE = 0;
    public static final int TIER_TWO = 1;
    public static final int TIER_THREE = 2;

    public HotelChain(String name, int tier){
        this.name = name;
        this.tier = tier;
        this.tiles = new ArrayList<>();
    }

    public String getName(){return name;}

    public int getSize(){
        return tiles.size();
    }

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
        if(getSize() >= SAFE_SIZE) {
            return true;
        }else{
            return false;
        }
    }
}
