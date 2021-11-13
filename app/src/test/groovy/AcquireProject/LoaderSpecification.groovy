package AcquireProject

import spock.lang.Specification


class LoaderSpecification extends Specification {

    /**
     * @result serialize a game object's data and save it in a text file
     */
    def "save-game"() {
//        def loader = new Loader()
//        Game game = new Game();
//        expect:
//        loader.saveGame(game);
    }

    /**
     *
     * @result game object from a json file
     */
     def "load-game"(){
         def loader = new Loader()
         expect:
         loader.loadGame() instanceof Game
     }
}