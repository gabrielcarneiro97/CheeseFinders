package bodyPack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import states.GameState;

import static handlers.B2DVars.*;

/**
 * Created by Gabriel on 24/10/2016.
 */
public class Map {

    public TiledMap tiledMap;
    public OrthogonalTiledMapRenderer renderer;
    World world;
    public Body body;
    float tileSize;
    GameState state;
    public Array<Cheese> cheeses;


    public int totalCheese;


    public Vector2 initialPosition;

    public Map(String path, GameState state){
        tiledMap = new TmxMapLoader().load(Gdx.files.internal(path).file().getAbsolutePath());
        this.world = state.world;
        this.state = state;
        renderer = new OrthogonalTiledMapRenderer(tiledMap, 1, state.batch);

        totalCheese = 0;
    }

    public void create(){

        TiledMapTileLayer layer1 = (TiledMapTileLayer) tiledMap.getLayers().get("layer1");


        tileSize = layer1.getTileHeight();
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();


        for (int row = 0; row < layer1.getHeight(); row++){
            for(int col = 0; col < layer1.getWidth(); col++){
                TiledMapTileLayer.Cell cell = layer1.getCell(col, row);

                if (cell == null) continue;
                if (cell.getTile() == null) continue;

                bdef.type = BodyDef.BodyType.StaticBody;
                bdef.position.set((col + .5f)*tileSize/PPM, (row + .5f)*tileSize/PPM);


                if (cell.getTile().getProperties().get("type").equals("crate")){
                    ChainShape shape = new ChainShape();
                    Vector2[] v = new Vector2[4];
                    v[0] = new Vector2(-tileSize/2/PPM, -tileSize/2/PPM);
                    v[1] = new Vector2(-tileSize/2/PPM, tileSize/2/PPM);
                    v[2] = new Vector2(tileSize/2/PPM, tileSize/2/PPM);
                    v[3] = new Vector2(tileSize/2/PPM, -tileSize/2/PPM);


                    shape.createLoop(v);

                    fdef.shape = shape;
                    fdef.filter.categoryBits = BIT_GROUND;
                    fdef.filter.maskBits = BIT_RAT;
                    fdef.isSensor = false;
                    fdef.restitution = 0;



                    body = world.createBody(bdef);
                    body.createFixture(fdef).setUserData("crate");
                }

                if (cell.getTile().getProperties().get("type").equals("toca")){
                    ChainShape shape = new ChainShape();
                    Vector2[] v = new Vector2[4];
                    v[0] = new Vector2(-tileSize/2/PPM, -tileSize/2/PPM);
                    v[1] = new Vector2(-tileSize/2/PPM, tileSize/2/PPM);
                    v[2] = new Vector2(tileSize/2/PPM, tileSize/2/PPM);
                    v[3] = new Vector2(tileSize/2/PPM, -tileSize/2/PPM);


                    shape.createLoop(v);

                    fdef.shape = shape;
                    fdef.filter.categoryBits = BIT_TOCA;
                    fdef.filter.maskBits = BIT_RAT;
                    fdef.isSensor = true;
                    fdef.restitution = 0;




                    body = world.createBody(bdef);
                    body.createFixture(fdef).setUserData("toca");
                }

                if (cell.getTile().getProperties().get("type").equals("jumper")){
                    ChainShape shape = new ChainShape();
                    Vector2[] v = new Vector2[4];
                    v[0] = new Vector2(-tileSize/2/PPM, -tileSize/2/PPM);
                    v[1] = new Vector2(-tileSize/2/PPM, tileSize/2/PPM);
                    v[2] = new Vector2(tileSize/2/PPM, tileSize/2/PPM);
                    v[3] = new Vector2(tileSize/2/PPM, -tileSize/2/PPM);


                    shape.createLoop(v);

                    fdef.shape = shape;
                    fdef.filter.categoryBits = BIT_GROUND;
                    fdef.filter.maskBits = BIT_RAT;
                    fdef.isSensor = false;
                    fdef.restitution = 1.5f;


                    body = world.createBody(bdef);
                    body.createFixture(fdef).setUserData("jumper");
                }

                if (cell.getTile().getProperties().get("type").equals("start")){
                    initialPosition = new Vector2((col + .5f)*tileSize/PPM, (row + .5f)*tileSize/PPM);
                }

            }
        }

        cheesesDef();


    }

    public void render(OrthographicCamera cam){
        renderer.setView(cam);
        renderer.render();
    }


    public void renderCheeses(SpriteBatch sb){
        for (Cheese cheese : cheeses){
            cheese.render(sb);
        }
    }

    public void updateCheeses(float dt){
        for (Cheese cheese : cheeses){
            cheese.update(dt);
        }
    }

    public void cheesesDef(){
        cheeses = new Array<Cheese>();

        totalCheese = 0;

        MapLayer objs = tiledMap.getLayers().get("cheeses");

        for (MapObject mo : objs.getObjects()){

            Cheese cheese = new Cheese(world);
            float x = mo.getProperties().get("x", Float.class)/PPM;
            float y = mo.getProperties().get("y", Float.class)/PPM;

            cheese.create(new Vector2(x, y));


            cheeses.add(cheese);
            totalCheese++;
        }
    }

    public void resetCheeses(){
        if(cheeses.size == 0){
            cheesesDef();
        }
        else{
            MapLayer objs = tiledMap.getLayers().get("cheeses");

            for (MapObject mo : objs.getObjects()){

                boolean isSet = false;

                for (Cheese cheese : cheeses){
                    if(cheese.body.getPosition().x == mo.getProperties().get("x", Float.class)/PPM && cheese.body.getPosition().x == mo.getProperties().get("x", Float.class)/PPM)
                        isSet = true;
                }

                if(!isSet){
                    Cheese cheese = new Cheese(world);
                    float x = mo.getProperties().get("x", Float.class)/PPM;
                    float y = mo.getProperties().get("y", Float.class)/PPM;

                    cheese.create(new Vector2(x, y));


                    cheeses.add(cheese);
                }
            }

        }
    }

}
