package update;

import SoundFX.GameSound;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.GameLev;
import game.myGame;
import objects.EnemyBullet;
import objects.bullet;
import player.EnemyPlayer;
import player.myPlayer;


public class BulletCollision implements CollisionListener {  //collision listner

    private GameLev lev;
    private bullet bullet;
    private GameSound bulletSound;


    public BulletCollision(bullet bullet, GameSound bulletSound) {  //bullet constructor
        this.bullet = bullet;

    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof EnemyPlayer){   //destroys bullet if it touched the enemy

            bullet.destroy();
            ((EnemyPlayer) e.getOtherBody()).setEnemyHealth();   //reduces health of the enemy

            try {


            }
            catch(NullPointerException exception) {
                exception.printStackTrace();
            }
        }

    }
}
