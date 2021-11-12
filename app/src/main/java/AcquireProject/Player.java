/*
 * Created by Emily Elzinga
 *
 * 11/5/2021
 */

package AcquireProject;

import java.util.ArrayList;
import java.util.List;

public class Player {


    private String playerName;
    private int balance;
    private List<Tile> playerTiles;
    private List<Stock> playerStocks;

    Player(String name) {
        playerName = name;
        int balance = 6000;
//        player_tiles.add(UnplayedTiles.drawTiles);
//        player_tiles.add(UnplayedTiles.drawTiles);
//        player_tiles.add(UnplayedTiles.drawTiles);
//        player_tiles.add(UnplayedTiles.drawTiles);
//        player_tiles.add(UnplayedTiles.drawTiles);
//        player_tiles.add(UnplayedTiles.drawTiles);
        this.playerName = name;
        this.balance = 6000;
        this.playerTiles = new ArrayList<>();
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getBalance() {
        return balance;
    }

    public void modifyBalance(int change){
        balance += change;
    }

    public void addTile(Tile tile){
        playerTiles.add(tile);
    }

    public List<Tile> getPlayerTiles() {
        return playerTiles;
    }

    public List<Stock> getPlayerStocks() {
        return playerStocks;
    }

    public void addStock(Stock stock){
        playerStocks.add(stock);
    }

    public void removeStock(Stock stock){
        playerStocks.remove(stock);
    }

}
