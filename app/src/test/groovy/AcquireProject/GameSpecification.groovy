package AcquireProject

import spock.lang.Specification


class GameSpecification extends Specification {
    def game
    def gameboard
    def setup(){

        List<Tile> bobStartTiles = new ArrayList<>();
        List<Tile> fredStartTiles = new ArrayList<>();
        List<Tile> sampleTiles = new ArrayList<>();
        sampleTiles.add(new Tile(Arrays.asList(9,1)))
        sampleTiles.add(new Tile(Arrays.asList(9,2)))
        sampleTiles.add(new Tile(Arrays.asList(9,3)))
        sampleTiles.add(new Tile(Arrays.asList(9,4)))
        sampleTiles.add(new Tile(Arrays.asList(9,5)))
        sampleTiles.add(new Tile(Arrays.asList(9,6)))
        sampleTiles.add(new Tile(Arrays.asList(9,7)))
        sampleTiles.add(new Tile(Arrays.asList(9,8)))
        sampleTiles.add(new Tile(Arrays.asList(10,1)))
        sampleTiles.add(new Tile(Arrays.asList(10,2)))
        sampleTiles.add(new Tile(Arrays.asList(10,3)))
        sampleTiles.add(new Tile(Arrays.asList(10,4)))

        HotelChain tower = new HotelChain("Tower", HotelChain.TIER_THREE)
        HotelChain continental = new HotelChain("Continental", HotelChain.TIER_THREE)

        List<HotelChain> unfounded = new ArrayList<>();
        unfounded.add(tower)
        unfounded.add(continental)
        game = new Game(sampleTiles, unfounded)
        sampleTiles.removeAll()


        Player player1 = new Player("bob", bobStartTiles)
        Player player2 = new Player("fred", fredStartTiles)

        game.addPlayer("bob")
        game.addPlayer('fred')
        game.goToNextPlayer()

        game.placeTile(0)
        game.placeTile(0)
        game.placeTile(0)
        game.placeTile(0)
        game.placeTile(0)
        game.placeTile(0)

        gameboard = game.getGameBoard()
        gameboard.FoundChain("Tower", player1)
        game.buyStock(0)
        game.buyStock(0)
        game.buyStock(0)
        game.buyStock(0)

        game.goToNextPlayer()
        game.placeTile(0)
        game.placeTile(0)
        game.placeTile(0)
        game.placeTile(0)
        game.placeTile(0)
        game.placeTile(0)

        game.goToNextPlayer()

        game.getCurrentPlayer().addTile(Arrays.asList(new Tile(Arrays.asList(10,5))))
        game.getCurrentPlayer().addTile(Arrays.asList(new Tile(Arrays.asList(10,6))))
        game.getCurrentPlayer().addTile(Arrays.asList(new Tile(Arrays.asList(10,7))))
        game.getCurrentPlayer().addTile(Arrays.asList(new Tile(Arrays.asList(10,8))))
        game.getCurrentPlayer().addTile(Arrays.asList(new Tile(Arrays.asList(11,1))))
        game.getCurrentPlayer().addTile(Arrays.asList(new Tile(Arrays.asList(11,2))))

        game.placeTile(0)
        game.placeTile(0)
        game.placeTile(0)
        game.placeTile(0)
        game.placeTile(0)
        game.placeTile(0)

        game.goToNextPlayer()
        game.getCurrentPlayer().addTile(Arrays.asList(new Tile(Arrays.asList(11,3))))
        game.getCurrentPlayer().addTile(Arrays.asList(new Tile(Arrays.asList(11,4))))
        game.getCurrentPlayer().addTile(Arrays.asList(new Tile(Arrays.asList(11,5))))
        game.getCurrentPlayer().addTile(Arrays.asList(new Tile(Arrays.asList(11,6))))
        game.getCurrentPlayer().addTile(Arrays.asList(new Tile(Arrays.asList(11,7))))
        game.getCurrentPlayer().addTile(Arrays.asList(new Tile(Arrays.asList(11,8))))

        game.placeTile(0)
        game.placeTile(0)
        game.placeTile(0)
        game.placeTile(0)
        game.placeTile(0)
        game.placeTile(0)



        game.goToNextPlayer()


        game.getCurrentPlayer().addTile(Arrays.asList(new Tile(Arrays.asList(1,1))))
        game.getCurrentPlayer().addTile(Arrays.asList(new Tile(Arrays.asList(1,2))))
        game.getCurrentPlayer().addTile(Arrays.asList(new Tile(Arrays.asList(1,3))))
        game.getCurrentPlayer().addTile(Arrays.asList(new Tile(Arrays.asList(1,4))))
        game.getCurrentPlayer().addTile(Arrays.asList(new Tile(Arrays.asList(1,5))))
        game.getCurrentPlayer().addTile(Arrays.asList(new Tile(Arrays.asList(1,6))))
        game.getCurrentPlayer().addTile(Arrays.asList(new Tile(Arrays.asList(1,7))))

        game.placeTile(0)
        game.placeTile(0)
        game.placeTile(0)
        game.placeTile(0)
        game.placeTile(0)
        game.placeTile(0)
        game.placeTile(0)
        gameboard.FoundChain("Continental", player2)

        game.getCurrentPlayer().addTile(Arrays.asList(new Tile(Arrays.asList(1,8))))
        game.getCurrentPlayer().addTile(Arrays.asList(new Tile(Arrays.asList(2,8))))
        game.getCurrentPlayer().addTile(Arrays.asList(new Tile(Arrays.asList(2,7))))
        game.getCurrentPlayer().addTile(Arrays.asList(new Tile(Arrays.asList(2,6))))
        game.getCurrentPlayer().addTile(Arrays.asList(new Tile(Arrays.asList(2,5))))
        game.getCurrentPlayer().addTile(Arrays.asList(new Tile(Arrays.asList(2,4))))

        game.placeTile(0)
        game.placeTile(0)
        game.placeTile(0)
        game.placeTile(0)
        game.placeTile(0)
        game.placeTile(0)

        game.goToNextPlayer()
        game.buyStock(1)
        game.buyStock(1)
        game.buyStock(1)


    }

