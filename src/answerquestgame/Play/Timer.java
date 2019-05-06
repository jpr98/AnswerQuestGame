/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package answerquestgame.Play;

import answerquestgame.Helpers.Assets;
import answerquestgame.Helpers.Item;
import java.awt.Graphics;

/**
 *
 * @author juanpabloramos
 */
public class Timer extends Item {
    private int height;
    private int width;
    private int time;
    private int frames;
    private boolean canMove;
    private Player player;

    /**
     * Creates a timer object
     * @param x
     * @param y
     * @param height
     * @param width
     * @param time
     * @param player 
     */
    public Timer(int x, int y, int height, int width, int time, Player player) {
        super(x, y);
        this.height = height;
        this.width = width;
        this.time = time;
        this.player = player;
        frames = 0;
        canMove = true;
    }

    /**
     * Gets time
     * @return time
     */   
    public int getTime() {
        return time;
    }

    /**
     * Sets time
     * @param time
     */   
    public void setTime(int time) {
        this.time = time;
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
     * @param height
     */
    public void setHeight(int height){
        this.height = height;
    }

    /**
     * Sets width
     * @param width
     */
    public void setWidth(int width){
        this.width = width;
    }

    /**
     * Sets if the timer is enabled
     * @param canMove 
     */
    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    /**
     * Moves the timer
     */
    private void moveTimer() {
        setWidth(getWidth()-1);
    }

    /**
     * Makes changes to object each frame
     */
    @Override
    public void tick() {
        if (canMove) {
            frames++;
            // change number to increase difficulty
            if (frames == 2) {
                moveTimer();
                frames = 0;
            }
            // Notify player that timer ended
            if (getWidth() <= 0) {
                setWidth(295);
                player.setTimesup(true);
            }
        }
    }

    /**
     * Draws objects for each frame
     * @param g 
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.timer, getX(), getY(), getWidth(), getHeight(), null);
    }
    
}
