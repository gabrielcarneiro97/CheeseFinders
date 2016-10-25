package handlers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

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


        return true;

    }
}
