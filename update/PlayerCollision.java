package update;

import SoundFX.GameSound;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.GameLev;
import game.myGame;
import objects.Coin;
import objects.bullet;
import player.EnemyPlayer;
import player.myPlayer;

public class PlayerCollision implements CollisionListener {

private myPlayer player;
private myGame game;
private GameLev lev;


    public PlayerCollision(myPlayer player , myGame game , GameLev lev){
        this.player = player;
        this.game = game;
        this.lev = lev;
    }

    // Coin collision
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Coin) {

            player.addDiamond();       //adds diamond if it is a coin
            e.getOtherBody().destroy();   //destroys the diamond /coin

            game.gameSound.diamondSoundfx();   //plays the sound

            if (lev.isComplete()){

                game.nextLevel();           //goes nexr level if enough coins are collected
            }
        }



    }
}
