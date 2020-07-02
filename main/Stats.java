package com.game.main;

import java.awt.*;

public class Stats {

    // don't use static unless there's only one variable, like here
    public static float HEALTH = 100;

    private float healthBar = 255;

    private int score = 0;
    private int level = 1;

    public void tick(){
        HEALTH = Game.bind(HEALTH, 0, 100);
        healthBar = Game.bind(healthBar, 0, 255);
        healthBar = HEALTH * 2;

        score++;
    }

    public void render(Graphics g){
        // HEALTH BAR
        g.setColor(Color.black);
        g.fillRect(15, 15, 200,20);
        g.setColor(Color.getHSBColor( (1f * (int)HEALTH) / 360, 1f, 1f));
        g.fillRect(15, 15, (int)HEALTH * 2,20);
        g.setColor(Color.white);
        g.drawRect(15, 15, 200,20);

        // SCORE STATS
        g.drawString("Score: " + score, 15,60);
        g.drawString("Level: " + level, 15,80);
    }

    void setScore(int score){
        this.score = score;
    }

    public int getLevel(){
        return level;
    }

    public void setLevel(int level){
        this.level = level;
    }

}
