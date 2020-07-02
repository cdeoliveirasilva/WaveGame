package com.game.main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas {

    public Window(int width, int height, String title, Game game){
        /** JFrame adds frame for the game window
         * Set variable name to 'frame' */
        JFrame frame = new JFrame(title);

        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        /** Makes close/'x' button actually stop the game
         * operation with setDefaultCloseOperation */
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        /** setLocationRelativeTo(null) makes sure the window
         * doesn't appear in the top left corner of the screen,
         * but in the middle*/
        frame.setLocationRelativeTo(null);
        /** Adds game class to the frame */
        frame.add(game);
        /** Make frame visible, duh */
        frame.setVisible(true);
        /** Start game, duh*/
        game.start();

    }

}
