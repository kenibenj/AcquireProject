package AcquireProject;

import lombok.Getter;

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

    public void addChain(String name){
        chains.add(name);
    }

    public Map<String, Integer> createPlayerProfile(Player player){
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

    public Map<Player, Integer> createChainProfile(HotelChain chain){
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
