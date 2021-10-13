package level;

import SoundFX.GameSound;
import city.cs.engine.*;
import city.cs.engine.Shape;

import game.GameLev;
import game.myGame;
import objects.chest;
import org.jbox2d.common.Vec2;
import player.EnemyPlayer;
import update.BulletCollision;
import update.EnemyCollision;


public class Level1 extends GameLev {



    public chest[] spawnChest;
    private GameLev lev;
    private EnemyPlayer[] enemy;
    private StaticBody ground;
    private int enemyCount =4;


    public Level1(myGame game) {

        super(game);
        // Creates floor
        Shape shape = new BoxShape(60, 0.5f);   //Creates the floor
        ground = new StaticBody(this, shape);
        ground.setPosition(new Vec2(40, -5));





        enemy = new EnemyPlayer[enemyCount];   // Creates 4 enemies
        spawnChest = new chest[5];         //creates 5 chest
        for (int i = 0; i < 5; i++) {

            spawnChest[i] = new chest(this);
            spawnChest[i].setAlwaysOutline(true);  //DELETE
            spawnChest[i].setPosition(new Vec2(10 + i * 20, ground.getPosition().y + 1.5f));

        }

        enemySpawner(); //spawns enemy

    }

    private void enemySpawner(){         // Enemy spawner. Spawns enemy randomly


        for (int i = 0; i < 4; i++) {    //creates the enemy

            enemy[i] = new EnemyPlayer(this);
            enemy[i].setPosition(new Vec2(10 + i * 13, ground.getPosition().y + 10));  //changes position of them
        }
    }
    // Getter method for chest
    public chest[] getChest() {

        return spawnChest.clone();
    }





    // Checks to see if the level is complete
    @Override
    public boolean isComplete() {
        return getPlayer().getDiamond() == 4;        //If you collect the diamonds it will go to the next level
    }

    @Override
    public EnemyPlayer[] getEnemy() {
        return enemy;
    }

    @Override
    public int getEnemyCount() {
        return enemyCount;
    }

    @Override
    public String getLevel(){
        return "Level1";

    }

}




