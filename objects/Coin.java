package objects;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Coin extends DynamicBody {
    private static final BodyImage coin = new BodyImage("data/coin.png", 3f);

    public Coin(World w, Shape s) {

        super(w, s);

         AttachedImage chest = new AttachedImage(this,coin,1,0f,new Vec2(0f,0f));   //coin image

    }


}

