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

public class Level3 extends GameLev {



    public chest[] spawnChest;
    private EnemyPlayer[] enemy;
    private StaticBody ground;
    private int enemyCount =8;

    public Level3(myGame game) {

        super(game);
        Shape shape = new BoxShape(50, 0.5f);
        ground = new StaticBody(this, shape);
        StaticBody groundTwo = new StaticBody(this , shape);
        ground.setPosition(new Vec2(40, -5));
        groundTwo.setPosition(new Vec2(20 , -20));


        enemy = new EnemyPlayer[enemyCount];   // Creates 10 enemies
        spawnChest = new chest[5];// Creating array of chest
        for (int i = 0; i < 5; i++) {

            spawnChest[i] = new chest(this);
            spawnChest[i].setAlwaysOutline(true);  //DELETE

        }

        spawnChest[0].setPosition(new Vec2(-5 , ground.getPosition().y+1.5f));
        spawnChest[1].setPosition(new Vec2(50 , ground.getPosition().y+1.5f));

        spawnChest[2].setPosition(new Vec2(-15,groundTwo.getPosition().y+1.5f));
        spawnChest[3].setPosition(new Vec2(20,groundTwo.getPosition().y+1.5f));
        spawnChest[4].setPosition(new Vec2(40,groundTwo.getPosition().y+1.5f));

        enemySpawner(); //spawns enemy
    }
    private void enemySpawner(){         // Enemy spawner. Spawns enemy randomly


        for (int i = 0; i < 8; i++) {    //creates the enemy

            enemy[i] = new EnemyPlayer(this);
            enemy[i].setPosition(new Vec2(10 + i * 13, ground.getPosition().y + 10));  //changes position of them
        }
    }


    // Getter method for chest
    public chest[] getChest() {

        return spawnChest.clone();
    }
/*
    public Vec2 getEnemyHealth() {

        for (int i=0 ; i< 4 ; i++){
            return new Vec2(enemy[i].getPosition());
        }
        return null;
    }
*/


    // Checks to see if the level is complete
    @Override
    public boolean isComplete() {
        if (getPlayer().getDiamond() == 8)
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
        return "Level3";
    }
}