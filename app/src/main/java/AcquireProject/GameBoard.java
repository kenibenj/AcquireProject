
/**
 * The game board class has the characteristics of a physical acquire game
 * board, with a matrix representing the physical board.
 *
 * @author Emily Elzinga
 * @version v0.1.0
 * @since 11/4/2021
 */

package AcquireProject;


import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.abs;


class GameBoard{


   private List<Tile> playedTiles;
   private ArrayList<ArrayList<Tile>> board;


   private List<HotelChain> unfoundedChains;
   private List<HotelChain> foundedChains;

   public ArrayList<ArrayList<Tile>> getGameBoardMatrix(){
      return board;
   }

   public List<Tile> getPlayedTiles() {
      return playedTiles;
   }

   public void addToPlayedTiles(Tile playedTile) {
      playedTiles.add(playedTile);
   }

   public void addToFoundedChains(HotelChain newChain) {
      foundedChains.add(newChain);
      unfoundedChains.removeIf(s -> s.equals(newChain));
   }

   /**
    * Initializes the game board. The board matrix will always have a height of 9 and a
    * length of 12, so it there are no parameters for it in the constructor.
    * @param unfoundedChains list of chains that haven't been founded yet
    * @param foundedChains list of chains that are founded
    */
   public GameBoard(List<HotelChain> unfoundedChains, List<HotelChain> foundedChains) {
      this.board = new ArrayList<>();
      for (int i = 0; i < 12; i++){
         ArrayList<Tile> y = new ArrayList<>();

         for (int j = 0; j < 9; j++){
            y.add(j, null);
         }
         board.add(i, y);
      }

      this.playedTiles = new ArrayList<>();
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
   public List<Tile> breadthFirstSearch(
           Queue<List<Integer>> queue,
           boolean[][] visited
   ) {
      ArrayList<Tile> neighborTiles = new ArrayList<>();
      int H = board.size();
      int L = board.get(0).size();

      while (!queue.isEmpty()) {
         List<Integer> x = queue.remove();
         int px = x.get(0);
         int py = x.get(1);


         if(px<0 || py<0 || px>=H || py>=L ||
                 visited[px][py] || board.get(px).get(py) == null)
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
    * Setup necessary for the breadthFirstSearch method
    * @param tile string representation of the newly laid tile
    * @return list of all of the newly laid tile's neighbors, including itself
    */
   private List<Tile> Scout(Tile tile){
      Queue<List<Integer>> neighbors = new LinkedList<>();
      neighbors.add(tile.getCoordinates());
      board.get(tile.getCoordinates().get(0)).set(tile.getCoordinates().get(1), tile);
      boolean[][] visited = new boolean[board.size()][board.get(0).size()];

      return breadthFirstSearch(neighbors,visited);
   }

   /**
    * Loops through the given tile clump list and cross-references the tile
    * chain name with elements of the founded chain list. When found, add the that chain to
    * the frequencyList
    * @param neighbors list of tiles in the clump
    * @return ordered list of hotel chains in the clump ordered in frequency
    */

   public List<HotelChain> modeInNeighborList(List<Tile> neighbors) {
      String chainName;

      Map<HotelChain, Integer> frequencyMap = new HashMap<>();
      for (int i = 0; i < neighbors.size(); i++) {
         chainName = neighbors.get(i).getChainName();
         for (HotelChain chain : foundedChains) {
            if (chainName.equals(chain.getName())){

               if (frequencyMap.get(chain) != null) {
                  int count = frequencyMap.get(chain);
                  count++;
                  frequencyMap.put(chain, count);
               } else {
                  frequencyMap.put(chain, 1);
               }
            }
         }

      }

      return frequencyMap.entrySet().stream()
              .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
              .map(Map.Entry::getKey)
              .collect(Collectors.toList());
   }




   public void placeTile(Tile tile){
      playedTiles.add(tile);
      List<Tile> chain = Scout(tile);
      List<HotelChain> modeChain = modeInNeighborList(chain);
      if(modeChain.size() == 1){
         Founder founder = new Founder();
         founder.foundChain();
      }

      else if (modeChain.size() > 1) {
         HotelChain mode = modeChain.remove(0);
         for (HotelChain aquiredChain : modeChain){
            Merger merger = mergeNeeded(mode, aquiredChain);
         }
      }

   }
   private void addChain(){

   }
   private Merger mergeNeeded(HotelChain acquiringChain,HotelChain acquiredChain){
      return new Merger(acquiringChain,acquiredChain);
   }
   //   private Founder foundNeeded(){
//      return new Founder();
//   }
   public boolean moveIsLegal(String tile){
      return true;
   }
}