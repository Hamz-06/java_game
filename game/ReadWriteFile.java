package game;

import level.Level1;
import level.Level2;
import level.Level3;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Mohammad, Iqbal, Mohammad.Iqbal.7@city.ac.uk
 * @version 1.0
 * @since Mar 2021
 * */
public class ReadWriteFile {

    private GameLev lev;
    /**
     * Saves the game
     *<p>
     *     This function saves the game. It first overwrites the current txt file and writes into it. It currently writes/saves
     *     the level , player health and bullet count.
     * @param  level passes the current level in order for it to be saved
     * @param  fileName passes the name of the file that is written into
     *
     */

    public static void savelev(GameLev level, String fileName) throws IOException {

        boolean append = false;
        FileWriter writer = null;

        try {
            writer = new FileWriter(fileName, append);
            writer.write(level.getLevel() +"," +
                    level.getPlayer().getHealth() + "," +
                    level.getPlayer().getBulletCount() + "," + "\n");
        }
        finally {
            if (writer != null) {
                writer.close();
            }
        }

    }
    /**
     * Loads the game file
     *<p>
     * Function first opens the file and converts it into a buffered reader allowing it to read the file line by line.
     * The line is then broken down into tokens which are stored as an array with the level, health count and bullet count.
     *
     * @param  game gets the game file
     * @param  fileName passes the name of the file that is written into
     *
     * @return level We then return the corresponding level that was in the text file
     */

    public static GameLev loadLevel(myGame game, String fileName) throws IOException {
        FileReader fr = null;
        BufferedReader reader = null;
        try{
            System.out.println("Read File");
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);
            String line = reader.readLine();
            String[] tokens = line.split(",");
            String loadLev = tokens[0];
            int healthCount = Integer.parseInt(tokens[1]);
            int bulletCount = Integer.parseInt(tokens[2]);



            GameLev level = null;
            if (loadLev.equals("Level1"))
                level = new Level1(game);

            else if (loadLev.equals("Level2"))
                level = new Level2(game);
            else if (loadLev.equals("Level3"))
                level = new Level3(game);

            return level;

        }finally {
            if (reader != null){
                reader.close();
            }
            if (fr != null){
                fr.close();
            }
        }
    }


}