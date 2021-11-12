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


    Player(String name, List<Tile> startingTiles) {
        this.playerTiles = startingTiles;
        this.playerName = name;
        this.balance = 6000;
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
