package AcquireProject;

import lombok.Getter;
import lombok.Setter;

public class Stock {

    @Getter private HotelChain chain;
    @Getter @Setter private Player owner;

    public Stock(HotelChain chain){
        this.chain = chain;
    }

    public int getValue() {
        return chain.getStockPrice();
    }

}
