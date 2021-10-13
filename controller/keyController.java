package controller;
import player.myPlayer;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class keyController implements KeyListener {

    private static final float RUNNING_SPEED = 10;
    private myPlayer myplayer;
    public boolean isPress;

    public keyController (myPlayer s){

        myplayer = s;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_A) {
            myplayer.startWalking(-RUNNING_SPEED);     // player runs backwards



        } else if (code == KeyEvent.VK_D) {
            myplayer.startWalking(RUNNING_SPEED);   // Player runs forward


        } else if (code == KeyEvent.VK_SPACE ){      //player jumps
            myplayer.jump(30);


        }else if (code == KeyEvent.VK_E && !isPress){
            isPress = true;
            myplayer.shoot();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {   //key release
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_A) {   //when key a is released the player stops walking

            myplayer.stopWalking();

        } else if (code == KeyEvent.VK_D) {

            myplayer.stopWalking();    //stops walking when key is released
        }
        else if(code == KeyEvent.VK_E){
            isPress = false;
        }
    }

    public void updateStudent(myPlayer myplayer){   //updates the student
        this.myplayer = myplayer;
    }

}
