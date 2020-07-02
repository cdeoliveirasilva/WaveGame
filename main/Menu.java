package com.game.main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.game.main.Game.STATE;

public class Menu extends MouseAdapter {

    private Game game;
    private Handler handler;
    private Stats stats;
    private Random r = new Random();

    public Menu(Game game, Handler handler, Stats stats){
        this.game = game;
        this.stats = stats;
        this.handler = handler;
    }

    public void mousePressed(MouseEvent e){
        int mouseX = e.getX();
        int mouseY = e.getY();

        if(game.gameState == STATE.Menu) {
            // play button
            if (mouseOver(mouseX, mouseY, 220, 150, 200, 64)) {
                game.gameState = STATE.Game;
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player1, handler));
                handler.clearEnemies();
                handler.addObject(new Enemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Enemy1, handler));
            }
        }

            if(game.gameState == STATE.GameOver) {
                // try again button
                if(mouseOver(mouseX, mouseY, 210, 260, 200, 64)) {
                    game.gameState = STATE.Game;
                    stats.setLevel(1);
                    stats.setScore(0);
                    handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player1, handler));
                    handler.clearEnemies();
                    handler.addObject(new Enemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Enemy1, handler));
                }
            }
        }

    public void mouseReleased(MouseEvent e){
    }

    private boolean mouseOver(int mouseX, int mouseY, int x, int y, int width, int height) {
        if (mouseX > x && mouseX < x + width){
            if(mouseY > y && mouseY < y + height){
            return true;
        } else return false;
    } else return false;
}

    public void tick(){
    }

    public void render(Graphics g) {
        if (Game.gameState == STATE.Menu) {
            // title
            Font fnt = new Font("arial", 3, 50);
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("WAVE GAME", 160, 100);

            // play button
            Font fnt2 = new Font("arial", 2, 35);
            g.setFont(fnt2);
            g.setColor(Color.white);
            g.drawRect(220, 150, 200, 64);
            g.drawString("play!", 285, 190);

            // game description
            Font fnt3 = new Font("arial", 3, 15);
            g.setFont(fnt3);
            g.setColor(Color.white);
            g.drawString("Press [play!] to start the game, or press [escape] key to exit game. ", 90, 300);
            g.drawString("To play, use arrow keys to move player and dodge enemies ", 110, 320);

        } else if(game.gameState == STATE.GameOver) {
            // game over
            Font fnt = new Font("arial", 3, 50);
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("GAME OVER! ", 150, 190);

            // try again
            Font fnt2 = new Font("arial", 2, 35);
            g.setFont(fnt2);
            g.setColor(Color.white);
            g.drawRect(210, 260, 200, 64);
            g.drawString("try again?", 235, 300);
        }
    }
}
