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
 * @author Emily Elzinga
 * @version 0.1.0
 * @since 11/12/2021
 * Reads from and writes to a text file containing all the game data.
 * This class is singleton since there should only ever be one loader in the game.
 */

package AcquireProject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Loader {
    private static Loader INSTANCE;

    public Loader getINSTANCE() {
        return INSTANCE;
    }

    /**
     * Constructor that forces singleton
     */
    public Loader (){
        synchronized(Loader.class){
            if(INSTANCE != null) throw new UnsupportedOperationException(
                    getClass() + " is singleton but constructor called more than once");
            INSTANCE = this;
        }
    }


    /**
     * Loads a game object from a text file.
     * @return game object
     */
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

    /**
     * Serializes a game object and saves it to a text file.
     * @param gameData game object
     * @throws IOException in case the file is not found or something is wrong with the data
     */
    public void saveGame(Game gameData) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Writer writer = new BufferedWriter((new OutputStreamWriter(
                new FileOutputStream("gameData.txt"), StandardCharsets.UTF_8)
        ));
        gson.toJson(gameData, writer);
        writer.close();
    }
}
