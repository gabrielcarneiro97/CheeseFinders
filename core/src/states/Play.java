package states;

import static handlers.B2DVars.PPM;
import static handlers.B2DVars.BIT_GROUND;
import static handlers.B2DVars.BIT_RAT;

import bodyPack.Cheese;
import bodyPack.Map;
import bodyPack.Mouse;
import bodyPack.Rope;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.physics.box2d.joints.RopeJointDef;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.CheeseGame;
import handlers.GameContactListener;
import handlers.GameInputs;
import handlers.GameStateManager;

/**
 * Created by Gabriel on 23/10/2016.
 */
public class Play extends GameState {

    private final float JUMP_HIGH = 200f;
    private final float STEP = 5f;

    Mouse mouse1;
    Mouse mouse2;
    Mouse set;
    Rope rope;

    int totalCheese;
    int collectedCheese;

    Map map;

    private boolean togheter = true;

    private GameContactListener cl;
    private Box2DDebugRenderer b2dr;
    private OrthographicCamera b2dCam;



    public Play(GameStateManager gsm){
        super(gsm);

        this.world = new World(new Vector2(0, -9.81f), true);
        cl = new GameContactListener();
        world.setContactListener(cl);
        b2dr = new Box2DDebugRenderer();


        RopeJointDef jdef = new RopeJointDef();
        jdef.maxLength = 210f/PPM;


        //mouse 1
        this.mouse1 = new Mouse(world);
        mouse1.create(new Vector2(180f/PPM, 200f/PPM));

        //mouse 2
        this.mouse2 = new Mouse(world);
        mouse2.create(new Vector2(180f/PPM, 200f/PPM));

        this.set = mouse1;

        jdef.localAnchorA.set(0, 0);
        jdef.localAnchorB.set(0, 0);
        jdef.bodyA = mouse1.body;
        jdef.bodyB = mouse2.body;

        world.createJoint(jdef);


        rope = new Rope(world, mouse1, mouse2);



        b2dCam = new OrthographicCamera();
        b2dCam.setToOrtho(false, (float)CheeseGame.V_WIDTH/PPM, (float)CheeseGame.V_HEIGHT/PPM);

        map = new Map("desktop/assets/maps/teste.tmx", this);
        map.create();


    }

    @Override
    public void handlerInput() {

        if(GameInputs.isDown(GameInputs.ARROW_RIGHT)) {
            if (togheter) {
                mouse1.rightAnimation();
                mouse2.rightAnimation();
                moveBoth(STEP);
            }
            else {
                set.rightAnimation();
                moveSet(STEP);
            }
        }
        else if(GameInputs.isDown(GameInputs.ARROW_LEFT)) {
            if (togheter){
                mouse1.leftAnimation();
                mouse2.leftAnimation();
                moveBoth(-STEP);
            }
            else {
                set.leftAnimation();
                moveSet(-STEP);
            }
        }
        else {
            mouse1.stop = true;
            mouse2.stop = true;
        }

        if(GameInputs.isPressed(GameInputs.ARROW_UP)){
            if(togheter) jumpBoth();
            else jumpSet();
        }

        if(GameInputs.isPressed(GameInputs.ACTION1)){
            changeMouse();
        }
        if(GameInputs.isPressed(GameInputs.ACTION2)){
            changeState();
        }
    }

    @Override
    public void update(float dt) {

        world.step(dt, 6, 2);

        Array<Body> bodies = cl.cheeseRemove;

        for (int i = 0; i<bodies.size; i++){
            Body b = bodies.get(i);
            for (Cheese cheese : map.cheeses){
                if(b.getPosition() == cheese.body.getPosition()){
                    map.cheeses.removeValue(cheese, true);
                    world.destroyBody(b);
                    collectedCheese++;
                }
            }
        }
        bodies.clear();

        Vector2 position = set.body.getPosition();
        float x = position.x;
        float y = position.y;

        b2dCam.position.set(x, y*1.3f, 0);
        b2dCam.update();
        cam.position.set(x*PPM,y*PPM*1.3f,0);
        cam.update();

        mouse1.update(dt);
        mouse2.update(dt);
        map.updateCheeses(dt);
        //handle inputs
        handlerInput();
    }

    @Override
    public void render() {

        //clear screen
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);


        //draw world
        b2dr.render(world, b2dCam.combined);
        map.render(cam);
        mouse1.render(batch);
        mouse2.render(batch);
        map.renderCheeses(batch);
        rope.render(batch);

        totalCheese = map.totalCheese;

    }

    @Override
    public void dispose() {

    }

    void changeMouse(){
        if(set == mouse1){
            set = mouse2;
        }
        else if(set == mouse2){
            set = mouse1;
        }
    }

    void changeState(){

        if(!togheter){
            if(set == mouse1){
                set.body.setTransform(mouse2.body.getPosition(), 0);
            }
            if(set == mouse2){
                set.body.setTransform(mouse1.body.getPosition(), 0);
            }
        }

        togheter = !togheter;
    }

    void moveBoth(float force){
        Vector2 position1 = mouse1.body.getPosition();
        float y1 = position1.y;

        Vector2 position2 = mouse2.body.getPosition();
        float y2 = position2.y;

        mouse1.body.applyForceToCenter(force, y1, true);
        mouse2.body.applyForceToCenter(force, y2, true);

        mouse1.stop = false;
        mouse2.stop = false;



    }

    void moveSet(float force){
        Vector2 position = set.body.getPosition();
        float y = position.y;
        set.body.applyForceToCenter(force, y, true);
        set.stop = false;
    }

    void jumpBoth(){
        if(cl.grounded(mouse1.id) && cl.grounded(mouse2.id)){
            Vector2 position1 = mouse1.body.getPosition();
            float x1 = position1.x;

            Vector2 position2 = mouse2.body.getPosition();
            float x2 = position2.x;

            mouse1.body.applyForceToCenter(x1, JUMP_HIGH, true);
            mouse2.body.applyForceToCenter(x2, JUMP_HIGH, true);

            set.stop = false;


        }

    }

    void jumpSet(){
        if(cl.grounded(set.id)){
            Vector2 position = set.body.getPosition();
            float x = position.x;
            set.body.applyForceToCenter(x, JUMP_HIGH, true);

        }
    }

}
