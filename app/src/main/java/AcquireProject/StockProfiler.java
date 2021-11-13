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

    public Map<String, Integer> createProfile(Player player){
        Map<String, Integer> profile = new HashMap<>();

        List<Stock> stocks = player.getPlayerStocks();

        for(String name : chains){
            profile.put(name, 0);
        }

        for(Stock stock : stocks){
            String name = stock.getChain();
            profile.put(name, profile.get(name) + 1);
        }

        return profile;
    }


}
