package AcquireProject;

import lombok.Getter;

public class Stock {

    @Getter private HotelChain chain;
    @Getter private Player owner;

    public Stock(){
        //this.chain = chain;
    }

    public int getValue() {
        return 1;
    }
}
