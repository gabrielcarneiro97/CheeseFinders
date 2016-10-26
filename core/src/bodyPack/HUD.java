package bodyPack;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.CheeseGame;
import states.Play;

/**
 * Created by Gabriel on 25/10/2016.
 */
public class HUD {

    Play play;


    public HUD(Play play){
        this.play = play;
    }


    public void render(SpriteBatch sb){
        BitmapFont font;

        font = new BitmapFont();

        Texture cheese = CheeseGame.res.getTexture("cheese");


        sb.begin();
        font.draw(sb, play.collectedCheese + "/" + play.totalCheese, 335, 270);
        sb.draw(cheese, 358, 256, 15, 15);
        sb.end();


    }

}
