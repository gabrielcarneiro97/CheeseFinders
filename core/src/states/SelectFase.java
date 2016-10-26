package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.CheeseGame;
import handlers.GameInputs;
import handlers.GameStateManager;

/**
 * Created by Gabriel on 26/10/2016.
 */
public class SelectFase extends GameState{

    boolean[][] selected;
    final int NUM_FASES = 30;
    final Vector2 FASE_1 = new Vector2(0, 0);
    final Vector2 FASE_2 = new Vector2(0, 1);
    final Vector2 FASE_3 = new Vector2(0, 2);
    final Vector2 FASE_4 = new Vector2(0, 3);
    final Vector2 FASE_5 = new Vector2(0, 4);
    final Vector2 FASE_6 = new Vector2(0, 5);
    final Vector2 FASE_7 = new Vector2(0, 6);
    final Vector2 FASE_8 = new Vector2(0, 7);
    final Vector2 FASE_9 = new Vector2(0, 8);
    final Vector2 FASE_10 = new Vector2(0, 9);
    final Vector2 FASE_11 = new Vector2(1, 0);
    final Vector2 FASE_12 = new Vector2(1, 1);
    final Vector2 FASE_13 = new Vector2(1, 2);
    final Vector2 FASE_14 = new Vector2(1, 3);
    final Vector2 FASE_15 = new Vector2(1, 4);
    final Vector2 FASE_16 = new Vector2(1, 5);
    final Vector2 FASE_17 = new Vector2(1, 6);
    final Vector2 FASE_18 = new Vector2(1, 7);
    final Vector2 FASE_19 = new Vector2(1, 8);
    final Vector2 FASE_20 = new Vector2(1, 9);
    final Vector2 FASE_21 = new Vector2(2, 0);
    final Vector2 FASE_22 = new Vector2(2, 1);
    final Vector2 FASE_23 = new Vector2(2, 2);
    final Vector2 FASE_24 = new Vector2(2, 3);
    final Vector2 FASE_25 = new Vector2(2, 4);
    final Vector2 FASE_26 = new Vector2(2, 5);
    final Vector2 FASE_27 = new Vector2(2, 6);
    final Vector2 FASE_28 = new Vector2(2, 7);
    final Vector2 FASE_29 = new Vector2(2, 8);
    final Vector2 FASE_30 = new Vector2(2, 9);

    //FASE_X = FASE_X.x + 1 + FASE_X.y * 10;



    public SelectFase(GameStateManager gsm){
        super(gsm);

        selected = new boolean[10][3];
        selected[0][0] = true;


    }

    @Override
    public void handlerInput() {

    }

    @Override
    public void update(float dt) {

        if(GameInputs.isPressed(GameInputs.ARROW_UP)){

            for (int y = 0; y < 3; y ++) {

                boolean isSet = false;

                for (int x = 0; x < 10; x++) {
                    if(selected[x][y]){
                        selected[x][y] = false;
                        if(y != 2) {
                            selected[x][y+1] = true;
                            isSet = true;
                            break;
                        }
                        else {
                            selected[x][0] = true;
                            isSet = true;
                            break;
                        }

                    }
                }

                if(isSet) break;
            }

        }
        if(GameInputs.isPressed(GameInputs.ARROW_DOWN)){

            for (int y = 0; y < 3; y ++) {

                boolean isSet = false;

                for (int x = 0; x < 10; x++) {
                    if(selected[x][y]){
                        selected[x][y] = false;
                        if(y != 0) {
                            selected[x][y-1] = true;
                            isSet = true;
                            break;
                        }
                        else {
                            selected[x][2] = true;
                            isSet = true;
                            break;
                        }

                    }
                }

                if(isSet) break;
            }

        }
        if(GameInputs.isPressed(GameInputs.ARROW_RIGHT)){

            for (int y = 0; y < 3; y ++) {

                boolean isSet = false;

                for (int x = 0; x < 10; x++) {
                    if(selected[x][y]){
                        selected[x][y] = false;
                        if(x != 9) {
                            selected[x+1][y] = true;
                            isSet = true;
                            break;
                        }
                        else {
                            selected[0][y] = true;
                            isSet = true;
                            break;
                        }

                    }
                }

                if(isSet) break;
            }

        }
        if(GameInputs.isPressed(GameInputs.ARROW_LEFT)){

            for (int y = 0; y < 3; y ++) {
                boolean isSet = false;
                for (int x = 0; x < 10; x++) {
                    if(selected[x][y]){
                        selected[x][y] = false;
                        if(x != 0) {
                            selected[x-1][y] = true;
                            isSet = true;
                            break;
                        }
                        else {
                            selected[9][y] = true;
                            isSet = true;
                            break;
                        }

                    }
                }

                if(isSet) break;
            }

        }
        if(GameInputs.isPressed(GameInputs.ACTION1)){

            for (int y = 0; y < 3; y ++) {
                for (int x = 0; x < 10; x++) {
                    if(selected[x][y]){
                        int fase = (x + 1) + (y * 10);
                        gsm.setFase(fase);
                        gsm.setState(gsm.PLAY);

                    }
                }
            }
        }


        handlerInput();
    }

    @Override
    public void render() {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        draw(batch);


    }

    @Override
    public void dispose() {

    }

    void draw(SpriteBatch sb){
        sb.begin();

        BitmapFont font = new BitmapFont();

        float btnWidth = CheeseGame.res.getTexture("btnD").getWidth();
        float btnHeight = CheeseGame.res.getTexture("btnD").getHeight();


        float btnCol = 63  - btnWidth/2;

        sb.draw(CheeseGame.res.getTexture("fundoMenu"), 0, 0, 400, 300);

        for (int y = 0; y < 3; y ++){
            for (int x = 0; x < 10; x++){
                //selected[x][y]
                if(selected[x][y]) sb.draw(CheeseGame.res.getTexture("btnFaseS"), btnCol + x*btnWidth/2, 120 + (y * (btnHeight*2)));
                else sb.draw(CheeseGame.res.getTexture("btnFaseD"), btnCol + x*btnWidth/2, 120 + (y * (btnHeight*2)));

                int fase = (x + 1) + (y * 10);

                if(y == 0 && x != 9) font.draw(sb, "" + fase, btnCol + x*btnWidth/2f + 8, 120f + (y * (btnHeight*2f)) + 19.5f);
                else font.draw(sb, "" + fase, btnCol + x*btnWidth/2f + 5, 120f + (y * (btnHeight*2f)) + 19.5f);
            }
        }





        sb.end();
    }
}
