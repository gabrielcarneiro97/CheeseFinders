package states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.CheeseGame;
import handlers.GameStateManager;

/**
 * Created by Gabriel on 23/10/2016.
 */
public abstract class GameState {
    protected GameStateManager gsm;
    protected CheeseGame game;
    public World world;


    public SpriteBatch batch;
    protected OrthographicCamera cam;
    protected OrthographicCamera hudCam;

    protected GameState(GameStateManager gsm){
        this.gsm = gsm;
        game = gsm.game();
        batch = game.getBatch();
        cam = game.getCam();
        hudCam = game.getHudCam();
    }

    public abstract void handlerInput();
    public abstract void update(float dt);
    public abstract void render();
    public abstract void dispose();
}
