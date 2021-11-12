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

    Player(String name, List<Tile> sartingTiles) {
        playerName = name;
        int balance = 6000;
        playerTiles.add(Game.unplayedTiles.drawTile());
        playerTiles.add(Game.unplayedTiles.drawTile());
        playerTiles.add(Game.unplayedTiles.drawTile());
        playerTiles.add(Game.unplayedTiles.drawTile());
        playerTiles.add(Game.unplayedTiles.drawTile());
        playerTiles.add(Game.unplayedTiles.drawTile());
        this.playerName = name;
        this.balance = 6000;
        this.playerTiles = new ArrayList<>();
    }


    public void addTile(Tile tile){
        playerTiles.add(tile);
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
