/**
 * a singleton class that is a factory that makes maps called profiles.
 * profiles can link the number of stocks a player holds in each chain and the chain name
 * profiles can also link the number of stocks a player holds in a chain with the player who holds them
 *
 * @autor Michael Collier
 * @since 1.0.0
 */

package AcquireProject;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class StockProfiler {

    @Getter private static List<String> chains = new ArrayList<>();

    private static StockProfiler stockProfiler1;

    private StockProfiler(){}

    public static StockProfiler instance(){
        if(stockProfiler1 == null){
            return stockProfiler1 = new StockProfiler();
        }else {
            return stockProfiler1;
        }
    }

    /**
     * adds a chain to the list of chains in the system
     * @param name the name of the chian
     */
    public void addChain(String name){
        chains.add(name);
    }

    /**
     * Creates a map where keys are the names of the hotel chians in the system
     * and values are the amount of stock the given player owns in that chian
     *
     * @param player the player to create the profile for
     * @return a map of chains to the amount of stock owned
     */
    public Map<String, Integer> createPlayerProfile(@NotNull Player player){
        Map<String, Integer> profile = new HashMap<>();

        List<Stock> stocks = player.getPlayerStocks();

        for(String name : chains){
            profile.put(name, 0);
        }

        for(Stock stock : stocks){
            String name = stock.getChainName();
            profile.put(name, profile.get(name) + 1);
        }

        return profile;
    }

    /**
     * Creates a map where the keys are Players and the values are the amount of stock the player owns in the given chain
     *
     * @param chain the chain to create a profile for
     * @return a map of players to the amount of stock owned
     */
    public Map<Player, Integer> createChainProfile(@NotNull HotelChain chain){
        Map<Player, Integer> profile = new HashMap<>();

        List<Stock> stocks = chain.getOwnedStock();

        for(Stock s : stocks){
            Player player = s.getOwner();
            if(profile.containsKey(player)){
                profile.put(player, profile.get(player) + 1);
            }else{
                profile.put(player, 1);
            }
        }

        return profile;
    }


}
