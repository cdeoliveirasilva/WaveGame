package com.game.main;

import java.awt.*;
import java.util.Random;

public class Player extends GameObject {

    Random r = new Random();
    Handler handler;

    public Player(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 32, 32);
    }

    public void tick(){
        x += speedX;
        y += speedY;

        // bind Player1 to confinements of game window
        x = Game.bind(x, 0, Game.WIDTH - 37);
        y = Game.bind(y, 0, Game.HEIGHT - 54);

        // call new method: collision
        collision();
    }

    private void collision() {
        for (int i = 0; i < handler.object.size() ; i++) {

            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ID.Enemy1 || tempObject.getId() == ID.Enemy2 || tempObject.getId() == ID.Enemy3 ){  // tempObject now references enemy
                if(getBounds().intersects(tempObject.getBounds())){
                    // collision code
                    Stats.HEALTH -= 2;
                }
            }

        }
    }

    public void render(Graphics g){
        if(id == ID.Player1) g.setColor(Color.white);
        g.fillRect((int)x, (int)y, 32, 32);

//      To actually see bounds:
//      Graphics2D g2d = (Graphics2D) g;
//      g.setColor(Color.orange);
//      g2d.draw(getBounds());

    }
}
