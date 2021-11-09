/*
 * Created by Emily Elzinga
 *
 * 11/5/2021
 */

package AcquireProject;

import java.util.ArrayList;

public class Player {


    private String playerName;
    private int balance;
    private ArrayList<String> playerTiles;
    private ArrayList<Stock> playerStocks;

    Player(String name) {
        playerName = name;
        int balance = 6000;
        playerTiles = new ArrayList<>();
    }

    public void addTile(String tile){
        playerTiles.add(tile);
    }

    public String getPlayer_name() {
        return playerName;
    }

    public int getBalance() {
        return balance;
    }

    public void modifyBalance(int change){
        balance += change;
    }

    public ArrayList<String> getPlayer_tiles() {
        return playerTiles;
    }

    public ArrayList<Stock> getPlayer_stocks() {
        return playerStocks;
    }

    public void addStock(Stock stock){
        playerStocks.add(stock);
    }

    public void removeStock(Stock stock){
        playerStocks.remove(stock);
    }
}
