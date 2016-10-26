package bodyPack;

import static handlers.B2DVars.BIT_RAT;
import static handlers.B2DVars.BIT_CHEESE;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.CheeseGame;
import handlers.Animation;

import static handlers.B2DVars.PPM;

/**
 * Created by Gabriel on 25/10/2016.
 */
public class Cheese {

    public Body body;
    BodyDef bdef;
    FixtureDef fdef;
    World world;

    Animation animation;
    TextureRegion[] reg;
    int width = 32;
    int height = 32;


    Cheese(World world){

        this.world = world;

        Texture tex = CheeseGame.res.getTexture("animationCheese");
        reg = new TextureRegion().split(tex, width, height)[0];

        bdef = new BodyDef();
        fdef = new FixtureDef();


        PolygonShape shape = new PolygonShape();
        shape.setAsBox(16f/PPM, 16f/PPM);

        bdef.type = BodyDef.BodyType.StaticBody;

        fdef.shape = shape;
        fdef.isSensor = true;

        fdef.filter.categoryBits = BIT_CHEESE;
        fdef.filter.maskBits = BIT_RAT;




        animation = new Animation();
    }

    public void create(Vector2 position){
        bdef.position.set(position);
        setAnimation(reg, 1/12f);
    }


    public void setAnimation(TextureRegion[] reg, float delay){
        animation.setFrames(reg, delay);
        body = world.createBody(bdef);
        body.createFixture(fdef).setUserData("cheese");
    }

    public void update(float dt){
            animation.update(dt);
    }

    public void render(SpriteBatch sb){
        sb.begin();
        sb.draw(animation.getFrame(), body.getPosition().x * PPM - width/2, body.getPosition().y * PPM - (height/2));
        sb.end();
    }
}
