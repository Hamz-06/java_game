package level;

import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import game.GameLev;
import game.myGame;
import objects.chest;
import org.jbox2d.common.Vec2;
import player.EnemyPlayer;

import java.util.ArrayList;

public class Level2 extends GameLev {



    public chest[] spawnChest;
    private EnemyPlayer[] enemy;
    private StaticBody ground;
    private int enemyCount =6;

    public Level2(myGame game) {

        super(game);
        Shape shape = new BoxShape(60, 0.5f);
        ground = new StaticBody(this, shape);
        ground.setPosition(new Vec2(40, -5));

        enemy = new EnemyPlayer[enemyCount];   // Creates 10 enemies
        spawnChest = new chest[5];// Creating array of chest
        for (int i = 0; i < 5; i++) {

            spawnChest[i] = new chest(this);
            spawnChest[i].setAlwaysOutline(true);  //DELETE
            spawnChest[i].setPosition(new Vec2(10 + i * 20, ground.getPosition().y + 1.5f));
        }


        enemySpawner(); //spawns enemy

    }
    // Getter method for chest
    @Override
    public chest[] getChest() {

        return spawnChest.clone();
    }



    private void enemySpawner(){         // Enemy spawner. Spawns enemy randomly


        for (int i = 0; i < 6; i++) {    //creates the enemy

            enemy[i] = new EnemyPlayer(this);
            enemy[i].setPosition(new Vec2(10 + i * 13, ground.getPosition().y + 10));  //changes position of them
        }
    }



    // Checks to see if the level is complete
    @Override
    public boolean isComplete() {
        if (getPlayer().getDiamond() == 6)
            return true;
        else
            return false;
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
        return "Level2";

    }
}
