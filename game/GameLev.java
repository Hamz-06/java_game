package game;

import city.cs.engine.World;
import objects.chest;
import org.jbox2d.common.Vec2;
import player.EnemyPlayer;
import player.myPlayer;
import update.PlayerCollision;

import java.io.IOException;

public abstract class GameLev extends World {

    private myPlayer player;
    private PlayerCollision diamondPickup;
    private myGame game;
    public GameLev(myGame game){

        this.game = game;
        player = new myPlayer(this , game);  //Create player
        diamondPickup = new PlayerCollision(player , game , this);  //creates collision listener
        player.addCollisionListener(diamondPickup);   //adds lisnter to player

    }


    public myPlayer getPlayer() {
        return player;
    }  // return player

    public abstract boolean isComplete();
    public abstract chest[] getChest();

    public abstract EnemyPlayer[] getEnemy();

    public abstract String getLevel();

    public abstract int getEnemyCount();
}


//collision diamondPickup = new collision(myplayer);
//