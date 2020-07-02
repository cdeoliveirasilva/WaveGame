package com.game.main;
import java.awt.*;

public class Enemy3 extends GameObject {

    private Handler handler;
    private GameObject player;

    public Enemy3(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;

        for(int i = 0; i < handler.object.size(); i++){
            if(handler.object.get(i).getId() == ID.Player1)
                player = handler.object.get(i);
        }
        /** calculate the distance between player and Enemy3 */
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 16, 16);
    }

    public void tick(){
        x += speedX;
        y += speedY;

        float differenceX = x - player.getX() - 8;
        float differenceY = y - player.getY() - 8;
        float distance = (float) Math.sqrt( (x-player.getX()) * (x-player.getX()) + (y-player.getY()) * (y-player.getY()));

        speedX = (-2/distance) * differenceX;
        speedY = (-2/distance) * differenceY;

//        if(y <= 0 || y >= Game.HEIGHT - 32) speedY *= -1;
//        if(x <= 0 || x >= Game.WIDTH - 16) speedX *= -1;

        handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.orange, 16, 16, 0.02f, handler));

    }

    public void render(Graphics g){
        g.setColor(Color.orange);
        g.fillRect((int)x, (int)y, 16, 16);
    }
}