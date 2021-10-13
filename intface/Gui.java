package intface;

import game.GameLev;
import game.ReadWriteFile;
import game.myGame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;

public class Gui {


    private JPanel mainPanel;

    private JPanel topPanel;

    private JButton Pause;
    private JButton play;

    private JButton zoomOutButton;
    private JButton ZoomIn;
    private JButton exitGame;
    private JButton saveGameButton;
    private JButton loadGameButton;
    private GameLev lev;
    private myGame game;

    public Gui(GameLev lev , myGame game) {
        this.lev = lev;
        this.game = game;
        Pause.addActionListener(new ActionListener() {   //Action listner for pause button
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("pause");
                game.pause();
            }
        });

        play.addActionListener(new ActionListener() {    //play
            @Override
            public void actionPerformed(ActionEvent e) {
                game.resume();
            }
        });

        zoomOutButton.addActionListener(new ActionListener() {   //zoom out
            @Override
            public void actionPerformed(ActionEvent e) {
                game.decreaseZoom();

            }
        });
        ZoomIn.addActionListener(new ActionListener() {   //zoom in
            @Override
            public void actionPerformed(ActionEvent e) {
                game.increaseZoom();
            }
        });


        exitGame.addActionListener(new ActionListener() {   //  exit screen
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        saveGameButton.addActionListener(new ActionListener() {   // saves the game
            @Override
            public void actionPerformed(ActionEvent e) {
                //lev.save();
                game.save();
            }
        });

        loadGameButton.addActionListener(new ActionListener() {   // saves the game
            @Override
            public void actionPerformed(ActionEvent e) {
                //lev.loadSave();
                game.loadSave();
            }
        });

    }
    public JPanel getMainPanel() {
        return mainPanel;
    }

}
