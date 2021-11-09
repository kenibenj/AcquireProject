package AcquireProject;

import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class GameUI {

    @Getter private Scene scene;
    @Setter private Game game;

    private UserInterface ui;



    public GameUI(UserInterface ui, Game game){
        this.ui = ui;
        this.game = game;

        this.scene = makeScene();
    }

    private Scene makeScene(){
        BorderPane border = new BorderPane();

        Scene scene = new Scene(border, 800, 600);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("stylesheet.css").toExternalForm());

        Text title = new Text();
        title.setText("Acquire Game");

        border.setTop(title);

        border.setBottom(makePlayerInfo());

        return scene;
    }

    private HBox makePlayerInfo(){
        HBox playerInfo = new HBox();
        playerInfo.getStyleClass().add("hbox");
        //playerInfo.setStyle("-fx-background-color: #2f4f4f; -fx-padding: 15; -fx-spacing: 10;");

        List<String> playerNames = game.getPlayerNames();
        List<Integer> playerBalances = game.getPlayerBalances();

        for(int i = 0; i < playerNames.size(); i++){

            VBox info = new VBox();
            info.getStyleClass().add("infoBox");

            Text name = new Text(playerNames.get(i));
            Text balance = new Text("$" + playerBalances.get(i).toString());

            info.getChildren().add(name);
            info.getChildren().add(balance);

            playerInfo.getChildren().add(info);

        }


        return playerInfo;
    }

}
