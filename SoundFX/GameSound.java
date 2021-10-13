package SoundFX;

import city.cs.engine.SoundClip;
import game.GameLev;
import level.Level1;
import level.Level2;
import level.Level3;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class GameSound {


    private static SoundClip diamondPick;
    private static SoundClip bulletFX;
    private static SoundClip crush;
    private static SoundClip[] levelMusic;
    private GameLev lev;


    public GameSound(GameLev lev) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        levelMusic = new SoundClip[3];
        this.lev = lev;
        levelMusic[0] = new SoundClip("data/firstlev.wav");
        levelMusic[1] = new SoundClip("data/secondlev.wav");
        levelMusic[2] = new SoundClip("data/thirdlev.wav");
    }

    public void gameMusic(){    //Game level music for each level
        if (lev instanceof Level1){


            levelMusic[0].loop();


        }
        else if (lev instanceof Level2){

            //levelMusic[0].stop();

            levelMusic[1].loop();

        }
        else if (lev instanceof Level3){

            //levelMusic[1].stop();

            levelMusic[2].loop();

        }
    }
    public void updateLev(GameLev updateLevel){      //updates level to play sound track
        for (int i =0; i< levelMusic.length ; i++){
            levelMusic[i].stop();
        }
        this.lev = updateLevel;
        gameMusic();

   }

    public void diamondSoundfx(){      //plays when diamond is picked up

        try {
            diamondPick = new SoundClip("data/pickup.wav");
            diamondPick.play();
        }catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){
            System.out.println(e);
        }
    }

    public void bulletSound(){      //plays when bullet is shot
        try {
            bulletFX = new SoundClip("data/weapon.wav");
            bulletFX.play();
        }catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){
            System.out.println(e);
        }

    }
    public void enemyHurt(){      //plays when enemy is hit
        try {
            crush = new SoundClip("data/crush.wav");
            crush.play();
        }catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){
            System.out.println(e);
        }

    }

    public GameSound getGameSound(){
        return this;
    }

}
