package AcquireProject

import spock.lang.Specification

class MergerSpecification extends Specification{
    def sackson
    def american
    def gameboard
    def merger
    def setup(){
        ArrayList<HotelChain> foundedChains = new ArrayList<String>()
        ArrayList<HotelChain> unfoundedChains = new ArrayList<String>()

        sackson = new HotelChain("Sackson",1)
        american = new HotelChain("American",2)
        for (int i = 4; i<9; i++){
            Tile tile = new Tile( Arrays.asList(i,4))
            tile.setChainName("Sackson")
            sackson.addTile(tile)
        }

        for (int i = 5; i< 9; i++){
            Tile tile = new Tile(Arrays.asList(i,4))
            tile.setChainName("American")
            american.addTile(tile)
        }

        gameboard = new GameBoard(
                unfoundedChains as List<HotelChain>, foundedChains as List<HotelChain>,
        )
        merger = new Merger(sackson,american,gameboard,);
    }

    def "new-erjeirj"(){
        def t1 = Arrays.asList(1,2);
        def t2 = Arrays.asList(3,5);
        def t3 = Arrays.asList(7,7);
        def t4 = Arrays.asList(4,7);
        def t5 = Arrays.asList(9,6);
        def t6 = Arrays.asList(1,4);

        def t7 = Arrays.asList(3,6);
        def t8 = Arrays.asList(3,3);
        def t9 = Arrays.asList(6,1);
        def t10 = Arrays.asList(8,1);
        def t11 = Arrays.asList(6,5);
        def t12 = Arrays.asList(7,4);
        def s1 = new Stock("Worldwide")
        def s2 = new Stock("Worldwide")
        def s3 = new Stock("Worldwide")
        def s4 = new Stock("Worldwide")
        def s5 = new Stock("Worldwide")
        def s6 = new Stock("Worldwide")
        def s7 = new Stock("Worldwide")
        def p1 = new Player("bob",Arrays.asList(t1,t2,t3,t4,t5,t6))
        p1.addStock(s1)
        p1.addStock(s2)
        p1.addStock(s3)

        def p2 = new Player("fred",Arrays.asList(t7,t8,t9,t10,t11,t12))
        p2.addStock(s4)
        p2.addStock(s5)
        p2.addStock(s6)
        p2.addStock(s7)
        def playerlist = Arrays.asList(p1,p2)


        def result = merger.getMajorityShareHolder()

        expect:
        result == 0

    }


}
