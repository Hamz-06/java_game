package player;

import city.cs.engine.*;
import city.cs.engine.Shape;
import game.GameLev;
import game.GameView;
import objects.EnemyBullet;
import objects.bullet;
import org.jbox2d.common.Vec2;
import update.EnemyCollision;
import update.updateFPS;

import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class EnemyPlayer extends Walker {

    public static final Shape witchShape = new BoxShape(1.5f, 2.1f);
    private final BodyImage runWitch = new BodyImage("data/witchRun.gif", 8f);
    private updateFPS updateEnemy;
    private GameLev lev;
    private int enemyDirection;
    private AttachedImage witch;
    private GameView view;
    private Graphics2D gtd;
    private boolean isFlip;
    Timer timer = new Timer();
    private int enemyHealth;

    public EnemyPlayer(GameLev lev) {    //Constructor

        super(lev , witchShape);
        this.lev = lev;
        //updateEnemy = new updateFPS(this);     // Creates a timer for follow player method
        enemyHealth = 100;
        jumpTimer();
        timer.schedule(enemyRun,0,300);

    }

    public void followPlayer(){

        removeAllImages();
        witch = new AttachedImage(this, runWitch, 1f,0,new Vec2(0f,0f));  //Creates witch

        float playerRange = this.getPosition().x - lev.getPlayer().getPosition().x;

        if (playerRange<0f){     // gets position of player

            this.startWalking(3f);                               //walks towards them depending on x pos
            isFlip = false;

        }

        else if (playerRange>0f){

            this.startWalking(-3f);
            witch.flipHorizontal();              // Flips image of the enemy to face me
            isFlip = true;
        }

    }

    public void jumpTimer(){
        Random rand = new Random();
        int randomJumpTime = rand.nextInt(7);
        final int offset = 3;
        jumpEn(randomJumpTime+offset);
    }

    public void jump() {

        
        this.jump(40);
        try {
            EnemyBullet enBullet= new EnemyBullet(lev, this.getPosition(), isFlip);
        }
        catch(NullPointerException exception) {
            System.out.println(exception);
        }

    }



    public void setEnemyHealth(){

        enemyHealth = enemyHealth-30;   //when bullet hits takes away 30 health
        killEnemy();
    }


    public int getEnemyHealth() {
        return enemyHealth;
    }

    //this is a timer for my enemy
    TimerTask enemyRun = new TimerTask() {//runs every 10 times a second

        @Override
        public void run()
        {
            followPlayer();

        }
    };


    //this is a timer for my enemy to jump
    TimerTask enemyjump = new TimerTask() {//runs every 10 times a second

        @Override
        public void run()
        {
            try {
                //
                jump();
            }
            catch(NullPointerException exception) {
                System.out.println(exception);
            }


        }
    };

    public void jumpEn(int jumpTime){

        int jumpTimer = 1000*jumpTime;
        timer.schedule(enemyjump,4000,jumpTimer);

    }

    public void cancelTimers(){
        enemyjump.cancel();
        enemyRun.cancel();
    }

    public void draw(Graphics2D g, GameView drawView){

        gtd = (Graphics2D) g;
        if (enemyHealth>0){

            //draws health on player. Converts world cords to view cords to draw it on player.

            g.setColor(Color.red);    //draws the health bar
            gtd.fillRect((int) drawView.worldToView(this.getPosition()).x -20 ,(int) drawView.worldToView(this.getPosition()).y-70, 50,10);


            g.setColor(Color.green);    //draws the health bar
            gtd.fillRect((int) drawView.worldToView(this.getPosition()).x -20 ,(int) drawView.worldToView(this.getPosition()).y-70, this.getEnemyHealth()/2,10);
        }

        //gtd.clearRect((int) drawView.worldToView(this.getPosition()).x -20 ,(int) drawView.worldToView(this.getPosition()).y-70, this.getEnemyHealth()/2,10);
    }

    private void killEnemy(){  //Kills enemy
        if (enemyHealth<0){

            this.destroy();
            cancelTimers();
        }
    }


}

