package AcquireProject

import spock.lang.Specification


class LoaderSpecification extends Specification {
    def game;
    /**
     * @result serialize a game object's data and save it in a text file
     */
    def "save a game"(){
        game = new Game()
    }

}