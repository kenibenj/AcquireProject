/**
 * MIT License
 *
 * Copyright (c) 2021 Michael Collier, Emily Elzinga, Benjamin Keninger
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * Gives the options for buying stock at the end of each turn
 *
 * @author Michael Collier
 *
 * @since 1.0.0
 */

package UserInterface;

import AcquireProject.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;

public class BuyStockMenu extends ActionMenu{

    public BuyStockMenu(GameUI ui){
        super(ui);
    }

    @Override
    public VBox getMenu() {
        return menu;
    }

    @Override
    public void updateMenu() {
        menu = new VBox();
        menu.getStyleClass().add("actionMenu");

        Text title = new Text("Buy stock");
        title.getStyleClass().add("mediumText");
        menu.getChildren().add(title);

        Text prompt = new Text("Stock left to buy: " + ui.getGame().getNumberOfStockLeftToBuy());
        menu.getChildren().add(prompt);

        List<String> stocks = ui.getGame().getAvailableStocks();


        for(int i = 0; i < stocks.size(); i++){

            int index = i;

            Button s = new Button(stocks.get(i));
            String name = stocks.get(i).split(":")[0];
            s.getStyleClass().add(name);
            menu.getChildren().add(s);
            s.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    ui.getGame().buyStock(index);
                    ui.updatePlayerInfo();
                    ui.changeActionMenu(GameUI.BUY_STOCK);
                }
            });

            if(!ui.getGame().playerCanBuyStock(index)){
                s.setDisable(true);
            }

        }

        Button continueButton = new Button("Continue");
        menu.getChildren().add(continueButton);
        continueButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ui.getGame().goToNextPlayer();
                ui.changeActionMenu(GameUI.PLACE_TILE);
            }
        });
    }

}
