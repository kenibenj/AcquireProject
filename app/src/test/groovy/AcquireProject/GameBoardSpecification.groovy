/**
 * @author Emily Elzinga
 * @version v0.1.0
 * @since 11/7/2021
 */


package AcquireProject

import spock.lang.Specification;

class GameBoardTest extends Specification {
    def gameboard
    def matrix
    def chain
    def setup(){
        ArrayList<String> foundedChains = new ArrayList<String>()
        ArrayList<String> unfoundedChains = new ArrayList<String>()
        gameboard = new GameBoard(
                unfoundedChains as List<HotelChain>, foundedChains as List<HotelChain>,
        )
        matrix = gameboard.getGameBoardMatrix();
//        chain = new HotelChain();
    }

    def "board-specs"(){
        expect:
        matrix.size() == 12
        matrix.get(0).size() == 9
    }


    def "breadth-first-search-for-a-tiles-neighbors"(){
        given:
        def tile1 = new Tile("8E")
        def tile2 = new Tile("7F")
        tile1.setChainName("American")
        tile2.setChainName("American")
        matrix.get(7).set(4,tile1)
        matrix.get(6).set(5,tile2)
        gameboard.addToPlayedTiles(tile1)
        gameboard.addToPlayedTiles(tile2)

        matrix.size()

        ArrayList<Integer> pair = new ArrayList<>()
        pair.add(0,6)
        pair.add(1,4)
        Queue<List<Integer>> neighbors = new LinkedList<>();
        neighbors.add(pair)
        def tile3 = new Tile("7E")
        tile3.setChainName("American")
        matrix.get(pair.get(0)).set(pair.get(1), tile3)
        boolean[][] visited = new boolean[matrix.size()][matrix.get(0).size()];
        gameboard.addToPlayedTiles(tile3);

        when:
        List<Integer> tally = gameboard.breadthFirstSearch(neighbors,visited)

        then:
        tally.size() == 3;

    }

    def "search vars"(){
        def tileName = "7A"
        expect:
        gameboard.Scout(tileName).size() == 1;
    }

   def "find-mode-in-tile-clump"(){
       given:
       def clumpOfTiles = new ArrayList<Tile>();
   }
}
