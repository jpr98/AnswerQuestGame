/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package answerquestgame.Play;

import answerquestgame.Game;
import answerquestgame.Helpers.Assets;
import answerquestgame.Helpers.Item;
import java.awt.Graphics;

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
    
    public LevelButton(int x, int y, int height, int width, boolean isLeft, Player player) {
        super(x, y);
        this.height = height;
        this.width = width;
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

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public boolean isCorrect() {
        return this.correct;
    }

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

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }
    
    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        if (canMove) {
            renderButtonLogic(g);
            g.drawString(answer, getX()+getWidth()/2, getY()+getHeight()/2);
        } else {
            g.drawString(answer, getX()+getWidth()/2, getY()+getHeight()/2);
            g.drawImage(Assets.button, getX(), getY(), getWidth(), getHeight(), null);
        }
    }
    
}
