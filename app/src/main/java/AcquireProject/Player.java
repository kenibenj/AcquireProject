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
<<<<<<< HEAD
        player_name = name;
        int balance = 6000;
//        player_tiles.add(UnplayedTiles.drawTiles);
//        player_tiles.add(UnplayedTiles.drawTiles);
//        player_tiles.add(UnplayedTiles.drawTiles);
//        player_tiles.add(UnplayedTiles.drawTiles);
//        player_tiles.add(UnplayedTiles.drawTiles);
//        player_tiles.add(UnplayedTiles.drawTiles);
=======
        this.playerName = name;
        this.balance = 6000;
        this.playerTiles = new ArrayList<>();
    }

    public void addTile(String tile){
        playerTiles.add(tile);
>>>>>>> a2cd3627087ec2df06a3bb989423f8391e5f4058
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

<<<<<<< HEAD
    public void addTile(String tile){
        player_tiles.add(tile);
    }

    public ArrayList<String> getPlayer_tiles() {
        return player_tiles;
=======
    public List<String> getPlayer_tiles() {
        return playerTiles;
>>>>>>> a2cd3627087ec2df06a3bb989423f8391e5f4058
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
