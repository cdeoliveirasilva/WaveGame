package com.game.main;

import java.util.Random;

public class Spawn {

    private Handler handler;
    private Stats stats;
    private Random r = new Random();

    private int scoreKeeper = 0;

    public Spawn(Handler handler, Stats stats){
        this.handler = handler;
        this.stats = stats;
    }

    public void tick(){
        scoreKeeper++;

        if(scoreKeeper >= 200) {
            scoreKeeper = 0;
            stats.setLevel(stats.getLevel() + 1);

            if (stats.getLevel() == 2) {
                handler.addObject(new Enemy(r.nextInt(Game.WIDTH - 30), r.nextInt(Game.HEIGHT - 30), ID.Enemy1, handler));
            } else if (stats.getLevel() == 3) {
                handler.addObject(new Enemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 20), ID.Enemy1, handler));
            } else if (stats.getLevel() == 4) {
                // for (int i = 0; i < 2; i++)
                handler.addObject(new Enemy2(r.nextInt(Game.WIDTH - 40), r.nextInt(Game.HEIGHT - 40), ID.Enemy2, handler));
            } else if (stats.getLevel() == 5) {
                handler.addObject(new Enemy3(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 20), ID.Enemy3, handler));
            }
        }
    }

}
