package handlers;

import com.mygdx.game.CheeseGame;
import states.GameState;
import states.Menu;
import states.Play;

import java.util.Stack;

/**
 * Created by Gabriel on 23/10/2016.
 */
public class GameStateManager {

    private CheeseGame game;
    private Stack<GameState> gameStates;

    public static final int PLAY = 1;
    public static final int MENU = 2;


    public GameStateManager(CheeseGame game){
        this.game = game;
        gameStates = new Stack<GameState>();
        pushState(MENU);
    }

    public CheeseGame game(){
        return this.game;
    }

    private GameState getState(int state){
        if(state == PLAY)
            return new Play(this);
        if(state == MENU)
            return new Menu(this);
        return null;
    }

    public void setState(int state){
        popState();
        pushState(state);
    }

    public void pushState(int state){
        gameStates.push(getState(state));
    }

    public void popState(){
        GameState g = gameStates.pop();
        g.dispose();
    }

    public void update(float dt){
        gameStates.peek().update(dt);
    }

    public void render(){
        gameStates.peek().render();
    }



}
