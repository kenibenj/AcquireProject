/**
 * Tests functionality for the Gameboard class methods
 * @author Emily Elzinga
 * @version v0.1.0
 * @since 11/7/2021
 */


package AcquireProject

import spock.lang.Specification;

class GameBoardTest extends Specification {
    def gameboard
    def sackson;
    def american;
    def matrix
    /**
     *Basic variables needed to run each test
     */
    def setup(){
        ArrayList<HotelChain> foundedChains = new ArrayList<String>()

        sackson = new HotelChain("Sackson",1)
        american = new HotelChain("American",2)
        ArrayList<HotelChain> unfoundedChains = new ArrayList<String>()
        unfoundedChains.add(sackson)
        unfoundedChains.add(american)
        gameboard = new GameBoard(
                unfoundedChains as List<HotelChain>, foundedChains as List<HotelChain>,
        )
        matrix = gameboard.getGameBoardMatrix();
    }

    /**
     * Create a valid game board
     * @result a game board with length of 12 and height of 9
     */
    def "board-specs"(){
        expect:
        matrix.size() == 12
        matrix.get(0).size() == 9
    }


    /**
     * Search for the 7E tile's neighbors that are on the board already
     * @result A list containing the tiles 7E, 8E, and 7F
     */
    def "breadth-first-search-for-a-tiles-neighbors"(){
        given:
        def tile1 = new Tile(Arrays.asList(7,4));
        def tile2 = new Tile(Arrays.asList(6,5))
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
        def tile = new Tile(Arrays.asList(6,0));

        expect:
        gameboard.Scout(tile).size() == 1;
    }

    /**
     * Find the chains that appears with the greatest frequency in linear tile clump
     * @result should be the american chain, since that is the one with the most tiles
     */
   def "find-mode-in-linear-tile-clump"(){
       given:
       for (int i = 0; i<3; i++){
           Tile tile = new Tile( i + 4 + "E")
           tile.setChainName("Sackson")
           sackson.addTile(tile)
       }

       for (int i = 0; i< 4; i++){
           Tile tile = new Tile(i + 5 + "E")
           tile.setChainName("American")
           american.addTile(tile)
       }
       gameboard.addToFoundedChains(sackson);
       gameboard.addToFoundedChains(american);

       def clumpOfTiles = new ArrayList<Tile>()
       clumpOfTiles.addAll(sackson.getTiles())
       clumpOfTiles.addAll(american.getTiles())

       when:
       def modeList = gameboard.modeInNeighborList(clumpOfTiles)

       then:
       modeList.get(0).getName() == "American";
   }
    def "find-mode-in-corner-tile-clump-with-two-chains-of-the-same-size"(){
        given:
        for (int i = 0; i<3; i++){
            Tile tile = new Tile( i + 4 + "A")
            tile.setChainName("Sackson")
            sackson.addTile(tile)
        }

        for (int i = 0; i< 3; i++){
            Tile tile = new Tile(i + 5 + "A")
            tile.setChainName("American")
            american.addTile(tile)
        }
        gameboard.addToFoundedChains(sackson);
        gameboard.addToFoundedChains(american);

        def clumpOfTiles = new ArrayList<Tile>()
        clumpOfTiles.addAll(sackson.getTiles())
        clumpOfTiles.addAll(american.getTiles())

        when:
        def modeList = gameboard.modeInNeighborList(clumpOfTiles)

        then:
        modeList.get(0).getTiles().size() == 3;
        modeList.get(1).getTiles().size() == 3;
    }

}
