package player;

import city.cs.engine.*;
import city.cs.engine.Shape;
import game.GameLev;
import game.myGame;
import level.Level1;
import level.Level2;
import level.Level3;
import objects.bullet;
import update.updateFPS;
import org.jbox2d.common.Vec2;


/**
 * @author Mohammad, Iqbal, Mohammad.Iqbal.7@city.ac.uk
 * @version 1.0
 * @since Mar 2021
 * */

public class myPlayer extends Walker {

    private updateFPS updateFrame;
    private GameLev lev;
    public static final Shape myChar = new BoxShape(1.4f, 3.2f);
    private static final BodyImage runimg = new BodyImage("data/runPlayer.gif", 7f);
    private static final BodyImage idleimg = new BodyImage("data/idlePlayer.gif", 7f);
    private bullet bullet;
    public static int diamond;
    private boolean isFlip;
    private int myHealth;
    private int bulletCount;
    private myGame game;

    /**
     * Constructs of my class which sets up the fields my player needs
     *
     * @param  lev passes the current level
     * @param  game passes the game class
     *
     */
    public myPlayer(GameLev lev, myGame game) {

        super(lev, myChar);
        this.game = game;  // I can now access game and level in this class
        this.lev = lev;
        updateFrame = new updateFPS(this);    // Creates animation object
        diamond = 0;       //Sets value to zero
        bulletCount = 500;  //bullet count
        healthBar();
        
    }

    /**
     * checks to see if my player is running
     * <p>
     * This function gets the players position and checks its velocity. If the velocity
     * is greater then 0. The player will face the right. If if it negative he will face the left.
     *
     */
    public void myplayerCheck( ){         // Is run 10 times a second and checks the speed of player

        //System.out.println(this.getLinearVelocity().x);
        

        if (this.getLinearVelocity().x > 0.5f ) {
            isFlip = false;
            playerAnimation(runimg  , false);       //flips images and makes the player run and idle
           
            
        } else if(this.getLinearVelocity().x < -0.5){
            isFlip = true;
            playerAnimation(runimg , true);
            
            
        } else if(this.getLinearVelocity().x > -0.5 && this.getLinearVelocity().x < 0.5f ) {
            playerAnimation(idleimg  ,isFlip);
        }
    }

    /**
     * Player animation
     *<p>
     *This is used to check if the player is standing still or running. If isFlip is true then the
     * player will flip horizontally so he faces the right way.
     * @param  playerState passes the player state to check if the player is running or idle
     * @param  isFlip Flips the image depending on the velocity
     *
     */
    public void playerAnimation(BodyImage playerState, boolean isFlip){     //Changes players running animation state

        BodyImage myState = playerState;    // run/idle state

        removeAllImages();
        AttachedImage run = new AttachedImage(this, myState, 1f, 0f, new Vec2(0f, 0f));


        if (isFlip){
            run.flipHorizontal(); //flips image
        }
    }

    /**
     * Sets my players health
     */
    private void healthBar(){
        myHealth = 100;

    }
    /**
     * Gets my players health
     *<p>
     *Now we can get my players health in any class
     * @return myHealth returns my players health
     *
     */
    public int getHealth(){
        return myHealth;
    }

    /**
     * If my player is hit
     *<p>
     *This function is called in other classes and is used if my player has been
     * hit my a bullet or an enemy. It changes according to the level you are on.
     */
    public void playerHit(){  // If player is hit then he will lose health depending on the level

        if (lev instanceof Level1){
            myHealth-=5;     // Player takes 10 health of damage

        }
        else if (lev instanceof Level2){

            myHealth-=10;    // Player takes 20 health of damage

        }

        else if (lev instanceof Level3){
            myHealth-=15;   // Player takes 30 health of damage

        }
        if (myHealth<=0){
            this.destroy();
            System.exit(0);
        }
    }
    /**
     * Gets the bullet count
     *<p>
     *Allows the other class to access the bullet count for my player
     * @return bulletCount  returns the bullet count
     */
    public int getBulletCount() {
        return bulletCount;
    }

    /**
     * Gets the bullet count
     *<p>
     *Adds diamonds to my player
     * @return bulletCount  returns the bullet count
     */
    public void addDiamond(){           //Add diamonds to player
        diamond++;
    }    //Add diamond to stack

    /**
     * Gets diamonds
     *<p>
     *gets the diamonds
     * @return returns diamonds
     */
    public int getDiamond(){
        return diamond;
    }


    /**
     * Shoots the bullets
     *<p>
     *Checks to see my players bullet count. If it zero it wont shoot. This function also
     * plays a sound. A bullet is also decremented.
     *
     */
    public void shoot(){    //bullet shoots and returns players position so that it could face the right way

        if (!(bulletCount<=0)){
            bullet = new bullet(lev , this.getPosition() , isFlip);    //CREATES A BULLET AND PASSES THIS
            //isFlip is to check what face the player is facing
            game.gameSound.bulletSound();  //plays bullet sound
            bulletCount--;   //take away one bullet


        }

        this.setLinearVelocity(new Vec2(4,0));
    }
    /**
     * sets my players health
     *<p>
     * If this is called in another class. We can set the players health directly. This is used in my load game function.
     *
     */
    public void setMyHealth(int health){
        this.myHealth = health;
    }






}





