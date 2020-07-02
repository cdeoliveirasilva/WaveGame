package com.game.main;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    LinkedList<GameObject> object = new LinkedList<GameObject>();

    public void tick(){
        for(int i = 0; i < object.size(); i++){
            // Reset temporaryObject to object.get(i),
            // which is a function within LinkedList that gets
            // the id of the object you're at
            // 'i' runs through the entire list of objects */
            GameObject tempObject = object.get(i);

            tempObject.tick();
        }
    }

    public void render(Graphics g){
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);

            tempObject.render(g);
        }
    }

    public void clearEnemies(){
        for (int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);

            if(tempObject.getId() == ID.Player1){
                object.clear();
                if(Game.gameState != Game.STATE.GameOver) {
                    addObject(new Player((int)tempObject.getX(), (int)tempObject.getY(), ID.Player1, this));
                }
            }
        }
    }

    // Add and remove objects to the list
    public void addObject(GameObject object){
        this.object.add(object);
    }
    public void removeObject(GameObject object){
        this.object.remove(object);
    }

}
