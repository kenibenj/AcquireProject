package AcquireProject

import spock.lang.Specification


class LoaderSpecification extends Specification {
    def game
    def loader
    def setup(){
        loader = new Loader();
    }
    /**
     * @result serialize a game object's data and save it in a text file
     */
    def "save-game"() {
        Game game = new Game();
        expect:
        loader.saveGame(game);
    }
//     def "load-game"(){
//         loader.get
//     }
}