    /**
     * tests if there is one or more chains of more than 11 tiles
     */
    def "can-game-end"(){
        expect:
            game.gameCanEnd() == true;
    }


    def "end-game"(){
        when:
        game.endGame()
        then:
        game.getPlayers().get(0).getPlayerStocks().size() == 0
        game.getPlayers().get(0).getPlayerStocks().size() == 0
    }

    def "bob-has-a-bunch-of-tiles"(){

        game.getCurrentPlayer().addTile(Arrays.asList(new Tile(Arrays.asList(5,3))))
        game.getCurrentPlayer().addTile(Arrays.asList(new Tile(Arrays.asList(5,4))))
        game.getCurrentPlayer().addTile(Arrays.asList(new Tile(Arrays.asList(5,5))))
        game.getCurrentPlayer().addTile(Arrays.asList(new Tile(Arrays.asList(5,6))))

        expect:
        game.getCurrentPlayerTiles() == Arrays.asList("6D","6E","6F","6G")
    }

    def "fred cannot buy anymore stock-because-he-is-broke"(){
       int balance = game.getPlayers().get(0).getBalance()
        game.getPlayers().get(0).modifyBalance(-balance)
        expect:
        game.playerCanBuyStock(1) == false
    }
    def "fred cannot buy anymore stock because he has used his turns"(){
        game.buyStock(1)
        game.buyStock(1)
        game.buyStock(1)
        expect:
        game.playerCanBuyStock(1) == false;
    }

    def "Tower's and Continental's available Stocks"(){
        List<String> stockFormat = new ArrayList<>()
        stockFormat.add('Tower: $' + gameboard.getFoundedChains().get(0).getStockPrice())
        stockFormat.add('Continental: $' + gameboard.getFoundedChains().get(1).getStockPrice())

        expect:
        game.getAvailableStocks() == stockFormat
    }

    def "get the founded chains names"(){
        List<String> hotelNames = new ArrayList<>()
        hotelNames.add("Sackson")
        hotelNames.add("Festival")

        gameboard.addToUnfoundedChains(new HotelChain("Sackson", HotelChain.TIER_ONE))
        gameboard.addToUnfoundedChains(new HotelChain("Festival", HotelChain.TIER_TWO))

        expect:
        game.getUnfoundedChains() == hotelNames
    }
}