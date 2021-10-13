package game;

import city.cs.engine.UserView;
import city.cs.engine.World;
import level.Level1;
import level.Level2;
import level.Level3;
import org.jbox2d.common.Vec2;
import player.EnemyPlayer;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.Arrays;

public class GameView extends UserView {

    private Image levelOneBack;
    private Image levelTwoBack;
    private Image levelThreeBack;
    private GameLev lev;
    //level 1 parallax background




    public GameView (GameLev lev, int width, int height) {

        super(lev, width, height);
        this.lev = lev;
        levelOneBack = new ImageIcon("data/glacier.png").getImage();
        levelTwoBack = new ImageIcon("data/parallax1.png").getImage();
        levelThreeBack = new ImageIcon("data/far-buildings.png").getImage();


    }


    protected void paintBackground(Graphics2D g){    //paints background depending on the level

        this.setCentre(lev.getPlayer().getPosition());
        //lev.getPlayer().getDiamond();

        if (lev instanceof Level1){       //changes level

            g.drawImage(levelOneBack,0,0,1500,800,null);  //level 1
        }
        else if (lev instanceof Level2){

            g.drawImage(levelTwoBack,0,0,1500,800,null); //level 2
        }

        else if (lev instanceof Level3){

            g.drawImage(levelThreeBack,0,0,1500,800,null);   //level3
        }
    }
    protected void paintForeground(Graphics2D g) {   // paints the background
        Font sansSerif = new Font("SansSerif", Font.BOLD, 20);
        g.setFont(sansSerif);

        g.setColor(Color.BLACK);
        g.drawString("Bullets:" + lev.getPlayer().getBulletCount() + "/ 500", 20 ,60);  //adds bullet on gui
        g.drawString("Diamond: " + lev.getPlayer().getDiamond(), 20, 30);   //adds diamond on gui

        g.setColor(Color.red);    //draws the health bar
        g.fillRect(450,15,300,50);

        g.setColor(Color.green);   //green color overlapping the gray background
        g.fillRect(450,15,lev.getPlayer().getHealth() * 3, 50);

        g.setColor(Color.black);       //outline for functionality
        g.drawRect( 450 ,15,300,50);

        //lev.getPlayer().draw(g);



        for (int i=0 ; i < lev.getEnemyCount() ; i ++){

            lev.getEnemy()[i].draw(g, this);

        }


    }




public void updateLevel(GameLev newLev){   //updates the level allowing the background to change
        this.lev = newLev;
    }
/*
    public GameView getGameview(){
        return this;


    }
*/


}
