/*
 * Created by Emily Elzinga
 *
 * 11/5/2021
 */

package AcquireProject;

import java.util.ArrayList;

public class Player {

    String player_name = "";
    int balance = 0;
    ArrayList<String> player_tiles = new ArrayList<>();
    ArrayList<Stock> player_stocks = new ArrayList<>();

    Player(String name) {
        player_name = name;
        int balance = 6000;
//        player_tiles.add(UnplayedTiles.drawTiles);
//        player_tiles.add(UnplayedTiles.drawTiles);
//        player_tiles.add(UnplayedTiles.drawTiles);
//        player_tiles.add(UnplayedTiles.drawTiles);
//        player_tiles.add(UnplayedTiles.drawTiles);
//        player_tiles.add(UnplayedTiles.drawTiles);
    }

    public String getPlayer_name() {
        return player_name;
    }

    public int getBalance() {
        return balance;
    }

    public void modifyBalance(int change){
        balance += change;
    }

    public void addTile(String tile){
        player_tiles.add(tile);
    }

    public ArrayList<String> getPlayer_tiles() {
        return player_tiles;
    }

    public ArrayList<Stock> getPlayer_stocks() {
        return player_stocks;
    }

    public void addStock(Stock stock){
        player_stocks.add(stock);
    }

    public void removeStock(Stock stock){
        player_stocks.remove(stock);
    }
}
