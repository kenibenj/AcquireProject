package AcquireProject

import spock.lang.Specification


class UnplayedTilesSpecification extends Specification {

    def "size-check"(){
        def unplayedTiles0 = new UnplayedTiles();
        expect:
        unplayedTiles0.getTiles().size() == 108
    }

    def "return-a-tile-from-the-list"(){
        def unplayedTiles1 = new UnplayedTiles();
        expect:
        unplayedTiles1.drawTile() instanceof List<Tile>
    }

    def "return-a-list-of-six-starting-tiles"(){
        def unplayedTiles2 = new UnplayedTiles();
        expect:
        unplayedTiles2.drawStartingTiles().size() == 6
    }
}