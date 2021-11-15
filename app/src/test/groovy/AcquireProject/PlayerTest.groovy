package AcquireProject

import spock.lang.Specification

class PlayerTest extends Specification{
    def testPlayer
    def listOfTiles

    def setup(){
        testPlayer = new Player();
        def tile1 = new Tile(Arrays.asList(1,6))
        def tile2 = new Tile(Arrays.asList(2,5))
        def tile3 = new Tile(Arrays.asList(3,4))
        def tile4 = new Tile(Arrays.asList(4,3))
        def tile5 = new Tile(Arrays.asList(5,2))
        def tile6 = new Tile(Arrays.asList(6,1))

    }

    def "giving player a new hand of tiles"(){

    }
}