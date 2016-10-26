package bodyPack;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.CheeseGame;

import static handlers.B2DVars.PPM;

/**
 * Created by Gabriel on 25/10/2016.
 */
public class Rope {

    Array<Body> rope;
    Texture tex;
    float width = 10f;
    float height = 3f;

    public Rope(World world, Mouse mouse1, Mouse mouse2){

        tex = CheeseGame.res.getTexture("rope");

        rope = new Array<Body>();

        for (int i = 0; i < 10; i++){
            Body b;
            BodyDef bdef = new BodyDef();
            FixtureDef fdef = new FixtureDef();

            bdef.type = BodyDef.BodyType.DynamicBody;

            PolygonShape shape = new PolygonShape();
            shape.setAsBox(10f/PPM, 3f/PPM);
            fdef.shape = shape;
            fdef.density = .01f;

            bdef.position.set(mouse1.body.getPosition());

            b = world.createBody(bdef);
            b.createFixture(fdef).setUserData("rope");


            RevoluteJointDef jdef2 = new RevoluteJointDef();



            RevoluteJointDef jdef3 = new RevoluteJointDef();


            if(i == 0){

                jdef2.bodyA = mouse1.body;
                jdef2.bodyB = b;
                jdef2.localAnchorA.set(-7f/PPM, -3f/PPM);
                jdef2.localAnchorB.set(-10f/PPM, 0);
            }
            else if(i == 9){

                jdef2.bodyA = mouse2.body;
                jdef2.bodyB = b;

                jdef3.bodyA = b;
                jdef3.bodyB = rope.get(i - 1);
                jdef3.localAnchorA.set(-10f/PPM, 0);
                jdef3.localAnchorB.set(10f/PPM, 0);
                world.createJoint(jdef3);
                jdef2.localAnchorA.set(-7f/PPM, -3f/PPM);
                jdef2.localAnchorB.set(10f/PPM, 0);

            }
            else {
                jdef2.bodyA = rope.get(i - 1);
                jdef2.bodyB = b;
                jdef2.localAnchorA.set(10f/PPM, 0);
                jdef2.localAnchorB.set(-10f/PPM, 0);
            }

            world.createJoint(jdef2);
            rope.add(b);
        }
    }


    public void render(SpriteBatch sb){
        sb.begin();
        for (Body body : rope)
            sb.draw(tex, body.getPosition().x *PPM, body.getPosition().y *PPM, width, height-2, 21, 3, /*scaleX*/1, /*scaleY*/1, /*rotation*/ body.getAngle() * MathUtils.radDeg, 0, 0, 80, 7, /*flipX*/false, /*flipY*/false);
        sb.end();
    }


}
