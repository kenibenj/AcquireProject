/**
 * @author Emily Elzinga
 * @version 0.1.0
 * @since 11/12/2021
 */

package AcquireProject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Loader {
    public Game loadGame(){
        Game savedGame = null;
        try {
            String json = String.join("\n", Files.readAllLines(Paths.get("gameData.txt")));
            Gson gson = new Gson();
            savedGame = gson.fromJson(json, Game.class);
        }
        catch (Exception ex){
            System.out.format("I/O error: %s%n", ex);
        }
        return savedGame;
    }

    public void saveGame(Game gameData) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Writer writer = new BufferedWriter((new OutputStreamWriter(
                new FileOutputStream("gameData.txt"), StandardCharsets.UTF_8)
        ));
        gson.toJson(gameData, writer);
        writer.close();
    }
}
