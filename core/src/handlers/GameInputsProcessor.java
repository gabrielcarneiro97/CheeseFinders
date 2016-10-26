package handlers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Gabriel on 23/10/2016.
 */
public class GameInputsProcessor extends InputAdapter {

    public boolean keyDown(int k){
        if(k == Input.Keys.UP) {
            GameInputs.setKey(GameInputs.ARROW_UP, true);
        }
        if(k == Input.Keys.RIGHT){
            GameInputs.setKey(GameInputs.ARROW_RIGHT, true);
        }
        if(k == Input.Keys.DOWN){
            GameInputs.setKey(GameInputs.ARROW_DOWN, true);
        }
        if(k == Input.Keys.LEFT){
            GameInputs.setKey(GameInputs.ARROW_LEFT, true);
        }
        if(k == Input.Keys.Z){
            GameInputs.setKey(GameInputs.ACTION1, true);
        }
        if(k == Input.Keys.X){
            GameInputs.setKey(GameInputs.ACTION2, true);
        }
        if(k == Input.Keys.R){
            GameInputs.setKey(GameInputs.RESET, true);
        }



        return true;
    }

    public boolean keyUp(int k){

        if(k == Input.Keys.UP) {
            GameInputs.setKey(GameInputs.ARROW_UP, false);
        }
        if(k == Input.Keys.RIGHT){
            GameInputs.setKey(GameInputs.ARROW_RIGHT, false);
        }
        if(k == Input.Keys.DOWN){
            GameInputs.setKey(GameInputs.ARROW_DOWN, false);
        }
        if(k == Input.Keys.LEFT){
            GameInputs.setKey(GameInputs.ARROW_LEFT, false);
        }
        if(k == Input.Keys.Z){
            GameInputs.setKey(GameInputs.ACTION1, false);
        }
        if(k == Input.Keys.X){
            GameInputs.setKey(GameInputs.ACTION2, false);
        }
        if(k == Input.Keys.R){
            GameInputs.setKey(GameInputs.RESET, false);
        }


        return true;

    }

    @Override
    public boolean touchDown (int x, int y, int pointer, int button) {
        if (button == Input.Buttons.LEFT) {
            GameInputs.setKey(GameInputs.MOUSE_LEFT, true);
            GameInputs.MOUSE_LEFT_CORDS = new Vector2(x, y);
            return true;
        }

        if(button == Input.Buttons.RIGHT){
            GameInputs.setKey(GameInputs.MOUSE_RIGHT, true);
            GameInputs.MOUSE_RIGHT_CORDS = new Vector2(x, y);
            return true;
        }
        return false;
    }
}
