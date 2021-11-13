/**
 * @author Emily Elzinga
 * @version v0.1.0
 * @since 11/7/2021
 */


package AcquireProject;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class HotelChain {
    private int tier;
    private List<Tile> tiles;
    @Getter private String name;

    private final int SAFE_SIZE = 11;

    public static final int TIER_ONE = 0;
    public static final int TIER_TWO = 1;
    public static final int TIER_THREE = 2;

    public HotelChain(String name, int tier){
        this.name = name;
        this.tier = tier;
        this.tiles = new ArrayList<>();
    }

  //  public String getName(){return name;}
    public int getSize(){
        return 1;
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
        return new Player("", new ArrayList<Tile>() {
        });
    }
    public Player getMinorityShareholder() {
        return new Player("", new ArrayList<>());
    }

    public Boolean isSafe(){
        return getSize() >= SAFE_SIZE;
    }
}
