package com.game.main;

import java.awt.*;

public abstract class GameObject {
    /** 'protected' means it can only be accessed by the object
     * that inherits the GameObject */
    protected float x, y;
    protected ID id;
    protected float speedX, speedY;

    /** Create constructor for game object */
    public GameObject(float x, float y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }

    /** methods */
    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    /** change x/y position through 'setX/Y'
     * 'this.x/y' will take the x in 'protected int x/y'
     * and will change it to whatever we put in the setX/Y(parameter)*/
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }

    /** ID */
    public void setId(ID id){
        this.id = id;
    }
    public ID getId(){
        return id;
    }

    /** Speed */
    public void setSpeedX(int speedX){
        this.speedX = speedX;
    }
    public void setSpeedY(int speedY){
        this.speedY = speedY;
    }
    public float getSpeedX(){
        return speedX;
    }
    public float getSpeedY(){
        return speedY;
    }
}
