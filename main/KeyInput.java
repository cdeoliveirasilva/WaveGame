package com.game.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;

    public  KeyInput(Handler handler){
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
//        System.out.println(key);

        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.Player1){
                /** here all key events for Player1:
                 * add keyboard input */
                if(key == KeyEvent.VK_UP) tempObject.setSpeedY(-5);
                if(key == KeyEvent.VK_DOWN) tempObject.setSpeedY(5);
                if(key == KeyEvent.VK_RIGHT) tempObject.setSpeedX(5);
                if(key == KeyEvent.VK_LEFT) tempObject.setSpeedX(-5);
                }
        }
        /** exit game by pressing escape key */
        if(key == KeyEvent.VK_ESCAPE) System.exit(1);
    }

        public void keyReleased (KeyEvent e){
            int key = e.getKeyCode();

            for(int i = 0; i < handler.object.size(); i++){
                GameObject tempObject = handler.object.get(i);

                if(tempObject.getId() == ID.Player1) {
                    /** here all key events for Player1:
                     * add keyboard input */
                    if(key == KeyEvent.VK_UP) tempObject.setSpeedY(0);
                    if(key == KeyEvent.VK_DOWN) tempObject.setSpeedY(0);
                    if(key == KeyEvent.VK_LEFT) tempObject.setSpeedX(0);
                    if(key == KeyEvent.VK_RIGHT) tempObject.setSpeedX(0);
                }
            }
        }
    }