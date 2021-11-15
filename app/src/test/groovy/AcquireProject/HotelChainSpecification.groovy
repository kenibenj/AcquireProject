/**
 * @author Emily Elzinga
 * @version v0.1.0
 * @since 11/7/2021
 */

package AcquireProject

import spock.lang.Shared
import spock.lang.Specification

class HotelChainSpecification extends Specification {
    def chain
    def setup(){
        chain = new HotelChain("Tower", 2)
        chain.addTile(new Tile(Arrays.asList(6,3)))
        chain.addTile(new Tile(Arrays.asList(6,2)))
        chain.addTile(new Tile(Arrays.asList(6,4)))
        chain.addTile(new Tile(Arrays.asList(6,5)))
        chain.addTile(new Tile(Arrays.asList(7,4)))
        chain.addTile(new Tile(Arrays.asList(7,5)))
        chain.addTile(new Tile(Arrays.asList(7,6)))

    }

    def "stock-price-for-tower"(){
        expect:
        chain.getStockPrice() == 800;
    }
}
