/**
 * Stock class that holds methods and attributes relevant to stock objects
 *
 * @author Benjamin, Emily, Michael
 *
 * @since 1.0.0
 */

package AcquireProject;

import lombok.Getter;
import lombok.Setter;

public class Stock {

    @Getter private String chainName;
    @Getter @Setter private Player owner;

    /**
     * stores which chain and which player own each stock in the game
     *
     * @param chain the hotel chain that owns the stock
     */
    public Stock(String chain){
        this.chainName = chain;
    }


}
