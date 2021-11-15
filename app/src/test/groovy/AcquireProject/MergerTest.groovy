package AcquireProject

import com.google.common.collect.Lists
import spock.lang.Specification

class MergerTest extends Specification{

    HotelChain americanChain;
    HotelChain towerChain;
    Player steve;
    Player bob;
    GameBoard gameBoard;
    Merger merger;


    def setup(){
        americanChain = new HotelChain("American", HotelChain.TIER_TWO);
        towerChain = new HotelChain("Tower", HotelChain.TIER_THREE);

        def unfoundedChains = new ArrayList<HotelChain>();

        americanChain.addTile(new Tile(Lists.asList(0,0)));
        americanChain.addTile(new Tile(Lists.asList(0,1)));
        americanChain.addTile(new Tile(Lists.asList(0,2)));
        americanChain.addTile(new Tile(Lists.asList(0,3)));

        gameBoard = new GameBoard(unfoundedChains);

        steve = new Player("Steve", new ArrayList<Tile>());
        americanChain.sellStock(steve);
        americanChain.sellStock(steve);

        bob = new Player("Bob", new ArrayList<Tile>());

        merger = new Merger(towerChain, americanChain, gameBoard);

    }

    def "test stock price"(){
        expect:
        americanChain.getStockPrice() == 500;
    }

    def "test trading stock removes two stocks and adds one"(){
        given:
        merger.tradeStock()

        expect:
        steve.getPlayerStocks().size() == 1;
    }

    def "test checking for more players when there are more players"(){
        expect:
        merger.morePlayersToHandle()
    }

    def "test checking for more players when there are not more players"(){
        given:
        merger.goToNextPlayer();

        expect:
        !merger.morePlayersToHandle()
    }

}
