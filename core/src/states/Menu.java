package states;

import bodyPack.Cheese;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.CheeseGame;
import handlers.GameInputs;
import handlers.GameStateManager;

/**
 * Created by Gabriel on 26/10/2016.
 */
public class Menu extends GameState {

    boolean[] selected;
    final int NUM_BUTTONS = 3;
    final int BTN_PLAY = 0;
    final int BTN_CRETIDS = 1;
    final int BTN_EXIT = 2;
    BitmapFont font;




    public Menu(GameStateManager gsm){
        super(gsm);


        selected = new boolean[NUM_BUTTONS];
        selected[BTN_PLAY] = true;

        font = new BitmapFont();


    }

    @Override
    public void handlerInput() {
        if(GameInputs.isPressed(GameInputs.ARROW_DOWN)){

            for(int i = 0; i < NUM_BUTTONS; i++){
                if(selected[i]){
                    selected[i] = false;
                    if(i != NUM_BUTTONS-1) {
                        selected[i+1] = true;
                        break;
                    }
                    else {
                        selected[0] = true;
                        break;
                    }
                }
            }
        }

        if(GameInputs.isPressed(GameInputs.ARROW_UP)){

            for(int i = 0; i < NUM_BUTTONS; i++){
                if(selected[i]){
                    selected[i] = false;
                    if(i != 0) {
                        selected[i-1] = true;
                        break;
                    }
                    else {
                        selected[NUM_BUTTONS - 1] = true;
                        break;
                    }
                }
            }
        }

        if(GameInputs.isPressed(GameInputs.ACTION1)){
            if (selected[BTN_PLAY]) gsm.setState(gsm.SELECT);
            if (selected[BTN_CRETIDS]) {

            }
            if (selected[BTN_EXIT]) {
            }
        }

    }

    @Override
    public void update(float dt) {

        handlerInput();
    }

    @Override
    public void render() {

        batch.setProjectionMatrix(game.getHudCam().combined);
        draw(batch);

    }

    @Override
    public void dispose() {

    }

    public void draw(SpriteBatch sb){

        float btnWidth = CheeseGame.res.getTexture("btnD").getWidth();

        float btnCol = 200  - btnWidth/2;


        sb.begin();

        sb.draw(CheeseGame.res.getTexture("fundoMenu"), 0, 0, 400, 300);
        sb.draw(CheeseGame.res.getTexture("logo"), 200 - CheeseGame.res.getTexture("logo").getWidth()/2, 160);


        if (selected[BTN_PLAY]) sb.draw(CheeseGame.res.getTexture("btnS"), btnCol, 140);
        else sb.draw(CheeseGame.res.getTexture("btnD"), btnCol, 140);

        if(selected[BTN_CRETIDS]) sb.draw(CheeseGame.res.getTexture("btnS"), btnCol, 120);
        else sb.draw(CheeseGame.res.getTexture("btnD"), btnCol, 120);

        if (selected[BTN_EXIT]) sb.draw(CheeseGame.res.getTexture("btnS"), btnCol, 100);
        else sb.draw(CheeseGame.res.getTexture("btnD"), btnCol, 100);

        font.draw(sb, "Play", 185, 156.5f);
        font.draw(sb, "Credits", 176, 136.5f);
        font.draw(sb, "Exit", 186.5f, 116.5f);




        sb.end();




    }
}
