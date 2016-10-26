package bodyPack;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.CheeseGame;

import static handlers.B2DVars.*;

/**
 * Created by Gabriel on 24/10/2016.
 */
public class Mouse extends BodyModel {

    static int mouseNum = 1;
    public int id;
    public TextureRegion[] sprites;
    boolean toRight = true;
    public boolean stop = true;


    public Mouse(World world){
        super();

        id = mouseNum;
        mouseNum++;
        this.world = world;
        bdef = new BodyDef();
        fdef = new FixtureDef();

        bdef.type = BodyDef.BodyType.DynamicBody;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(16f/PPM, 8f/PPM);

        fdef.shape = shape;
        fdef.filter.categoryBits = BIT_RAT;
        fdef.filter.maskBits = (short) (BIT_CHEESE | BIT_GROUND | BIT_TOCA);

    }



    @Override
    public void create(Vector2 position) {

        width = 32f;
        height = 32f;

        Texture tex = CheeseGame.res.getTexture("rightWlk");
        sprites = TextureRegion.split(tex, 32, 32)[0];

        bdef.position.set(position);
        body = world.createBody(bdef);
        body.createFixture(fdef).setUserData("Mouse" + id);

        FixtureDef foot = new FixtureDef();

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(10f/PPM, 5f/PPM, new Vector2(0, -4f/PPM), 0);
        foot.shape = shape;
        foot.filter.categoryBits = BIT_RAT;
        foot.filter.maskBits = BIT_GROUND;
        foot.isSensor = true;
        body.createFixture(foot).setUserData("Foot" + id);

        setAnimation(sprites, 1/12f);



    }

    @Override
    public void delete() {

    }

    public void setAnimation(TextureRegion[] reg, float delay){
        animation.setFrames(reg, delay);
    }

    public void update(float dt){
        if(!stop)
            animation.update(dt);
    }

    public void render(SpriteBatch sb){
        sb.begin();
        sb.draw(animation.getFrame(), body.getPosition().x * PPM - width/2, body.getPosition().y * PPM - (height - 11));
        sb.end();
    }

    public void leftAnimation(){
        Texture tex = CheeseGame.res.getTexture("leftWlk");
        sprites = TextureRegion.split(tex, 32, 32)[0];
        if(toRight){
            setAnimation(sprites, 1/12f);
            toRight = !toRight;
        }

    }

    public void rightAnimation(){
        Texture tex = CheeseGame.res.getTexture("rightWlk");
        sprites = TextureRegion.split(tex, 32, 32)[0];
        if(!toRight){
            setAnimation(sprites, 1/12f);
            toRight = !toRight;
        }
    }
}
