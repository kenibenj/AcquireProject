package UserInterface;

import AcquireProject.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;

public class BuyStockMenu extends ActionMenu{

    public BuyStockMenu(Game game, GameUI ui){
        super(game, ui);
    }

    @Override
    public VBox getMenu() {
        return menu;
    }

    @Override
    public void updateMenu() {
        menu = new VBox();

        Text title = new Text("Buy stock");
        menu.getChildren().add(title);

        Text prompt = new Text("Stock left to buy:" + game.getNumberOfStockLeftToBuy());
        menu.getChildren().add(prompt);

        List<String> stocks = game.getAvailableStocks();

        for(int i = 0; i < stocks.size(); i++){

            int index = i;

            Button s = new Button(stocks.get(i));
            menu.getChildren().add(s);
            s.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    game.buyStock(index);
                    ui.updatePlayerInfo();
                    ui.changeActionMenu(GameUI.BUY_STOCK);
                }
            });

            if(!game.playerCanBuyStock(index)){
                s.setDisable(true);
            }

        }

        Button continueButton = new Button("Continue");
        menu.getChildren().add(continueButton);
        continueButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                game.goToNextPlayer();
                ui.changeActionMenu(GameUI.PLACE_TILE);
            }
        });
    }

}
