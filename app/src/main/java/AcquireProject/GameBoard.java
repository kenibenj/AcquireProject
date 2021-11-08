
/**
 * The HotelChain class consolidates all characteristics of a hotel chain,
 * including the tier and the tiles it has.
 *
 * @author Emily Elzinga
 * @version v0.1.0
 * @since 11/4/2021
 */

package AcquireProject;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

import static java.lang.Math.abs;


class GameBoard{

   private List<String> playedTiles;
   private ArrayList<ArrayList<String>> board;

   private List<HotelChain> unfoundedChains;
   private List<HotelChain> foundedChains;

   /**
    * Initializes the game board. The board matrix will always have a height of 9 and a
    * length of 12, so it there are no parameters for it in the constructor.
    * @param unfoundedChains list of chains that haven't been founded yet
    * @param foundedChains list of chains that are founded
    */
   public GameBoard(List<HotelChain> unfoundedChains, List<HotelChain> foundedChains) {
      this.board = new ArrayList<>();
      for (int i = 0; i < 12; i++){
         ArrayList<String> y = new ArrayList<>();

         for (int j = 0; j < 9; j++){
            y.add(j, "");
         }
         board.add(i, y);
      }

      this.unfoundedChains = unfoundedChains;
      this.foundedChains = foundedChains;
   }

   /**
    * This method uses a breadth-first-search algorithm to count the number of tiles
    * part of a one that has just been laid. Because the new tile is part of the hotel chain,
    * the method counts it as well.
    *
    * @param queue List of nodes for possible neighbors for the tile just laid
    * @param visited Boolean matrix that has a bool value of whether that node has been visited
    * @return the newly laid tile's neighbors.
    */
   public List<String> breadthFirstSearch(
           Queue<List<Integer>> queue,
           boolean[][] visited
   ) {
      ArrayList<String> neighborTiles = new ArrayList<>();
      int H = board.size();
      int L = board.get(0).size();

      while (!queue.isEmpty()) {
         List<Integer> x = queue.remove();
         int px = x.get(0);
         int py = x.get(1);


         if(px<0 || py<0 || px>=H || py>=L ||
                 visited[px][py] || board.get(px).get(py).equals(""))
            continue;

         visited[px][py]=true;
         neighborTiles.add(board.get(px).get(py));
         queue.add(Arrays.asList(px + 1,py)); //go right
         queue.add(Arrays.asList(px - 1,py)); //go left
         queue.add(Arrays.asList(px,py - 1)); //go up
         queue.add(Arrays.asList(px,py + 1)); //go down
      }

      return neighborTiles;
   }

   /**
    * Converts the string coordinate of a tile to the x-y coordinates used by the game board.
    * @param tile string representation of the tile
    * @return x-y coordinates of the tile
    */
   public List<Integer> tileToCoords(String tile){
      List<Character> y_coords = Arrays.asList('A','B','C','D','E','F','G','H','I');

      int firstCoord = Character.getNumericValue(tile.charAt(0))-1;
      int secondCoord = y_coords.indexOf(tile.charAt(1));

      board.get(firstCoord).set(secondCoord, tile);
      return Arrays.asList(firstCoord, secondCoord);
   }

   /**
    * Setup necessary for the breadth first search method
    * @param tile string representation of the newly laid tile
    * @return list of all of the newly laid tile's neighbors, including itself
    */
   private List<String> Scout(String tile){
      List<Integer>  pair = tileToCoords(tile);
      Queue<List<Integer>> neighbors = new LinkedList<>();
      neighbors.add(pair);
      board.get(pair.get(0)).set(pair.get(1), tile);
      boolean[][] visited = new boolean[board.size()][board.get(0).size()];

      return breadthFirstSearch(neighbors,visited);
   }

   /**
    * compare difference of the newly laid tile and its neighbor tiles'
    * x-y coordinates to test whether they are next to each other.
    * @param chain comparison tiles
    * @param newTile newly laid tile
    * @return if more than one of the neighbors is the next tile over, return true
    */
   public boolean findDistance(List<String> chain, String newTile){
      List<Integer> nCoords = tileToCoords(newTile);
      int tally = 0;
      boolean vertical;
      boolean horizontal;

      for (String t : chain) {
         List<Integer> cCoords = tileToCoords(t);
         vertical = ((cCoords.get(0) - nCoords.get(0)) == 0 &&
                 abs(cCoords.get(1) - nCoords.get(1)) == 1);
         horizontal = ((cCoords.get(1) - nCoords.get(1)) == 0 &&
                 abs(cCoords.get(0) - nCoords.get(0)) == 1);
         if (vertical || horizontal) tally++;

      }
      return tally > 1;
   }

   public void placeTile(String tile){
      List<String> chain = Scout(tile);

      if(chain.size() == 2){
         Founder founder = new Founder(chain,);
         founder.foundChain();
      }
      else if (chain.size() > 2 && findDistance(chain,tile)) {
         Merger merger = mergeNeeded();
      }

   }
   private void addChain(){

   }
   private Merger mergeNeeded(){
      return new Merger();
   }
   //   private Founder foundNeeded(){
//      return new Founder();
//   }
   public boolean moveIsLegal(String tile){
      return true;
   }
}