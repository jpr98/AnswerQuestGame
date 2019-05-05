/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package answerquestgame.Play;

import answerquestgame.Game;
import answerquestgame.Helpers.*;
import answerquestgame.Models.Question;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author juanpabloramos
 */
public class Player extends Item {
    private int height;
    private int width;
    private Level level;
    private Animation normalAnimation;
    private Animation fallingAnimation;
    private boolean isPlayer1;
    private int moveCounter;
    private int dropCounter;
    private boolean won;
    private boolean canMove;
    private boolean enabled;
    private boolean falling;
    private LevelButton leftButton;
    private LevelButton rightButton;
    private Timer timer;
    private boolean timesup;
    private LinkedList<Question> questions;
    private int currentQuestionIndex;
    private Question currentQuestion;
    
    /**
     * Constructor
     * @param width
     * @param height
     * @param game
     */
    public Player(int x, int y, int height, int width, LinkedList<Question> questions, boolean isPlayer1, Level level) {
        super(x, y);
        this.height = height;
        this.width = width;
        this.isPlayer1 = isPlayer1;
        this.level = level;
        this.questions = questions;
        
        setAnimations();
        
        currentQuestionIndex = 0;
        moveCounter = 0;
        dropCounter = 0;
        won = false;
        canMove = true;
        enabled = true;
        falling = false;
        timesup = false;
        setButtons();
        setTimer();
        setCurrentQuestion();
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

    /**
     * Gets the Level
     * @return level
     */
    public Level getLevel() {
        return level;
    }

    /**
     * Checks if is player 1 or 2
     * @return ture if player one
     */
    public boolean isPlayer1() {
        return isPlayer1;
    }

    /**
     * Checks if player can move
     */
    public void canMove(boolean canMove) {
        this.canMove = canMove;
    }

    /**
     * Checks if time's up
     */
    public boolean isTimesup() {
        return timesup;
    }

    public void setTimesup(boolean bool) {
        this.timesup = bool;
    }
    
    public boolean hasWon() {
        return won;
    }

    /**
     * Creates the players buttons
     */
    private void setButtons() {
        if (isPlayer1) {
            leftButton = new LevelButton(17, 677, 90, 125, true, this);
            rightButton = new LevelButton(154, 677, 90, 125, false, this);
        } else {
            leftButton = new LevelButton(318, 677, 90, 125, true, this);
            rightButton = new LevelButton(455, 677, 90, 125, false, this);
        }
    }

    /**
     * Creates the timer for player
     */
    private void setTimer() {
        if (isPlayer1) {
            timer = new Timer(0, 53, 14, 295, 10, this);
        } else {
            timer = new Timer(304, 53, 14, 296, 10, this);
        }
    }
    
    /**
     * Sets the animation based on the level number
     */
    private void setAnimations() {
        switch(level.getLevelNumber()) {
            case ONE:
                normalAnimation = new Animation(Assets.balloonMoving, 160);
                fallingAnimation = new Animation(Assets.balloonFalling, 300);
                break;
            case TWO:
                normalAnimation = new Animation(Assets.rocketMoving, 160);
                fallingAnimation = new Animation(Assets.rocketFalling, 160);
                break;
            case THREE:
                break;
            case FOUR:
                break;
        }
    }

    private void setCurrentQuestion() {
        if (currentQuestionIndex >= questions.size()) {
            currentQuestionIndex = 0;
        }
        currentQuestion = questions.get(currentQuestionIndex);
        setAnswers();
        currentQuestionIndex++;
    }
    /**
     * Sets the answers to each button randomly
     */
    private void setAnswers() {
        int correctButton = (Math.random() <= 0.5) ? 1 : 2;
        if (correctButton == 1) {
            leftButton.setCorrect(true);
            leftButton.setAnswer(currentQuestion.getCorrectAns());
            rightButton.setCorrect(false);
            rightButton.setAnswer(currentQuestion.getWrongAns());
        } else {
            rightButton.setCorrect(true);
            rightButton.setAnswer(currentQuestion.getCorrectAns());
            leftButton.setCorrect(false);
            leftButton.setAnswer(currentQuestion.getWrongAns());
        }
    }

    /**
     * Check if wrong answer was tapped
     * @return true if wrong
     */
    private boolean checkWrong() {
        boolean leftWrong;
        boolean rigthWrong;

        if (isPlayer1) {
            leftWrong = !leftButton.isCorrect() && level.getGame().getKeyManager().a;
            rigthWrong = !rightButton.isCorrect() && level.getGame().getKeyManager().d;
        } else {
            leftWrong = !leftButton.isCorrect() && level.getGame().getKeyManager().left;
            rigthWrong = !rightButton.isCorrect() && level.getGame().getKeyManager().right;
        }

        if (rigthWrong || leftWrong) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check if correct answer was tapped
     * @return true if correct
     */
    private boolean checkCorrect() {
        boolean leftCorrect;
        boolean rightCorrect;
        
        if (isPlayer1) {
            leftCorrect = level.getGame().getKeyManager().a && leftButton.isCorrect();
            rightCorrect = level.getGame().getKeyManager().d && rightButton.isCorrect();
        } else {
            leftCorrect = level.getGame().getKeyManager().left && leftButton.isCorrect();
            rightCorrect = level.getGame().getKeyManager().right && rightButton.isCorrect();
        }

        if (rightCorrect || leftCorrect) {
            // get next question and answers
            return true;
        } else {
            return false;
        }
    }

    /**
     * Disables player interaction
     */
    public void stop() {
        enabled = false;
        timer.setCanMove(false);
        leftButton.setCanMove(false);
        rightButton.setCanMove(false);
    }

    /**
     * Re-enables player interaction
     */
    public void start() {
        enabled = true;
        timer.setCanMove(true);
        leftButton.setCanMove(true);
        rightButton.setCanMove(true);
    }

    public void drop() {
        if (getY() < 530) {
            setY(getY()+2);
        } else {
            canMove = true;
            falling = false;
            start();
            setTimesup(false);
        }
    }

    /**
     * Moves the player over a period of time
     */
    private void moving() {
        enabled = false;
        timer.setCanMove(false);
        // disble buttons 5 frames after pressed so they show the color change
        if (moveCounter == 75 || dropCounter == 35) {
            leftButton.setCanMove(false);
            rightButton.setCanMove(false);
        }

        if (moveCounter > 0) {
            setY(getY()-1);
            moveCounter--;
        }
        if (dropCounter > 0) {
            setY(getY()+2);
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
        won = true;
        stop();
        canMove(false);
        level.setPlayer1Won(true);
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

        // Checking if player reaches bottom
        if (getY() > 530) {
            setY(530);
            dropCounter = 0;
        }

        if (isTimesup()) {
            canMove = false;
            falling = true;
            stop();
            drop();
            setCurrentQuestion();
        }

        // Moving player
        if (canMove) {
            if (enabled) {
                if (checkCorrect()) {
                    moveCounter = 80;
                    timer.setWidth(295);
                    setCurrentQuestion();
                } else if (checkWrong()) {
                    dropCounter = 40;
                    timer.setWidth(295);
                    setCurrentQuestion();
                }
            }
            moving();
        }
        timer.tick();

        if (falling) {
            fallingAnimation.tick();
        } else {
            normalAnimation.tick();
        }
    }
    
    private void renderQuestions(Graphics g) {
        if (isPlayer1) {
            g.setFont(new Font("Courier", Font.BOLD, 30));
            g.setColor(Color.WHITE);
            g.drawString(currentQuestion.getQuestion(), 30, 30);
        } else {
            g.drawString(currentQuestion.getQuestion(), 400, 30);
        }
    }

    /**
     * Draw images for each frame
     */
    @Override
    public void render(Graphics g) {
        if (falling) {
            g.drawImage(fallingAnimation.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        } else {
            g.drawImage(normalAnimation.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        }
        
        renderQuestions(g);
        leftButton.render(g);
        rightButton.render(g);
        timer.render(g);
    }
    
}
