package handlers;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Gabriel on 23/10/2016.
 */
public class GameInputs {
    public static boolean[] keys;
    public static boolean[] pkeys;

    public static final int NUM_KEYS = 9;

    public static final int ARROW_UP = 0;
    public static final int ARROW_RIGHT = 1;
    public static final int ARROW_DOWN = 2;
    public static final int ARROW_LEFT = 3;
    public static final int ACTION1 = 4;
    public static final int ACTION2 = 5;
    public static final int RESET = 6;
    public static final int MOUSE_LEFT = 7;
    public static Vector2 MOUSE_LEFT_CORDS = new Vector2();
    public static final int MOUSE_RIGHT = 8;
    public static Vector2 MOUSE_RIGHT_CORDS = new Vector2();

    static {
        keys = new boolean[NUM_KEYS];
        pkeys = new boolean[NUM_KEYS];
    }

    public static void update() {
        for(int i = 0; i < NUM_KEYS; i++){
            pkeys[i] = keys[i];
        }
    }

    public static void setKey(int i, boolean b){
        keys[i] = b;
    }

    public static boolean isDown(int i){
        return keys[i];
    }

    public static boolean isPressed(int i){
        return keys[i] && !pkeys[i];
    }

}
