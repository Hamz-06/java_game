package game;

import SoundFX.GameSound;

import controller.MouseInput;
import controller.keyController;
import intface.Gui;
import level.Level1;
import level.Level2;
import level.Level3;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class myGame {


    private GameLev level;



    private GameView view;
    private keyController controller;
    private MouseInput mouseInput;
    private GiveFocus giveFocus;
    public GameSound gameSound;
    private JFrame frame;
    private Gui gui;
    private int zoom;


    public myGame() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        //Creates level one
        level = new Level1(this);
        level.setGravity(52);
        view = new GameView(level, 800, 800);

        zoom =15;
        setZoom();  // standard zoom
        //Game level sound fx
        gameSound = new GameSound(level);
        gameSound.gameMusic();
     //Adds a frame
        frame = new JFrame("gme");
        frame.add(view);   //adds view to frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setResizable(false);
        frame.setVisible(true);

        frame.pack();


        controller = new keyController(level.getPlayer());  //changes controller
        view.addKeyListener(controller);  //adds key listner
        giveFocus = new GiveFocus(view);
        mouseInput = new MouseInput(level,view);
        view.addMouseListener(giveFocus);   //Adds listeners
        view.addMouseListener(mouseInput);
        showMenu();


        level.start();

    }

    public GameView getView() {
        return view;
    }

    private void showMenu(){    //Adds menu on the side

        gui = new Gui(level, this);
        frame.add(gui.getMainPanel(), BorderLayout.WEST);

        frame.pack();
        frame.setVisible(true);
    }

    public void nextLevel(){

        if (level instanceof Level1){   //level 1
            level.stop();

            level = new Level2(this);
            gameSound.updateLev(level);    //updates sound
            level.setGravity(52);   //changes gravity
            view.setWorld(level);
            view.updateLevel(level);    //updates level
            setZoom();   //updates zoom
            mouseInput.setLevel(level,view);   //updates mouse
            controller.updateStudent(level.getPlayer());   //updates the player
            level.start();


        }
        else if (level instanceof Level2){   //does this if on level 2
            level.stop();
            level = new Level3(this);       //creates level 3
            gameSound.updateLev(level); //updates sound
            level.setGravity(52);   //sets gravity
            view.setWorld(level);
            view.updateLevel(level);    //updates level
            setZoom();     // gets the zoom
            mouseInput.setLevel(level,view);
            controller.updateStudent(level.getPlayer());
            level.start();
        }

        else if (level instanceof Level3){
            System.exit(0);   //exits after level 3

        }
    }

    public void setZoom(){    //sets the zoom


        if (zoom>10 && zoom<30){   //caps it between 10 and 30

            view.setView(new Vec2(0,0), zoom);
            //zoom = 30;

        }

    }
    public void increaseZoom(){   //increases the zoom -- button 1
        zoom++;
        if (zoom>30){    //min zoom of 10
            zoom=30;
        }
        setZoom();

    }
    public void decreaseZoom(){   //decrease the zoom -- button 2
        zoom--;
        if (zoom<10){    //zoom capped at 30
            zoom=10;
        }
        setZoom();

    }

    public void pause(){   //pause game
      level.stop();

    }
    public void resume(){   //resume game
        level.start();

    }
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        new myGame();
        System.out.println("W,A,S,D CONTROL AND MOUSE TO OPEN THINGS");
    }

    public void setLevel(GameLev loadLev){
        this.level.stop();

        this.level = loadLev;
        gameSound.updateLev(level);    //updates sound
        level.setGravity(52);   //changes gravity
        view.setWorld(level);
        view.updateLevel(level);    //updates level
        setZoom();   //updates zoom
        mouseInput.setLevel(level,view);   //updates mouse
        controller.updateStudent(level.getPlayer());   //updates the player
        this.level.start();

    }
        public void save(){
        try {

            ReadWriteFile.savelev(level,"data/save.txt");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }
    public void loadSave(){
        try {
            GameLev lev = ReadWriteFile.loadLevel(this,"data/save.txt");

            System.out.println(lev+"R");
            this.setLevel(lev);

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }



}
