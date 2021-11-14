/**
 * A facade class to control the game classes
 *
 * @author Michael Collier
 *
 * @since 1.0.0
 */

package AcquireProject;

import UserInterface.GameUI;
import lombok.Getter;
import lombok.Setter;
import java.util.*;

public class Game {


    private Queue<Player> players;
    private GameBoard gameBoard;
    private Player currentPlayer;
    private int stockLeftToBuy;
    private UnplayedTiles unplayedTiles;

    @Getter @Setter private int UIState = GameUI.ADD_PLAYERS;

    /**
     * Constructor that creates list of hotel chains and their respective tiers
     */
    public Game(){

        stockLeftToBuy = 3;

        this.players = new LinkedList<>();

        this.unplayedTiles = new UnplayedTiles();

        List<HotelChain> hotelChains = makeHotelChains();
        this.gameBoard = new GameBoard(hotelChains);

    }

    /**
     * create each hotel chain with their name and tier
     *
     * @return a list of hotel chains
     */
    private List<HotelChain> makeHotelChains(){
        List<HotelChain> hotelChains = new ArrayList<>();
        hotelChains.add(new HotelChain("Worldwide", HotelChain.TIER_ONE));
        hotelChains.add(new HotelChain("Sackson", HotelChain.TIER_ONE));
        hotelChains.add(new HotelChain("Festival", HotelChain.TIER_TWO));
        hotelChains.add(new HotelChain("Imperial", HotelChain.TIER_TWO));
        hotelChains.add(new HotelChain("American", HotelChain.TIER_TWO));
        hotelChains.add(new HotelChain("Continental", HotelChain.TIER_THREE));
        hotelChains.add(new HotelChain("Tower", HotelChain.TIER_THREE));

        return hotelChains;
    }

    /**
     * A method to switch the scene back to the main menu
     */
    public void returnToMainMenu(){

    }

    /**
     * tests if the conditions are met for the game to end
     *
     * @return true if the game can end
     */
    public boolean gameCanEnd(){
        boolean allChainsSafe = true;

        boolean thereIsALargeChain = false;

        for(HotelChain chain : gameBoard.getFoundedChains()){
            if(!chain.isSafe()){
                allChainsSafe = false;
                break;
            }
            if(chain.getSize() >= 41){
                thereIsALargeChain = true;
            }
        }

        return (allChainsSafe || thereIsALargeChain) && (gameBoard.getFoundedChains().size() > 0);

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
     * creates a profile for each player and puts them in a list
     *
     * @return a list of maps describing the amount of stock a player has in each chain
     */
    public List<Map<String, Integer>> getPlayerStockProfiles(){
        List<Map<String, Integer>> profiles = new ArrayList<>();
        for(Player p: players){
            profiles.add(StockProfiler.instance().createPlayerProfile(p));
        }
        return profiles;
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

    }

    /**
     * removes all tiles from players hand and gives the player six new tiles
     * should only be done when no tiles are playable from players hand
     */
    public void giveCurrentPlayerNewHand(){

        List<Tile> newHand = new ArrayList<>();
        for(int i = 0; i < 6; i++){
            newHand.addAll(unplayedTiles.drawTile());
        }

        currentPlayer.giveNewHand(newHand);

    }


    /**
     * adds a tile to the current players hand from the list of available tiles
     */
    public void addTileToCurrentPlayer(){
        List<Tile> givenTile = unplayedTiles.drawTile();
        currentPlayer.addTile(givenTile);
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

        for(HotelChain chain : gameBoard.getFoundedChains()){
            Map<Player, Integer> profile = StockProfiler.instance().createChainProfile(chain);
            for(Player player : profile.keySet()){
                for(int i = 0; i < profile.get(player); i++){
                    chain.buyStock(player);
                }
            }
        }

    }

    /**
     * creates a message describing the winner of the game
     *
     * @return a string in the form "Player is the winner with $cash"
     */
    public String getWinner(){
        String message = "";

        int maxCash = 0;
        Player winner = currentPlayer;

        for(Player player : players){
            if(player.getBalance() > maxCash){
                maxCash = player.getBalance();
                winner = player;
            }
        }

        message += winner.getPlayerName();
        message += " is the winner with $";
        message += winner.getBalance();

        return message;
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
        return stockLeftToBuy;
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
     * adds one stock of the given chain to the current player and charges their account
     *
     * @param chainIndex the number for the chain to buy stock in
     */
    public void buyStock(int chainIndex){
        stockLeftToBuy--;
        HotelChain chain = gameBoard.getFoundedChains().get(chainIndex);
        chain.sellStock(currentPlayer);
    }

    /**
     * checks to see if a player can buy stock in the given chain
     * checks if the player has enough money, they have bought less than three stock this turn, and if the chain still has stock to sell
     *
     * @param chainIndex the chain the player might be able to buy from
     *
     * @return true if the player can buy the stock
     */
    public Boolean playerCanBuyStock(int chainIndex){
        HotelChain chain = gameBoard.getFoundedChains().get(chainIndex);
        if(currentPlayer.getBalance() < chain.getStockPrice()){
            return false;
        }
        if(chain.getNumberOfUnsoldStock() <= 0){
            return false;
        }
        if(stockLeftToBuy <= 0){
            return false;
        }
        return true;
    }

    /**
     * used to add a new player at the start of the game
     *
     * @param name the name of the new player
     */
    public void addPlayer(String name){
        this.players.add(new Player(name, unplayedTiles.drawStartingTiles()));
    }

    /**
     * updates starts the next players turn and makes them the current player
     */
    public void goToNextPlayer(){
        if(this.players.size() > 0){
            if(Objects.isNull(this.currentPlayer)){
                currentPlayer = players.peek();
            }else{
                players.offer(players.poll());
                currentPlayer = players.peek();
            }

            stockLeftToBuy = 3;

        }
    }

    /**
     * checks if there is a founding that needs to be handled
     *
     * @return true if there is a founder waiting to be handled
     */
    public Founder foundNeeded(){
        return gameBoard.foundNeeded();
    }

    /**
     * a method that determines if a merge is needed between 2 chains when a tile is played
     *
     * @return boolen value on if a merge is needed
     */
    public Boolean mergeNeeded(){
        return gameBoard.mergeNeeded();
    }

    /**
     * gets the next merger to be handled
     *
     * @return a Merger object to be handled
     */
    public Merger getCurrentMerger(){
        return gameBoard.getCurrentMerger();
    }

    /**
     * a method that founds a chain when tiles are placed next to each other
     *
     * @param chain object that is being formed
     */
    public void foundChain(String chain){
        gameBoard.FoundChain(chain, currentPlayer);
    }

    /**
     * check to see if the tile can legally be placed on the game board
     *
     * @param tileIndex the index for the tile being placed from the list of the current players hand
     *
     * @return if the tile can be placed legally
     */
    public boolean moveIsLegal(int tileIndex){
        return gameBoard.moveIsLegal(currentPlayer.getPlayerTiles().get(tileIndex));
    }


}
