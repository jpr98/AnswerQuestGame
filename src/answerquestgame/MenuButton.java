/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package answerquestgame;

import answerquestgame.Helpers.Assets;
import answerquestgame.Helpers.Item;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author juanpabloramos
 */
public class MenuButton extends Item {
    private int height;
    private int width;
    private Game game;
    private MenuButtonType type;
    
    public MenuButton(int x, int y, int height, int width, MenuButtonType type, Game game) {
        super(x, y);
        this.height = height;
        this.width = width;
        this.type = type;
        this.game = game;
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
     * Creates a Rectangle object the size of the ball
     * @return rectangle
     */
    public Rectangle getPerimeter() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public boolean isPressed() {
        int xCoord = game.getMouseManager().getX();
        int yCoord = game.getMouseManager().getY();
        boolean mouseClicked = game.getMouseManager().isLeft() || game.getMouseManager().isRight();
        return mouseClicked && getPerimeter().contains(xCoord, yCoord);
    }
    
    /**
     * Sets width
     * @param width
     */
    public void setWidth(int width){
        this.width = width;
    }
    
    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        switch (type) {
            case START:
                if (isPressed()) {
                    g.drawImage(Assets.startButtonClicked, getX(), getY(), getWidth(), getHeight(), null);
                } else {
                    g.drawImage(Assets.startButton, getX(), getY(), getWidth(), getHeight(), null);
                }
                break;
        }
        
    }
    
}

enum MenuButtonType {
    START, LEADERBOARDS, INSTRUCTIONS, SETTINGS
}