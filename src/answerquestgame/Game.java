/*
 * Game class creates the objects, manages the interactions between them,
 * and pauses, restarts and ends the game
 */
package answerquestgame;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class Game implements Runnable {
    private BufferStrategy bs;
    private Graphics g;
    private Display display;
    String title;
    private int width;
    private int height;
    private Thread thread;
    private boolean running;
    private KeyManager keyManager;
    private MouseManager mouseManager;
    private Player player1;
    private Player player2;
    private boolean player1Won;
    private Animation titleAnimation;
    private boolean isMenu;
    private MenuButton startButton;


    /**
     * Constructor
     * @param title
     * @param width
     * @param height
     */
    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        keyManager = new KeyManager();
        mouseManager = new MouseManager(); 
        isMenu = true;
    }

    /**
     * Return game width
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Return game height
     * @return height
     */
    public int getHeight() {
        return height;
    }
    
    public Display getDisplay() {
        return display;
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }
    
    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public void setPlayer1Won(boolean player1Won) {
        this.player1Won = player1Won;
    }

    /**
     * Initializer, create game figures and display
     */
    private void init() {
        display = new Display(title, width, height);
        Assets.init();
        player1 = new Player(96, 530, 110, 110, true, this);
        player2 = new Player(380, 530, 110, 110, false, this);
        startButton = new MenuButton(200, 100, (width/2 - 100), 250, this);
        player1Won = false;
        titleAnimation = new Animation(Assets.titleMoving, 100);
        display.getJframe().addKeyListener(keyManager);
    }

    /**
     * Restarts the game and sets the variables to initial values
     */
    private void restart() {
    }

    private void saveGame() {
        try {
            FileWriter file = new FileWriter("save.txt");

            file.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void loadGame() {
        try {
            BufferedReader file = new BufferedReader(new FileReader("save.txt"));

            file.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Starts the game
     */
    @Override
    public void run() {
        init();
        int fps = 60;
        double timeTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();

        while(running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timeTick;
            lastTime = now;
            if (delta > 0) {
                tick();
                render();
                delta--;
            }
        }
        stop();
    }

    /**
     * Makes changes to objects each frame
     */
    private void tick() {
        keyManager.tick();
        if (isMenu) {
            // Checks if mouse clicks on button
            int xCoord = getMouseManager().getX();
            int yCoord = getMouseManager().getY();
            if (getKeyManager().enter) {
                isMenu = false;
            }
        } else {
            player1.tick();
            player2.tick();
            if (player1Won) {
                player1.stop();
                player2.stop();
            }
        }
    }

    /**
     * Draw images for each frame
     */
    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        } else {
            if (isMenu) {
                g = bs.getDrawGraphics();
                g.clearRect(0,0, width,height);
                // render menu stuff
                g.drawImage(Assets.menuBackground, 0, 0, width, height, null);
                g.drawImage(Assets.startButton, (width/2 - 100), 250, 200, 100, null);
                bs.show();
                g.dispose();
            } else {
                g = bs.getDrawGraphics();
                g.clearRect(0,0, width,height);
                g.drawImage(Assets.background, 0, 0, width, height, null);
                player1.render(g);
                player2.render(g);
                bs.show();
                g.dispose();
            }
        }
    }

    /**
     * Create new thread for game to run on
     */
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * Stop game
     */
    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}