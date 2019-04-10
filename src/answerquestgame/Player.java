/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package answerquestgame;

import java.awt.Graphics;

/**
 *
 * @author juanpabloramos
 */
public class Player extends Item {
    private int height;
    private int width;
    private Game game;
    private boolean isPlayer1;
    private int moveCounter;
    private boolean canMove;
    private Button leftButton;
    private Button rightButton;
    private Timer timer;
    
    /**
     * Constructor
     * @param width
     * @param height
     * @param game
     */
    public Player(int x, int y, int height, int width, boolean isPlayer1, Game game) {
        super(x, y);
        this.height = height;
        this.width = width;
        this.isPlayer1 = isPlayer1;
        this.game = game;
        moveCounter = 0;
        canMove = true;
        setButtons();
        setTimer();
    }

     /**
     * Gets height
     * @return height
     */   
    public int getHeight(){
        return height;
    }
    
    /**
     * Gets width
     * @return width
     */
    public int getWidth(){
        return width;
    }

    /**
     * Sets height
     * @return height
     */
    public void setHeight(int height){
        this.height = height;
    }

    /**
     * Sets width
     * @return width
     */
    public void setWidth(int width){
        this.width = width;
    }

    public Game getGame() {
        return game;
    }

    public boolean isPlayer1() {
        return isPlayer1;
    }

    public void canMove(boolean canMove) {
        this.canMove = canMove;
    }

    /**
     * 
     */
    private void setButtons() {
        if (isPlayer1) {
            leftButton = new Button(17, 677, 90, 125, true, this);
            rightButton = new Button(154, 677, 90, 125, false, this);
        } else {
            leftButton = new Button(318, 677, 90, 125, true, this);
            rightButton = new Button(455, 677, 90, 125, false, this);
        }
    }

    /**
     * 
     */
    private void setTimer() {
        if (isPlayer1) {
            timer = new Timer(0, 53, 14, 295, 10);
        } else {
            timer = new Timer(304, 53, 14, 296, 10);
        }
    }

    public void stop() {
        canMove(false);
        timer.setCanMove(false);
        leftButton.setCanMove(false);
        rightButton.setCanMove(false);
    }

    public void start() {
        canMove(true);
        timer.setCanMove(true);
        leftButton.setCanMove(true);
        rightButton.setCanMove(true);
    }

    public void drop() {
        stop();
        int time = 300;
        while (time > 0) {
            setY(getY()+1);
        }
        start();
    }

    /**
     * Moves the player over a period of time
     */
    private void moving() {
        if (moveCounter > 0) {
            setY(getY()-1);
            moveCounter--;
        }
    }

    /**
     * Notifying observers that player won
     */
    private void reachTop() {
        game.setPlayer1Won(true);
        timer.setCanMove(false);
    }

    /**
     * Makes changes to objects each frame
     */
    @Override
    public void tick() {
        // Checking if player reaches top
        if (getY() + getHeight() < 179) {
            // manage winning level
            reachTop();
        } 

        // Moving player with corresponding keys
        if (isPlayer1 && canMove) {
            if (game.getKeyManager().left) {
                moveCounter = 80;
                timer.setWidth(295);
            }
            if (game.getKeyManager().right) {
                moveCounter = 80;
                timer.setWidth(295);
            }
            moving();
        } else if (!isPlayer1 && canMove){
            if (game.getKeyManager().a) {
                moveCounter = 80;
                timer.setWidth(295);
            }
            if (game.getKeyManager().d) {
                moveCounter = 80;
                timer.setWidth(295);
            }
            moving();
        }
        timer.tick();
    }

    /**
     * Draw images for each frame
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.balloon, getX(), getY(), getWidth(), getHeight(), null);
        leftButton.render(g);
        rightButton.render(g);
        timer.render(g);
    }
    
}
