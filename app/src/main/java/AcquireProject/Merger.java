/*
 * Created by Emily Elzinga
 *
 * 11/5/2021
 */

package AcquireProject;

import java.util.HashMap;

public class Merger {
    private HotelChain acquiringChain;
    private HotelChain acquiredChain;

    private int calculateShareholderBonus(){
        return 1;
    }

    Merger(HotelChain acquiringChain, HotelChain acquiredChain){
        this.acquiringChain = acquiringChain;
        this.acquiredChain = acquiringChain;
    }
}
