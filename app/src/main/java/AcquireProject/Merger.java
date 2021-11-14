/**
 * Application class that runs the main method of the program
 *
 * @author Benjamin, Emily, Michael
 *
 * @since 1.0.0
 */


package AcquireProject;

import lombok.Getter;

import java.util.*;

public class Merger {
    @Getter private HotelChain acquiringChain;
    @Getter private HotelChain acquiredChain;
    private GameBoard gameBoard;

    private List<Player> playersToMakeDecision;

    Merger(HotelChain acquiringChain, HotelChain acquiredChain, GameBoard gameBoard){
        this.acquiringChain = acquiringChain;
        this.acquiredChain = acquiredChain;
        this.playersToMakeDecision = findPlayers();
        this.gameBoard = gameBoard;
    }

    public void giveShareholderBonus(){

        List<Player> majority = getMajorityShareHolder();
        int majorityBonus = acquiredChain.getMajorityShareholderBonus();
        for(Player p : majority){
            p.modifyBalance(majorityBonus / majority.size());
        }
        List<Player> minority = getMinorityShareHolder(majority);
        int minorityBonus = acquiredChain.getMinorityShareholderBonus();
        if(minority.size() == 0){
            for(Player p : majority){
                p.modifyBalance(minorityBonus / 100 / majority.size() * 100);
            }
        }else{
            for(Player p : minority){
                p.modifyBalance(minorityBonus / 100 / minority.size() * 100);
            }
        }

    }

    private List<Player> getMajorityShareHolder(){
        return getMinorityShareHolder(new ArrayList<Player>());
    }

    private List<Player> getMinorityShareHolder(List<Player> majority){
        List<Player> minority = new ArrayList<>();
        int maxStock = 1;

        Map<Player, Integer> profile = StockProfiler.instance().createChainProfile(acquiredChain);

        for(Player p : profile.keySet()){

            if(majority.contains(p)){
                continue;
            }

            int stockAmount = profile.get(p);
            if(stockAmount > maxStock){
                minority.clear();
                minority.add(p);
                maxStock = stockAmount;
            }else if(stockAmount == maxStock){
                minority.add(p);
            }
        }

        return minority;
    }

    private List<Player> findPlayers(){
        Set<Player> keys = StockProfiler.instance().createChainProfile(acquiredChain).keySet();
        List<Player> players = new ArrayList<>();

        for(Player p : keys){
            players.add(p);
        }

        return players;
    }

    public Boolean morePlayersToHandle(){
        return playersToMakeDecision.size() > 0;
    }

    public void goToNextPlayer(){
        playersToMakeDecision.remove(0);
    }

    public String getPlayerName(){
        return playersToMakeDecision.get(0).getPlayerName();
    }

    public int getPlayerStockCount(){
        return StockProfiler.instance().createPlayerProfile(playersToMakeDecision.get(0)).get(acquiredChain.getName());
    }

    public int getStockPrice(){
        return acquiredChain.getStockPrice();
    }

    public void sellStock(){
        acquiredChain.buyStock(playersToMakeDecision.get(0));
    }

    public void tradeStock(){
        Player player = playersToMakeDecision.get(0);
        acquiredChain.takeStock(player);
        acquiredChain.takeStock(player);
        acquiringChain.giveStock(player);
    }

    public void mergeChains(){
        gameBoard.mergeChains(this);
    }
}
