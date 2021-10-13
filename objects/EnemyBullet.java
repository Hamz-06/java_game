package objects;

import city.cs.engine.*;
import game.GameLev;
import org.jbox2d.common.Vec2;
import update.EnemyCollision;

import java.util.Timer;
import java.util.TimerTask;

public class EnemyBullet extends DynamicBody {

    private static final BodyImage enBullet = new BodyImage("data/orange.png", 0.7f);
    public static final Shape bulletShape = new BoxShape(0.5f, 0.5f);
    private boolean flipBullet;
    private int bulletDirection;

    Timer timer = new Timer();

    public EnemyBullet(GameLev lev, Vec2 spawnPosition, boolean flipBullet) throws NullPointerException {
        super(lev, bulletShape);

        if (flipBullet){     // Flips image of the bullet depending on player
            bulletDirection = -1;
        }else {
            bulletDirection = 1;

        }
        AttachedImage bullet = new AttachedImage(this, enBullet, 1,0,new Vec2(0f,0f));
        shootBullet(spawnPosition);
        EnemyCollision collision = new EnemyCollision(this , lev);   //adds enemy collision class
        this.addCollisionListener(collision);  //Adds collision


        //timer.schedule(destroyTimer,4000);


    }


    public void shootBullet(Vec2 spawn){

        this.setPosition(new Vec2(spawn.x , spawn.y+5));
        this.applyImpulse(new Vec2(35f * bulletDirection,0f));

    }

    TimerTask destroyTimer = new TimerTask() {
        @Override
        public void run()
        {
            try {

                destroyBullet();
            }
            catch(NullPointerException exception) {
                System.out.println(exception + "wd");


            }

        }
    };

    public void destroyBullet() throws NullPointerException{
        this.destroy();

    }
}
