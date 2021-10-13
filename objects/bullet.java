package objects;

import SoundFX.GameSound;
import city.cs.engine.*;
import game.GameLev;
import org.jbox2d.common.Vec2;
import update.BulletCollision;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Mohammad, Iqbal, Mohammad.Iqbal.7@city.ac.uk
 * @version 1.0
 * @since Mar 2021
 * */
public class bullet extends DynamicBody {

    private static final BodyImage bulletimg = new BodyImage("data/bullet2.png", 0.7f);
    public static final Shape bulletShape = new BoxShape(0.5f, 0.5f);
    private int bulletDirection;
    private GameSound gamesound;
    Timer timer = new Timer();

    /**
     * Constructs the bullet class
     *<p>
     *     This class first checks to see what face my player is facing. It then
     *     flips the bullets image and shoots it in that direction. A timer
     *     is also called to destroy the bullet after 5 seconds.
     *
     * @param  lev passes the current level
     * @param  playerPos passes the player position
     * @param  flipBullet checks to see what way the player is facing
     *
     */
    public bullet(GameLev lev , Vec2 playerPos , boolean flipBullet ) {

        super(lev, bulletShape);

        if (flipBullet){     // Flips image of the bullet depending on player
            bulletDirection = -1;
        }else {
            bulletDirection = 1;
        }
        //The scale flips the image so if i shoot from the left the bullet will face the left
        AttachedImage bullet = new AttachedImage(this,bulletimg, 1f * bulletDirection ,0,new Vec2(0f,0f));
        moveBullet(playerPos);   //calls this and sends the position of my player
        BulletCollision bulletCollision = new BulletCollision(this, gamesound);   //Adds collision listners
        this.addCollisionListener(bulletCollision);   //adds the listner
        timer.schedule(destroyTimer,3000);   //THIS is causing me some issues but if u want to add it u can

    }
    /**
     * Moves my bullet according to its position
     *<p>
     *     Gets the players position and sets the bullet so it is outside of the players body.
     *     It then applies a force in the x axis. However if the player is facing the left, ther bullet direction is
     *     multiplied by -1 so it shoots to the left.
     *
     * @param  playerPos passes the current level
     *
     */
    private void moveBullet(Vec2 playerPos){

        this.setPosition(new Vec2 (playerPos.x +(2*bulletDirection), playerPos.y -1f));    // positions bullet in gun. and outside of the body
        this.applyImpulse(new Vec2(50f * bulletDirection,0f));     //if player face is away apply force other way
    }

    /**
     * Triggers a timer
     */
    TimerTask destroyTimer = new TimerTask() {

        @Override
        public void run()
        {
            destroyBullet();
        }
    };

    /**
     * Destroys the bullet
     */
    public void destroyBullet(){
        this.destroy();
    }

}
