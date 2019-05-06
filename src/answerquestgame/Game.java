/*
 * Game class creates the objects, manages the interactions between them,
 * and pauses, restarts and ends the game
 */
package answerquestgame;

import answerquestgame.Helpers.*;
import answerquestgame.Play.*;
import answerquestgame.Play.Level.LevelNumber;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
    private Level level;
    private ScreenType screen;
    private Menu menu;
    private int sleep;
    
    private Database database;

    public enum ScreenType {
        MENU, LEVEL, TUTORIAL, LEADERBOARD
    }
    

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
        screen = ScreenType.MENU;
        sleep = 0;
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
    
    public void setScreen(ScreenType type) {
        this.screen = type;
    }
    
    public void setSleep() {
        sleep = 0;
    }
    
    public Database getDatabase() {
        return database;
    }

    /**
     * Initializer, create game figures and display
     */
    private void init() {
        display = new Display(title, width, height);
        setupListeners();
        Assets.init();
        level = new Level(LevelNumber.ONE, this);
        level.init();
        menu = new Menu(this);
        menu.init();
        database = new Database();
    }

    /**
     * Setup mouse and key listeners to display
     * To be used in init() only
     */
    private void setupListeners() {
        display.getJframe().addKeyListener(keyManager);
        display.getJframe().addMouseListener(mouseManager);
        display.getJframe().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
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
        switch(screen) {
            case MENU:
                menu.tick();
                
                if (menu.getStartButton().isPressed() && sleep > 5) {
                    level.setSleep();
                    setScreen(ScreenType.LEVEL);
                    database.insertPlayerData("JP", 12);
                }
                sleep++;
                break;
            case LEVEL:
                level.tick();
                break;
            case TUTORIAL:
                break;
            case LEADERBOARD:
                break;
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
            switch(screen) {
                case MENU:
                    g = bs.getDrawGraphics();
                    g.clearRect(0,0, width,height);
                    menu.render(g);
                    bs.show();
                    g.dispose();
                    break;
                case LEVEL:
                    g = bs.getDrawGraphics();
                    g.clearRect(0,0, width,height);
                    level.render(g);
                    bs.show();
                    g.dispose();
                    break;
                case TUTORIAL:
                    break;
                case LEADERBOARD:
                    break;
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