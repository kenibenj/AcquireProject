/*
 * Created by Emily Elzinga
 *
 * 11/5/2021
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
        return new Player("Alice");
    }
    public Player getMinorityShareholder() {
        return new Player("Alice");
    }
    public Boolean isSafe(){
        return true;
    }
}
