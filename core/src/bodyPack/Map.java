package bodyPack;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.CheeseGame;
import handlers.GameContactListener;
import states.GameState;

import static handlers.B2DVars.BIT_GROUND;
import static handlers.B2DVars.BIT_RAT;
import static handlers.B2DVars.PPM;

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

    Body lastCheese = null;


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



                    body = world.createBody(bdef);
                    body.createFixture(fdef).setUserData("crate");
                }

            }
        }

        cheeses = new Array<Cheese>();

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

}
