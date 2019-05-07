/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package answerquestgame;

import answerquestgame.Helpers.Assets;
import answerquestgame.Helpers.Item;
import answerquestgame.Helpers.Sizes;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author juanpabloramos
 */
public class MenuButton extends Item {
    private int height;
    private int width;
    private int oWidth;
    private int oHeight;
    private Menu menu;
    private MenuButtonType type;
    
    public enum MenuButtonType {
        START, HIGHSCORE, INSTRUCTIONS, SETTINGS
    }
    
    /**
     * Creates a MenuButton with the given attributes
     * @param size
     * @param type
     * @param menu 
     */
    public MenuButton(Sizes.SizeAndPos size, MenuButtonType type, Menu menu) {
        super(size.x, size.y);
        this.height = size.height;
        this.width = size.width;
        oWidth = width;
        oHeight = height;
        this.type = type;
        this.menu = menu;
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

    /**
     * Checks if the button is pressed
     * @return boolean
     */
    public boolean isPressed() {
        int xCoord = menu.getGame().getMouseManager().getX();
        int yCoord = menu.getGame().getMouseManager().getY();
        boolean mouseClicked = menu.getGame().getMouseManager().isLeft() || menu.getGame().getMouseManager().isRight();
        //menu.getGame().getMouseManager().setLeft(false);
        //menu.getGame().getMouseManager().setRight(false);
        return mouseClicked && getPerimeter().contains(xCoord, yCoord);
    }
    
    /**
     * Sets width
     * @param width
     */
    public void setWidth(int width){
        this.width = width;
    }
    
    public void hover() {
        if (width < oWidth + 20) {
            x--;
            width += 2;
        }
        if (height < oHeight + 20) {
            y--;
            height += 2;
        }
    }
    
    public void hoverBack() {
        if (width > oWidth) {
            x++;
            width -= 2;
        }
        if (height > oHeight) {
            y++;
            height -= 2;
        }
    }
    /**
     * Makes changes to objects each frame
     */
    @Override
    public void tick() {
        int xCoord = menu.getGame().getMouseManager().getX();
        int yCoord = menu.getGame().getMouseManager().getY();
        if (getPerimeter().contains(xCoord, yCoord)) {
            hover();
        } else {
            hoverBack();
        }
    }
    /**
     * Draws objects each frame
     * @param g 
     */
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
            case HIGHSCORE:
                if (isPressed()) {
                    g.drawImage(Assets.highscoreButtonClicked, getX(), getY(), getWidth(), getHeight(), null);
                } else {
                    g.drawImage(Assets.highscoreButton, getX(), getY(), getWidth(), getHeight(), null);
                }
                break;
            case INSTRUCTIONS:
                if (isPressed()) {
                    g.drawImage(Assets.tutorialButtonClicked, getX(), getY(), getWidth(), getHeight(), null);
                } else {
                    g.drawImage(Assets.tutorialButton, getX(), getY(), getWidth(), getHeight(), null);
                }
                break;
        }
        
    }
    
}