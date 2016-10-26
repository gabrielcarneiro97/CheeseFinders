package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import handlers.GameInputs;
import handlers.GameStateManager;

/**
 * Created by Gabriel on 26/10/2016.
 */
public class Menu extends GameState {

    public Menu(GameStateManager gsm){
        super(gsm);




    }

    @Override
    public void handlerInput() {
        if(GameInputs.isDown(GameInputs.MOUSE_LEFT)){
            System.out.println(GameInputs.MOUSE_LEFT_CORDS);
            GameInputs.setKey(GameInputs.MOUSE_LEFT, false);
        }
    }

    @Override
    public void update(float dt) {




        handlerInput();
    }

    @Override
    public void render() {

        batch.setProjectionMatrix(game.getHudCam().combined);
        design(batch);

    }

    @Override
    public void dispose() {

    }

    public void design(SpriteBatch sb){


        BitmapFont font = new BitmapFont(Gdx.files.internal("desktop/assets/fonts/big_noodle_titling.ttf"), false);

        sb.begin();
        font.draw(sb, "CheeseFinders!", 100, 150);
        sb.end();


    }
}
