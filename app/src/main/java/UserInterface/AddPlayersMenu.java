package UserInterface;

import AcquireProject.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AddPlayersMenu extends ActionMenu{

    private int numberOfPlayers;
    private static final int MAX_PLAYERS = 6;

    public AddPlayersMenu(Game game, GameUI ui){
        super(game, ui);
        numberOfPlayers = 0;
        createMenu();
    }

    @Override
    public void updateMenu() {

    }

    private void createMenu(){
        this.menu = new VBox();

        Text title = new Text("Add Players");
        menu.getChildren().add(title);

        Text prompt = new Text("Enter the name of the next Player");
        menu.getChildren().add(prompt);

        TextField name = new TextField();
        menu.getChildren().add(name);

        Button continueButton = new Button("Add next player");
        menu.getChildren().add(continueButton);
        continueButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!name.getCharacters().isEmpty()) {
                    game.addPlayer(name.getCharacters().toString());
                    name.clear();
                    numberOfPlayers++;
                    if (numberOfPlayers >= MAX_PLAYERS - 1) {
                        continueButton.setDisable(true);
                    }
                }
            }
        });

        Button finishButton = new Button("Start the game");
        menu.getChildren().add(finishButton);
        finishButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(!name.getCharacters().isEmpty()){
                    game.addPlayer(name.getCharacters().toString());
                    name.clear();
                    numberOfPlayers++;
                    if (numberOfPlayers >= MAX_PLAYERS - 1) {
                        continueButton.setDisable(true);
                    }
                }

                ui.updatePlayerInfo();
                ui.changeActionMenu(ui.PLACE_TILE);
            }
        });
    }

}
