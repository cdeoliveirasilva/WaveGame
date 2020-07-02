package com.game.main;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
    private static int var;

    // initialization
    private Thread thread;
    private boolean running = false;

    private Random r;
    private Handler handler;
    private Stats stats;
    private Spawn spawn;
    private Menu menu;

    public enum STATE {
        Menu,
        Game,
        GameOver,
    };

    public static STATE gameState = STATE.Menu;
    // this will allow STATE to be cast as type
    // that can hold Menu and Game

    public Game() {
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));

        new Window(WIDTH, HEIGHT, "The Wave Game", this);
        stats = new Stats();
        menu = new Menu(this, handler, stats);
        this.addMouseListener(menu);

        spawn = new Spawn(handler, stats);
        r = new Random();

        if (gameState == STATE.Game)
        {
            handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player1, handler));
            handler.addObject(new Enemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.Enemy1, handler));
        } else {
            for (int i = 0; i < 4 ; i++){
                handler.addObject(new MenuStyling(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuStyling, handler));
            }
            for (int i = 0; i < 4 ; i++) {
                handler.addObject(new MenuStyling(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuStyling, handler));
            }
        }
    }

    public synchronized void start(){
        // start will initialize the thread
        // 'this' refers to the Game class
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        // 'try' is like an if statement
        //'tread.join()' kills the thread/stops it from running
        try{
            thread.join();
            running = false;
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    /** creating game loop */
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
//                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){
        handler.tick();
        if(gameState == STATE.Game){
            stats.tick();
            spawn.tick();

            if(Stats.HEALTH <=0){
                Stats.HEALTH = 100;
                gameState = STATE.GameOver;
                handler.clearEnemies();
                for (int i = 0; i < 8 ; i++) {
                    handler.addObject(new MenuStyling(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuStyling, handler));
                }
            }

        } else if(gameState == STATE.Menu || gameState == STATE.GameOver){
            menu.tick();
        }
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect((int)0,(int)0, WIDTH, HEIGHT);

        handler.render(g);

        if(gameState == STATE.Game){
            stats.render(g);
        } else if(gameState == STATE.Menu || gameState == STATE.GameOver){
            menu.render(g);
        }

        g.dispose();
        bs.show();
    }

    // create static method
    public static float bind(float var, float min, float max){
        // has to return integer value
        if(var >= max)
            return var = max;
        else if(var <= min)
            return var = min;
        else
            return var;
    }

    // Creates new Game in the 'main' method, which will call
    // a constructor in the Game class
    public static void main(String[] args){
        new Game();
    }
}
