package AcquireProject;

import java.util.*;

class GameBoard{

   private List<String> playedTiles;
   private ArrayList<ArrayList<String>> board;
   //private List<String> playedTiles;
   private List<HotelChain> unfoundedChains;
   private List<HotelChain> foundedChains;

   public GameBoard(List<HotelChain> unfoundedChains, List<HotelChain> foundedChains) {
      this.board = new ArrayList<>(12);
      for (int i = 0; i < 9; i++){
         board.add(i, new ArrayList<>(9));
      }

      this.unfoundedChains = unfoundedChains;
      this.foundedChains = foundedChains;
   }


   public void BreadthFirstSearch(Queue<List<Integer>> queue, boolean[][] visited) {

      int H = board.size();
      int L = board.get(0).size();

      while (!queue.isEmpty()) {
         List<Integer> x = queue.remove();
         int row = x.get(0);
         int col = x.get(1);


         if(row<0 || col<0 || row>=H || col>=L ||
                 visited[row][col] || board.get(row).get(col)==null)
            continue;

         visited[row][col]=true;
         queue.add(Arrays.asList(row,col-1)); //go left
         queue.add(Arrays.asList(row,col-1)); //go right
         queue.add(Arrays.asList(row,col-1)); //go up
         queue.add(Arrays.asList(row,col-1)); //go down
      }
   }

   private int Scout(List<Integer> pair){
      int h = board.size();
      int l = board.get(0).size();
      Queue<List<Integer>> neighbors = new LinkedList<>();

      boolean[][] visited = new boolean[board.size()][board.get(0).size()];

      for (int i = 0; i < h; i++) {
         for (int j = 0; j < l; j++) {
            visited[i][j] = false;
         }
      }

      for (int i = 0; i < h; i++) {
         for (int j = 0; j < l; j++) {
            if (!visited[i][j] && board.get(i).get(j) != null) {
               neighbors.add(pair);
               BreadthFirstSearch(neighbors, visited);
            }
         }
      }
      return 0;
   }


   public List<Integer> tileToCoords(String tile){
      List<Character> y_coords = Arrays.asList('A','B','C','D','E','F','G','H','I');

      int firstCoord = Character.getNumericValue(tile.charAt(0));
      int secondCoord = y_coords.indexOf(tile.charAt(1));


      board.get(firstCoord).set(secondCoord, tile);
      return Arrays.asList(firstCoord, secondCoord);
   }

   public void placeTile(String tile){
      List<Integer> pair = tileToCoords(tile);

      if(Scout(pair) == 1){
         Founder founder = foundNeeded();
         founder.foundChain();

      }
      else if (Scout(pair) > 1) {
         Merger merger = mergeNeeded();
      }

   }
   private void addChain(){

   }
   private Merger mergeNeeded(){
      return new Merger();
   }
   private Founder foundNeeded(){


      return new Founder(playedTiles, new HotelChain()); //THIS IS A PLACEHOLDER,
   }
   public boolean moveIsLegal(String tile){
      return true;
   }
}