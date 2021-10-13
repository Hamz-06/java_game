package objects;

import city.cs.engine.*;
import game.GameLev;
import objects.Coin;
import org.jbox2d.common.Vec2;

public class chest extends StaticBody {



    public static final Shape chestShape = new BoxShape(1f, 1f);
    public static final Shape coinShape = new BoxShape(1.2f,1.1f);

    private final BodyImage closedChest = new BodyImage("data/closedchest.png", 2f);
    private final BodyImage openChest = new BodyImage("data/openchest.png", 2f);

    private int chestOpen;

        private GameLev lev;

    public chest(GameLev lev) {

        super(lev , chestShape);
        this.lev = lev;   //changes level
        chestState(closedChest);     //creates chest as closed when created
        chestOpen = 0;  //checks to see if chest has been open
    }

    public void openChest(){


        chestState(openChest);

        if (chestOpen<1){

            Coin coin[] = new Coin[2];    //creates 2 coin when you open the chest

            coin[0] = new Coin(lev,coinShape);
            coin[0].setPosition(new Vec2(this.getPosition().x -4 , this.getPosition().y+15));

            coin[1] = new Coin(lev,coinShape);
            coin[1].setPosition(new Vec2(this.getPosition().x +3 , this.getPosition().y+11));

        }
        chestOpen++;
    }

    public void chestState(BodyImage chestS){   //changes image when you open the chest

        AttachedImage chest = new AttachedImage(this,chestS,1,0f,new Vec2(0f,0f));  //image of open chest

    }







}
