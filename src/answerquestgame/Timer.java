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
public class Timer extends Item {
    private int height;
    private int width;
    private int time;
    private int currentTime;
    private Player player;
    private int frames;

    public Timer(int x, int y, int height, int width, int time) {
        super(x, y);
        this.height = height;
        this.width = width;
        this.time = time;
        frames = 0;
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

    private void moveTimer() {
        setWidth(getWidth()-1);
    }

    @Override
    public void tick() {
        frames++;
        // change number to increase difficulty
        if (frames == 2) {
            moveTimer();
            frames = 0;
        }
        if (getWidth() <= 0) {
            setWidth(295);
            // notify player that timer reached 0
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.timer, getX(), getY(), getWidth(), getHeight(), null);
    }
    
}
