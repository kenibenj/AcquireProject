/**
 * @author Emily Elzinga
 * @version v0.1.0
 * @since 11/7/2021
 */


package AcquireProject

import spock.lang.Specification;

class GameBoardTest extends Specification {

    def matrix
    def chain
    def setup(){
        ArrayList<String> foundedChains = new ArrayList<String>()
        ArrayList<String> unfoundedChains = new ArrayList<String>()
        GameBoard gameboard = new GameBoard(
                unfoundedChains as List<HotelChain>, foundedChains as List<HotelChain>
        )
        matrix = gameboard.getBoardMatrix()
//        chain = new HotelChain();
    }

    def "board-specs"(){
        expect:
        matrix.size() == 12
        matrix.get(0).size() == 9
    }


    def "breadth-first-search-for-a-tiles-neighbors"(){
        given:
        matrix.get(7).set(4,"8E")
        matrix.get(6).set(5,"7F")
        matrix.size()

        ArrayList<Integer> pair = new ArrayList<>()
        pair.add(0,6)
        pair.add(1,4)
        Queue<List<Integer>> neighbors = new LinkedList<>();
        neighbors.add(pair)
        matrix.get(pair.get(0)).set(pair.get(1), "7E")
        boolean[][] visited = new boolean[matrix.size()][matrix.get(0).size()];


        when:
        int tally = chain.BreadthFirstSearch(matrix,neighbors,visited)

        then:
        tally == 3;

    }
}
