package update;
import player.EnemyPlayer;
import player.myPlayer;

import java.util.Timer;
import java.util.TimerTask;

public class updateFPS {

    private myPlayer player;
    private EnemyPlayer enemy;
    Timer timer = new Timer();
  //Two constructor one for enemy one for player

    public updateFPS(myPlayer player){
        this.player = player;

        timer.schedule(task,0,100);

    }
    /**
    public updateFPS(EnemyPlayer enemy){
        this.enemy = enemy;

        timer.schedule(enemyRun,0,300);
    }*/


    //this is a timer for my player
    TimerTask task = new TimerTask() {//runs every 10 times a second

        @Override
        public void run()
        {
            player.myplayerCheck();
        }
    };



}
