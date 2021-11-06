package AcquireProject;

import java.util.List;
import java.util.Vector;

class GameBoard{
   private List<String> playedTiles;

   public void placeTile(String tile){

   }
   private void addChain(){

   }
   private Merger mergeNeeded(){
      return new Merger();
   }
   private Founder foundNeeded(){
      return new Founder();
   }
   public boolean moveIsLegal(String tile){
      return true;
   }
   private int[] tileToCoords(String tile){
      return new int[]{0, 0};
   }
}