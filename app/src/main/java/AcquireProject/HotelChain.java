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

    private int tier;
    @Getter private List<Tile> tiles;
    private String name;
    private List<Stock> unownedStock;
    private List<Stock> ownedStock;

    private final int SAFE_SIZE = 11;

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
        if(getSize() >= SAFE_SIZE) {
            return true;
        }else{
            return false;
        }
    }

    private List<Stock> generateStock(){
        List<Stock> stock = new ArrayList<>();

        for(int i = 0; i < NUMBER_OF_STOCK; i++){
            stock.add(new Stock(this));
        }

        return stock;
    }

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

    public void sellStock(Player player){
        giveStock(player);
        player.modifyBalance(-getStockPrice());
    }

    public void giveStock(Player player){
        Stock stock = unownedStock.get(0);
        unownedStock.remove(stock);
        ownedStock.add(stock);
        stock.setOwner(player);
        player.addStock(stock);
    }

    public int getNumberOfUnsoldStock(){
        return unownedStock.size();
    }
}
