package AcquireProject

import spock.lang.Specification


class LoaderSpecification extends Specification {

    static final loader = new Loader()

    /**
     * @result serialize a game object's data and save it in a text file
     */
    def "save-game"() {

        Game game = new Game();
        expect:
        loader.getINSTANCE().saveGame(game);
    }



    /**
     *
     * @result game object from a json file
     */
     def "load-game"(){

         expect:
         loader.getINSTANCE().loadGame() instanceof Game
     }
}