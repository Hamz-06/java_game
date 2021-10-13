package controller;

import game.GameLev;
import game.GameView;
import objects.chest;
import org.jbox2d.common.Vec2;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
    private GameView view;
    public GameLev lev;
    public chest[] clone;

    public MouseInput(GameLev level, GameView view){
        lev = level;
        this.view = view;

        getChestLocation();
    }

    public void getChestLocation(){      // Gets called after every level to get the correct chest

       clone = lev.getChest();    //Stores it in this clone

    }
    public void setLevel(GameLev newLevel , GameView newView){
        this.lev = newLevel;            // gets new level acts as an update
        this.view = newView;
        getChestLocation();
    }

    @Override
    public void mousePressed(MouseEvent e) {

        Point mousePoint = e.getPoint();    // Finds cord of the the mouse pos in screen space
        Vec2 worldPoint = view.viewToWorld(mousePoint);           //convert it to world point

       // System.out.println(this.lev);

        for (int i = 0 ; i<5 ; i++){                  //Searches through every chest array to see if you opened it

            if (worldPoint.x > clone[i].getPosition().x-1 && worldPoint.x < clone[i].getPosition().x+1){
                // minus one as shape spawns in the middle

                if (worldPoint.y > clone[i].getPosition().y -1 && worldPoint.y < clone[i].getPosition().y +1){  // Checks location of chest

                    clone[i].openChest();   // if clicked then it will open the chest and drop loot

                }
            }
        }


    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }

}


