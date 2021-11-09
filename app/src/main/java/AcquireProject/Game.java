package AcquireProject;

import java.util.ArrayList;
import java.util.List;

/**
 * A facade class to control the game classes
 */
public class Game {

    public Game(){

    }

    /**
     * A method to switch the scene back to the main menu
     */
    public void returnToMainMenu(){

    }

    /**
     * Puts together a list of the players names
     *
     * @return a list of player names in turn order
     */
    public List<String> getPlayerNames(){
        List<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");
        return names;
    }

    /**
     * gets the balance for the current player
     *
     * @return an integer value for the balance of the player who's turn it is
     */
    public int getCurrentPlayerBalance(){
        return 2000;
    }

    /**
     * gets all players balances for displaying in the ui
     *
     * @return a list of player balances in turn order
     */
    public List<Integer> getPlayerBalances(){
        List<Integer> balances = new ArrayList<>();
        balances.add(1355);
        balances.add(2000);
        balances.add(4560);
        return balances;
    }

    /**
     * changes the balance of the current player by an amount.
     *
     * @param change the amount to be added if positive or removed if negative
     *
     * @return the new balance of the players account
     */
    public int modifyCurrentPlayerBalance(int change){
        return 200;
    }

    /**
     * gets a list of the tiles in a player's hand
     *
     * @return a list of strings representing the tiles a player can play
     */
    public List<String> getCurrentPlayerTiles(){
        List<String> tiles = new ArrayList<>();
        tiles.add("4A");
        tiles.add("7B");
        tiles.add("1C");
        tiles.add("7F");
        tiles.add("12D");
        tiles.add("5G");
        return tiles;
    }

    /**
     * adds a tile to the game board, removes the tile from the players hand and gives the player a new tile
     *
     * @param tile the tile to be placed
     */
    public void placeTile(String tile){

        addTileToCurrentPlayer();
    }

    /**
     * removes all tiles from players hand and gives the player six new tiles
     * should only be done when no tiles are playable from players hand
     */
    public void giveCurrentPlayerNewHand(){

    }

    /**
     * adds a tile to the current players hand from the list of available tiles
     */
    private void addTileToCurrentPlayer(){

    }

    /**
     * check to see if the tile can legally be placed on the game board
     *
     * @param tile that is trying to get placed
     *
     * @return if the tile can be placed legally
     */
    public Boolean moveIsLegal(String tile){
        return true;
    }

    /**
     * get the tiles that have already been placed
     * @return
     */
    public List<int[]> getPlayedTiles(){
        List<int[]> tiles = new ArrayList<>();
        tiles.add(new int[]{1, 3});
        tiles.add(new int[]{5, 6});
        tiles.add(new int[]{2, 2});
        tiles.add(new int[]{6, 1});
        return tiles;
    }

    /**
     * end game and complete scoring
     */
    public void endGame(){

    }



}
