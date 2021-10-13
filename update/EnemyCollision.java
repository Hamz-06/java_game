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



public class EnemyCollision implements CollisionListener {  //collision listner

    private GameLev lev;
    private EnemyBullet bullet;




    public EnemyCollision(EnemyBullet bullet , GameLev lev) {  //bullet constructor
        this.bullet = bullet;

    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof myPlayer){   //destroys bullet if it touched the enemy



            try {
                ((myPlayer) e.getOtherBody()).playerHit();   //reduces health of the enemy
                this.bullet.destroy();
            }
            catch(NullPointerException exception) {
                exception.printStackTrace();
            }



        }

    }
}