package AcquireProject;

import java.util.*;

/**
 * A facade class to control the game classes
 *
 * @author Michael
 *
 * @since 1.0.0
 */
public class Game {


    private Queue<Player> players;
    private GameBoard gameBoard;
    private Player currentPlayer;
    private UnplayedTiles unplayedTiles;

    public Game(){

        this.players = new LinkedList<>();

        this.unplayedTiles = new UnplayedTiles();

        List<HotelChain> hotelChains = new ArrayList<>();
        hotelChains.add(new HotelChain("Worldwide", HotelChain.TIER_ONE));
        hotelChains.add(new HotelChain("Sackson", HotelChain.TIER_ONE));
        hotelChains.add(new HotelChain("Festival", HotelChain.TIER_TWO));
        hotelChains.add(new HotelChain("Imperial", HotelChain.TIER_TWO));
        hotelChains.add(new HotelChain("American", HotelChain.TIER_TWO));
        hotelChains.add(new HotelChain("Continental", HotelChain.TIER_THREE));
        hotelChains.add(new HotelChain("Tower", HotelChain.TIER_THREE));
        this.gameBoard = new GameBoard(hotelChains);

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
        for(Player p : players){
            names.add(p.getPlayerName());
        }
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
        for(Player p : players){
            balances.add(p.getBalance());
        }
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

        if(currentPlayer.equals(null)){
            return new ArrayList<>();
        }

        List<String> tileNames = new ArrayList<>();

        List<Tile> currentPlayerTiles = currentPlayer.getPlayerTiles();

        for(Tile t : currentPlayerTiles){
            tileNames.add(t.getTileName());
        }

        return tileNames;
    }

    /**
     * adds a tile to the game board, removes the tile from the players hand and gives the player a new tile
     *
     * @param tileIndex the index in the players list of tiles that should be placed
     */
    public void placeTile(int tileIndex){

        gameBoard.placeTile(currentPlayer.getPlayerTiles().get(tileIndex));
        currentPlayer.getPlayerTiles().remove(tileIndex);

        //addTileToCurrentPlayer();

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
    public void addTileToCurrentPlayer(){
        Tile givenTile = unplayedTiles.drawTile();
        currentPlayer.addTile(givenTile);
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
    public List<Tile> getPlayedTiles(){
        return gameBoard.getPlayedTiles();
    }

    /**
     * end game and complete scoring
     */
    public void endGame(){

    }

    /**
     * a method for getting the names of the unfounded hotel chains
     *
     * @return a list of names for the unfounded hotel chains.
     */
    public List<String> getUnfoundedChains(){
        List<String> names = new ArrayList<>();
        List<HotelChain> chains = gameBoard.getUnfoundedChains();
        for(HotelChain c : chains){
            names.add(c.getName());
        }
        return names;
    }

    /**
     * a method to find how many stock a player is still allowed to buy on their turn
     *
     * @return the number of stock a player can still buy this turn
     */
    public int getNumberOfStockLeftToBuy(){
        return 3;
    }

    /**
     * a method to find the stocks available stocks and their prices, should be formatted as "HotelChain $price"
     *
     * @return a list of stings describing the available stocks and their prices
     */
    public List<String> getAvailableStocks(){
        List<String> stocks = new ArrayList<>();
        for(HotelChain chain : gameBoard.getFoundedChains()){
            stocks.add(chain.getName() + ": $" + chain.getStockPrice());
        }
        return stocks;
    }

    /**
     * a method to get the name of the player who is currently deciding what to do with their stock during a merge
     *
     * @return the name of the player deciding their stock options
     */
    public String getMergingPlayerName(){
        return "Alice";
    }

    /**
     * a method to get how many stocks the player currently making stock options has in the acquired chain
     *
     * @return the number of stocks the player has in the acquired chain
     */
    public int getMergingPlayerStockAmount(){
        return 5;
    }

    /**
     * used to add a new player at the start of the game
     *
     * @param name the name of the new player
     */
    public void addPlayer(String name){
        this.players.add(new Player(name, unplayedTiles.drawStartingTiles()));
    }

    public void handleMerger(Merger merge){

    }

    public void goToNextPlayer(){
        if(this.players.size() > 0){
            if(Objects.isNull(this.currentPlayer)){
                currentPlayer = players.peek();
            }else{
                players.offer(players.poll());
                currentPlayer = players.peek();
            }



        }
    }

    public Founder foundNeeded(){
        return gameBoard.foundNeeded();
    }

    public void foundChain(String chain){
        gameBoard.FoundChain(chain);
    }


}
