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
    private List<String> playerTiles;
    private List<Stock> playerStocks;

    Player(String name) {
        this.playerName = name;
        this.balance = 6000;
        this.playerTiles = new ArrayList<>();
    }

    public void addTile(String tile){
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

    public List<String> getPlayer_tiles() {
        return playerTiles;
    }

    public List<Stock> getPlayer_stocks() {
        return playerStocks;
    }

    public void addStock(Stock stock){
        playerStocks.add(stock);
    }

    public void removeStock(Stock stock){
        playerStocks.remove(stock);
    }
}
