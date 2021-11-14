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

    private final int NUMBER_OF_STOCK = 25;
    private final int SAFE_SIZE = 11;

    private int tier;
    @Getter private List<Tile> tiles;
    private String name;
    private List<Stock> unownedStock;
    @Getter private List<Stock> ownedStock;

    public static final int TIER_ONE = 0;
    public static final int TIER_TWO = 1;
    public static final int TIER_THREE = 2;

    private static final int[] stockPrices = new int[]{200, 300, 400, 500, 600, 700, 800, 900, 1000, 1100, 1200};
    private static final int[][] stockBracket = new int[][]{new int[]{2, 2}, new int[]{3, 3}, new int[]{4, 4}, new int[]{5, 5}
            , new int[]{6, 10}, new int[]{11, 20}, new int[]{21, 30}, new int[]{31, 40}, new int[]{41, 42069}};



    public HotelChain(String name, int tier){
        this.name = name;
        this.tier = tier;
        this.tiles = new ArrayList<>();
        this.unownedStock = generateStock();
        this.ownedStock = new ArrayList<>();

        StockProfiler.instance().addChain(name);
    }

    public String getName(){return name;}
    public int getSize(){
        return 1;
    }

    public void addTile(Tile tile){
        tiles.add(tile);
    }

    public int getMajorityShareholderBonus(){
        return getStockPrice() * 10;
    }

    public int getMinorityShareholderBonus(){
        return getMajorityShareholderBonus() / 2;
    }
    public Player getMajorityShareholder(){
        return new Player("", new ArrayList<Tile>() {
        });
    }

    public Player getMinorityShareholder() {
        return new Player("", new ArrayList<>());
    }

    /**
     * tests if the chain is safe from being acquired
     *
     * @return if the chain can not be acquired
     */
    public Boolean isSafe(){
        if(getSize() >= SAFE_SIZE) {
            return true;
        }else{
            return false;
        }
    }

    /**
     * creates the inital stock for the hotel chain
     *
     * @return a list of stock objects the chain owns
     * @author Michael Collier
     */
    private List<Stock> generateStock(){
        List<Stock> stock = new ArrayList<>();

        for(int i = 0; i < NUMBER_OF_STOCK; i++){
            stock.add(new Stock(name));
        }

        return stock;
    }

    /**
     * calculates the price of one stock based on the size of the chain
     *
     * @return the price of stock in the chain
     * @author Michael Collier
     */
    public int getStockPrice(){
        int size = tiles.size();
        int index = 0;

        for(int i = 0; i < stockBracket.length; i++){
            int[] bracket = stockBracket[i];
            if(size >= bracket[0] && size <= bracket[1]){
                index = i;
                continue;
            }
        }

        index += tier;

        return stockPrices[index];
    }


    /**
     * sells a stock to a player and charges their account
     *
     * @param player the player to sell the stock to
     */
    public void sellStock(Player player){
        giveStock(player);
        player.modifyBalance(-getStockPrice());
    }

    /**
     * gives a stock to a player and marks all the stock as sold
     *
     * @param player the player to give the stock to
     *
     * @author Michael Collier
     */
    public void giveStock(Player player){
        Stock stock = unownedStock.get(0);
        unownedStock.remove(stock);
        ownedStock.add(stock);
        stock.setOwner(player);
        player.addStock(stock);
    }

    /**
     * buys a stock from a player
     *
     * @param player the player selling the stock back to the chain
     */
    public void buyStock(Player player){
        takeStock(player);
        player.modifyBalance(getStockPrice());
    }

    public void takeStock(Player player){
        Stock stock = null;
        for(Stock s : ownedStock){
            if(s.getOwner().equals(player)){
                stock = s;
                break;
            }
        }

        stock.setOwner(null);
        ownedStock.remove(stock);
        unownedStock.add(stock);

        player.removeStock(stock);
    }

    public int getNumberOfUnsoldStock(){
        return unownedStock.size();
    }
}
