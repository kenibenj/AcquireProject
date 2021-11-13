/*
 * Created by Emily Elzinga
 *
 * 11/5/2021
 */

package AcquireProject;

import lombok.Getter;

import java.util.*;

public class Merger {
    @Getter private HotelChain acquiringChain;
    @Getter private HotelChain acquiredChain;

    private List<Player> playersToMakeDecision;

    Merger(HotelChain acquiringChain, HotelChain acquiredChain){
        this.acquiringChain = acquiringChain;
        this.acquiredChain = acquiredChain;
        this.playersToMakeDecision = findPlayers();
    }

    public void giveShareholderBonus(){

        List<Player> majority = getMajoritySharHolder();
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

    private List<Player> getMajoritySharHolder(){
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
                majority.clear();
                majority.add(p);
                maxStock = stockAmount;
            }else if(stockAmount == maxStock){
                majority.add(p);
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

    public Player getNextPlayer(){
        Player player = playersToMakeDecision.get(0);
        playersToMakeDecision.remove(player);
        return player;
    }
}
