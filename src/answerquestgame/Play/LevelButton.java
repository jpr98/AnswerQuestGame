/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package answerquestgame.Play;

import answerquestgame.Game;
import answerquestgame.Helpers.Assets;
import answerquestgame.Helpers.Item;
import answerquestgame.Helpers.Sizes;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author juanpabloramos
 */
public class LevelButton extends Item {
    private int height;
    private int width;
    private Player player;
    private boolean isLeft;
    private boolean canMove;
    private boolean correct;
    private String answer;
    
    /**
     * Creates a LevelButton object with the given attributes
     * @param size
     * @param isLeft
     * @param player 
     */
    public LevelButton(Sizes.SizeAndPos size, boolean isLeft, Player player) {
        super(size.x, size.y);
        this.height = size.height;
        this.width = size.width;
        this.player = player;
        this.isLeft = isLeft;
        canMove = true;
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
     * Sets the answer to the button
     * @param answer 
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * Sets whether the button contains the correct answer
     * @param correct 
     */
    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    /**
     * Returns true if the answer in the  button is correct
     * @return correct
     */
    public boolean isCorrect() {
        return this.correct;
    }

    /**
     * Contains logic to manage clicked or unclicked button rendering
     * @param g 
     */
    private void renderButtonLogic(Graphics g) {
        if (player.isPlayer1()) {
            if (isLeft) {
                if (player.getLevel().getGame().getKeyManager().a) {
                    g.drawImage(Assets.buttonClicked, getX(), getY(), getWidth(), getHeight(), null);
                } else {
                    g.drawImage(Assets.button, getX(), getY(), getWidth(), getHeight(), null);
                }
            } else {
                if (player.getLevel().getGame().getKeyManager().d) {
                    g.drawImage(Assets.buttonClicked, getX(), getY(), getWidth(), getHeight(), null);
                } else {
                    g.drawImage(Assets.button, getX(), getY(), getWidth(), getHeight(), null);
                }
            }
        } else {
            if (isLeft) {
                if (player.getLevel().getGame().getKeyManager().left) {
                    g.drawImage(Assets.buttonClicked, getX(), getY(), getWidth(), getHeight(), null);
                } else {
                    g.drawImage(Assets.button, getX(), getY(), getWidth(), getHeight(), null);
                }
            } else {
                if (player.getLevel().getGame().getKeyManager().right) {
                    g.drawImage(Assets.buttonClicked, getX(), getY(), getWidth(), getHeight(), null);
                } else {
                    g.drawImage(Assets.button, getX(), getY(), getWidth(), getHeight(), null);
                }
            }
        }
    }

    /**
     * Sets width
     * @return width
     */
    public void setWidth(int width){
        this.width = width;
    }

    /**
     * Sets if the button is enabled
     * @param canMove 
     */
    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }
    
    /**
     * Makes changes to objects each frame
     */
    @Override
    public void tick() {
    }

    /**
     * Function to center a string in a rectangle
     * @param g
     * @param text
     * @param rect
     * @param font 
     */
    private void centerString(Graphics g, String text, Rectangle rect, Font font) {
         // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        // Set the font
        g.setFont(font);
        // Draw the String
        g.drawString(text, x, y);
    }
    
    /**
     * Contains the logic to render the questions
     * @param g 
     */
    private void renderQuestions(Graphics g) {
        Font font = new Font("Courier", Font.BOLD, 30);
        g.setColor(Color.WHITE);
        if (player.isPlayer1()) {
            if (isLeft) {
                centerString(g, answer, Sizes.answerArea1P1, font);
            } else {
                centerString(g, answer, Sizes.answerArea2P1, font);
            }
        } else {
            if (isLeft) {
                centerString(g, answer, Sizes.answerArea1P2, font);
            } else {
                centerString(g, answer, Sizes.answerArea2P2, font);
            }
        }
    }
    
    /**
     * Draws objects each frame
     * @param g 
     */
    @Override
    public void render(Graphics g) {
        if (canMove) {
            renderButtonLogic(g);
        } else {
            g.drawImage(Assets.button, getX(), getY(), getWidth(), getHeight(), null);
        }
        renderQuestions(g);
    }
    
}
