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
    private int dropCounter;
    private boolean canMove;
    private boolean enabled;
    private Button leftButton;
    private Button rightButton;
    private Timer timer;
    private String question = "2x4";
    private String questiontwo = "10+1";
    private String ansone = "8";
    private String anstwo = "6";
    private String ansthree = "101";
    private String ansfour = "11";
    
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
        dropCounter = 0;
        canMove = true;
        enabled = true;
        setButtons();
        setTimer();
        setAnswers();
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

    private void setAnswers() {
        int correctButton = (Math.random() <= 0.5) ? 1 : 2;
        if (correctButton == 1) {
            leftButton.setCorrect(true);
            leftButton.setAnswer(ansone);
            rightButton.setCorrect(false);
            rightButton.setAnswer(anstwo);
        } else {
            rightButton.setCorrect(true);
            rightButton.setAnswer(ansone);
            leftButton.setCorrect(false);
            leftButton.setAnswer(anstwo);
        }
    }

    private boolean checkWrong() {
        boolean leftWrong;
        boolean rigthWrong;

        if (isPlayer1) {
            leftWrong = !leftButton.isCorrect() && game.getKeyManager().left;
            rigthWrong = !rightButton.isCorrect() && game.getKeyManager().right;
        } else {
            leftWrong = !leftButton.isCorrect() && game.getKeyManager().a;
            rigthWrong = !rightButton.isCorrect() && game.getKeyManager().d;
        }

        if (rigthWrong || leftWrong) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkCorrect() {
        boolean leftCorrect;
        boolean rightCorrect;
        
        if (isPlayer1) {
            leftCorrect = game.getKeyManager().left && leftButton.isCorrect();
            rightCorrect = game.getKeyManager().right && rightButton.isCorrect();
        } else {
            leftCorrect = game.getKeyManager().a && leftButton.isCorrect();
            rightCorrect = game.getKeyManager().d && rightButton.isCorrect();
        }

        if (rightCorrect || leftCorrect) {
            // get next question and answers
            return true;
        } else {
            return false;
        }
    }

    public void stop() {
        enabled = false;
        timer.setCanMove(false);
        leftButton.setCanMove(false);
        rightButton.setCanMove(false);
    }

    public void start() {
        enabled = true;
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
        enabled = false;
        timer.setCanMove(false);
        // disble buttons 5 ticks after pressed so they show the color change
        if (moveCounter == 75 || dropCounter == 75) {
            leftButton.setCanMove(false);
            rightButton.setCanMove(false);
        }

        if (moveCounter > 0) {
            setY(getY()-1);
            moveCounter--;
        }
        if (dropCounter > 0) {
            setY(getY()+1);
            dropCounter--;
        }
        if (moveCounter == 0 && dropCounter == 0) {
            start();
        }
    }

    /**
     * Notifying observers that player won
     */
    private void reachTop() {
        stop();
        canMove(false);
        game.setPlayer1Won(true);
    }

    /**
     * Makes changes to objects each frame
     */
    @Override
    public void tick() {
        // Checking if player reaches top
        if (getY() + getHeight() < 179) {
            // manage winning level
            moveCounter = 0;
            dropCounter = 0;
            reachTop();
        } 

        // Moving player with corresponding keys
        if (canMove) {
            if (enabled) {
                if (checkCorrect()) {
                    moveCounter = 80;
                    timer.setWidth(295);
                } else if (checkWrong()) {
                    dropCounter = 80;
                    timer.setWidth(295);
                } 
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
