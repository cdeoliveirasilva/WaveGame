package com.game.main;
import java.awt.*;

public class Enemy2 extends GameObject {

    private Handler handler;

    public Enemy2(int x, int y, ID id, Handler handler){
        super(x, y, id);

        this.handler = handler;

        speedX = 3;
        speedY = 8;
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 16, 16);
    }

    public void tick(){
        x += speedX;
        y += speedY;

        if(y <= 0 || y >= Game.HEIGHT - 32) speedY *= -1;
        if(x <= 0 || x >= Game.WIDTH - 16) speedX *= -1;
        /**
         z = speedY * -1
         Upward:    z = - 5 * -1 = 5
         Downward:  z = 5 * -1 = -5
         */

        handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.blue, 16, 16, 0.02f, handler));

    }

    public void render(Graphics g){
        g.setColor(Color.blue);
        g.fillRect((int)x, (int)y, 16, 16);
    }
}