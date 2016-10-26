package handlers;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Gabriel on 23/10/2016.
 */
public class GameContactListener implements ContactListener {

    private int isOnFloor1;
    private int isOnFloor2;
    public Array<Body> cheeseRemove = new Array<Body>();
    public boolean atToca;

    @Override
    public void beginContact(Contact contact) {

        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();


        if(a.getUserData() != null && a.getUserData().equals("Foot1")) isOnFloor1++;
        if(b.getUserData() != null && b.getUserData().equals("Foot1")) isOnFloor1++;


        if(a.getUserData() != null && a.getUserData().equals("Foot2")) isOnFloor2++;
        if(b.getUserData() != null && b.getUserData().equals("Foot2")) isOnFloor2++;

//        if(!a.getUserData().equals("rope") || !b.getUserData().equals("rope"))
//            System.out.println(a.getUserData() +  ", " + b.getUserData());


        if(a.getUserData().equals("cheese") && (b.getUserData().equals("Mouse1") || b.getUserData().equals("Mouse2"))){
            cheeseRemove.add(a.getBody());
        }
        if(b.getUserData().equals("cheese") && (a.getUserData().equals("Mouse1") || a.getUserData().equals("Mouse2"))){
            cheeseRemove.add(b.getBody());
        }


        if(a.getUserData().equals("toca") && (b.getUserData().equals("Mouse1") || b.getUserData().equals("Mouse2"))){
            atToca = true;
        }
        if(b.getUserData().equals("toca") && (a.getUserData().equals("Mouse1") || a.getUserData().equals("Mouse2"))){
            atToca = true;
        }

    }

    @Override
    public void endContact(Contact contact) {

        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();


        if(a.getUserData() != null && a.getUserData().equals("Foot1")) isOnFloor1--;
        if(b.getUserData() != null && b.getUserData().equals("Foot1")) isOnFloor1--;


        if(a.getUserData() != null && a.getUserData().equals("Foot2")) isOnFloor2--;
        if(b.getUserData() != null && b.getUserData().equals("Foot2")) isOnFloor2--;

        if(a.getUserData().equals("toca") && (b.getUserData().equals("Mouse1") || b.getUserData().equals("Mouse2"))){
            atToca = false;
        }
        if(b.getUserData().equals("toca") && (a.getUserData().equals("Mouse1") || a.getUserData().equals("Mouse2"))){
            atToca = false;
        }

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    public boolean grounded(int i){

        if(i == 1) return isOnFloor1 > 0;
        else if(i == 2) return isOnFloor2 > 0;

        return false;

    }


}
