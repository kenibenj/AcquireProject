/**
 * MIT License
 *
 * Copyright (c) 2021 Michael Collier, Emily Elzinga, Benjamin Keninger
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * @author Emily Elzinga
 *
 * @version v0.1.0
 *
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
    private static final int[][] stockBracket = new int[][]{new int[]{0, 2}, new int[]{3, 3}, new int[]{4, 4}, new int[]{5, 5}
            , new int[]{6, 10}, new int[]{11, 20}, new int[]{21, 30}, new int[]{31, 40}, new int[]{41, 42069}};


    /**
     * Constructor that initializes HotelChain object with a name and its financial tier
     *
     * @param name the name of the HotelChain
     *
     * @param tier the tier of the Hotelchain that determines its cost and payout
     */
    public HotelChain(String name, int tier){
        this.name = name;
        this.tier = tier;
        this.tiles = new ArrayList<>();
        this.unownedStock = generateStock();
        this.ownedStock = new ArrayList<>();

        StockProfiler.instance().addChain(name);
    }

    /**
     * getter method that gets the name of HotelChain
     *
     * @return name of the HotelChain
     */
    public String getName(){
        return name;}

    /**
     * getter method that gets an integer amount of how many tiles are in the HotelChain
     *
     * @return integer size of the HotelChain
     */
    public int getSize(){
        return tiles.size();
    }

    /**
     * method that adds a tile to the list of Tile objects the HotelChain contains
     *
     * @param tile being added to HotelChain
     */
    public void addTile(Tile tile){
        tiles.add(tile);
    }

    /**
     * method that gets the majority shareholder bonus
     *
     * @return int value of the majority shareholder bonus
     */
    public int getMajorityShareholderBonus(){
        return getStockPrice() * 10;
    }

    /**
     * method that gets the minority shareholder bonus
     *
     * @return int value of the minority shareholder bonus
     */
    public int getMinorityShareholderBonus(){
        return getMajorityShareholderBonus() / 2;
    }

    /**
     * tests if the chain is safe from being acquired
     *
     * @return if the chain can not be acquired
     */
    public Boolean isSafe(){
        return tiles.size() >= SAFE_SIZE;
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
     *
     * @author Michael Collier
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
     *
     * @author Michael Collier
     */
    public void buyStock(Player player){
        takeStock(player);
        player.modifyBalance(getStockPrice());
    }

    /**
     * takes a stock from a player and lists it as unowned
     *
     * @param player the player to take the stock from
     *
     * @author Michael Collier
     */
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

    /**
     * gets the amount of stock yet to be sold to a player
     *
     * @return integer amount of unsold stock
     */
    public int getNumberOfUnsoldStock(){
        return unownedStock.size();
    }
}
