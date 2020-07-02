package com.game.main;
import java.awt.*;
import java.util.Random;

public class MenuStyling extends GameObject {

    private Handler handler;

    Random r = new Random();

    private Color color;

    int direction = 0;

    public MenuStyling(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;

        direction = r.nextInt(2);
        if(direction == 0){
            speedX = 3;
            speedY = 4;
        } else if(direction == 1){
            speedX = 4;
            speedY = 3;
        }

        color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 16, 16);
    }

    public void tick(){
        x += speedX;
        y += speedY;

        if(y <= 0 || y >= Game.HEIGHT - 32) speedY *= -1;
        if(x <= 0 || x >= Game.WIDTH - 16) speedX *= -1;

        handler.addObject(new Trail((int)x, (int)y, ID.Trail, color, 16, 16, 0.02f, handler));

    }

    public void render(Graphics g){
        g.setColor(color); //random color
        g.fillRect((int)x, (int)y, 16, 16);
    }
}