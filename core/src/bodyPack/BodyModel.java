package bodyPack;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import handlers.Animation;

/**
 * Created by Gabriel on 24/10/2016.
 */
public abstract class BodyModel {

    public World world;
    public BodyDef bdef;
    public Body body;
    public FixtureDef fdef;
    public Animation animation;
    public float width;
    public float height;

    public BodyModel(){
        animation = new Animation();
    }

    abstract void create(Vector2 position);
    abstract void delete();
}
