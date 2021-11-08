/**
 * @author Emily Elzinga
 * @version v0.1.0
 * @since 11/7/2021
 */


package AcquireProject;

import java.util.List;

public class HotelChain {
    private int tier;
    private List<String> tiles;

    public int getSize(){
        return 1;
    }
    public void addTile(String tile){}
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
        return new Player();
    }
    public Player getMinorityShareholder() {
        return new Player();
    }
    public Boolean isSafe(){
        return true;
    }
}
