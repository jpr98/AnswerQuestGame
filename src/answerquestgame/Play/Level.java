/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package answerquestgame.Play;

import answerquestgame.Helpers.Assets;
import answerquestgame.Models.Question;
import answerquestgame.*;
import answerquestgame.Game.ScreenType;
import answerquestgame.Helpers.Sizes;
import answerquestgame.NavigationButton.NavButtonType;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author juanpabloramos
 */
public class Level {
    private Game game;
    private Player player1;
    private Player player2;
    private LevelNumber number;
    private LinkedList<Question> questions;
    private int player1WinCount;
    private int player2WinCount;
    private int scoresPlayer1[];
    private int scoresPlayer2[];
    private int sleep;
    private int topic;
    private boolean noUsername;
    
    private boolean playerWon;
    private NavigationButton nextButton;
    private NavigationButton homeButton;
    
    private boolean paused;
    private PauseScreen pauseScreen;
    
    public enum LevelNumber {
        ONE, TWO, THREE
    }

    /**
     * Creates a level from a level number and for a game
     * @param level
     * @param game 
     */
    public Level(LevelNumber level, Game game) { // add LevelNumber number as first parameter
        this.number = level;
        this.game = game;
        player1WinCount = 0;
        player2WinCount = 0;
        scoresPlayer1 = new int[3];
        scoresPlayer2 = new int[3];
    }

    /**
     * Initializes the level for a topic
     * 1 - math
     * 2 - spelling
     * 3 - science
     * 
     */
    public void init(int topic) {
        // preparing questions
        questions = new LinkedList<>();
        this.topic = topic;
        fetchQuestions();
        
        player1 = new Player(Sizes.player1, questions, true, this);
        player2 = new Player(Sizes.player2, questions, false, this);

        sleep = 0;
        playerWon = false;
        nextButton = new NavigationButton(Sizes.nextButton, NavButtonType.NEXTLEVEL, game);
        homeButton = new NavigationButton(Sizes.nextButton, NavButtonType.HOME, game);
        
        paused = false;
        game.getKeyManager().releaseP();
        pauseScreen = new PauseScreen(this);
        pauseScreen.init();
        noUsername = true;
    }

    /**
     * Gets questions from database
     */
    private void fetchQuestions() {
        int difficulty = 1;
        switch (number) {
            case ONE:
                difficulty = 1;
                break;
            case TWO:
                difficulty = 2;
                break;
            case THREE:
                difficulty = 3;
                break;
        }
        
        switch (topic) {
            case 1:
                questions = game.getDatabase().getMath(difficulty);
                break;
            case 2:
                questions = game.getDatabase().getSpelling(difficulty);
                break;
            case 3:
                questions = game.getDatabase().getScience(difficulty);
                break;
        }
    }
    
    /**
     * Return the game object
     * @return game
     */
    public Game getGame() {
        return game;
    }
    
    /**
     * Returns the levelNumber
     * @return level
     */
    public LevelNumber getLevelNumber() {
        return number;
    }
    
    /**
     * Sets the level number
     * @param number 
     */
    public void setLevelNumber(LevelNumber number) {
        this.number = number;
    }
    
    /**
     * Checks if the game is paused
     * @return paused
     */
    public boolean isPaused() {
        return paused;
    }
    
    /**
     * Resets the sleep variable to 0
     */
    public void setSleep() {
        sleep = 0;
    }
    
    /**
     * Returns true if player 1 won the level
     * @param playerWon 
     */
    public void setPlayer1Won(boolean playerWon) {
        this.playerWon = playerWon;
    }
    
    /**
     * Increases the count of a player's wins
     * True for player 1
     * False for player 2
     * @param isPlayer1 
     */
    public void increasePlayerWinCount(boolean isPlayer1) {
        if (isPlayer1) {
            player1WinCount++;
        } else {
            player2WinCount++;
        }
    }

    /**
     * Gets the next level and saves the players' scores
     */
    public void changeLevel() {
        switch(number) {
            case ONE:
                scoresPlayer1[0] = player1.getScore();
                scoresPlayer2[0] = player2.getScore();
                number = LevelNumber.TWO;
                this.init(this.topic);
                break;
            case TWO:
                scoresPlayer1[1] = player1.getScore();
                scoresPlayer2[1] = player2.getScore();
                number = LevelNumber.THREE;
                this.init(this.topic);
                break;
            case THREE:
                scoresPlayer1[2] = player1.getScore();
                scoresPlayer2[2] = player2.getScore();
                game.setScreen(ScreenType.MENU);
                break;
        }
    }
    
    /**
     * Checks if KeyManager.p matches the paused variable
     */
    private void checkP() {
        if (game.getKeyManager().p != paused) {
            paused = game.getKeyManager().p;
        }
    }
    
    /**
     * Makes changes to objects each frame
     */
    public void tick() {
        checkP();
        if (paused) {
            pauseScreen.tick();
            if (pauseScreen.getHomeButton().isPressed() && sleep > 30) {
                game.setSleep();
                game.setScreen(ScreenType.MENU);
            }
            sleep++;
            if (pauseScreen.getTutorialButton().isPressed()) {
                pauseScreen.setShowTutorial(true);
            }
            if (pauseScreen.getBackButton().isPressed()) {
                pauseScreen.setShowTutorial(false);
            }
            if (pauseScreen.getRestartButton().isPressed()) {
                game.getKeyManager().releaseP();
                this.init(this.topic);
            }
        } else {
            player1.tick();
            player2.tick();
            if (playerWon) {
                player1.stop();
                player1.canMove(false);
                player2.stop();
                player2.canMove(false);
                homeButton.tick();
                nextButton.tick();
                if (number == LevelNumber.THREE && homeButton.isPressed()) {
                    changeLevel();
                    if (number == LevelNumber.THREE && noUsername) {
                        askForUsername();
                    }
                }
                if (nextButton.isPressed()) {
                    changeLevel();
                }
            }
        }
        
    }
    
    /**
     * Displays a Jframe to asks the user for its username
     */
    private void askForUsername() {
        JDialog d = new JDialog(game.getDisplay().getJframe(), "Register High Score", true);
        JPanel pDisplay = new JPanel();
        if (player1.hasWon()) {
            pDisplay.add(new JLabel("Please write your initials:"));
        } else if (player2.hasWon()) {
            pDisplay.add(new JLabel("Please write your initials:"));
        }
        JTextField myField = new JTextField(10);
        pDisplay.add(myField);
        
        JButton button = new JButton("Submit");
        button.addActionListener(new ActionListener() {
            /**
            * Close the display box.
            * @param con 
            */
             @Override
             public void actionPerformed(ActionEvent con) {
                int totalScore = 0;
                if (player1.hasWon()) {
                    totalScore += scoresPlayer1[0] + scoresPlayer1[1] + scoresPlayer1[2];
                    
                } else if (player2.hasWon()) {
                    totalScore += scoresPlayer2[0] + scoresPlayer2[1] + scoresPlayer2[2];
                }
                getGame().getDatabase().insertScore(myField.getText(), totalScore, topic);
                noUsername = false;
                d.dispose();
             }
         });
        pDisplay.add(button);
        d.add(pDisplay);
        d.setBounds(100, 300, 200, 200);
        d.setLocationRelativeTo(game.getDisplay().getJframe());
        d.setVisible(true);
    }

    /**
     * Draws objects each frame
     * @param g 
     */
    public void render(Graphics g) {
        if (paused) { 
            pauseScreen.render(g);
        } else {
            switch(number) {
                case ONE:
                    if (playerWon) {
                        if (player1.hasWon()) {
                            g.drawImage(Assets.level1Player1Win, 0, 0, game.getWidth(), game.getHeight(), null);
                        } else if (player2.hasWon()) {
                            g.drawImage(Assets.level1Player2Win, 0, 0, game.getWidth(), game.getHeight(), null);
                        }
                        nextButton.render(g);
                    } else {
                        g.drawImage(Assets.backgroundOne, 0, 0, game.getWidth(), game.getHeight(), null);
                        player1.render(g);
                        player2.render(g);
                    }
                    break;
                case TWO:
                    if (playerWon) {
                         if (player1.hasWon()) {
                            g.drawImage(Assets.level2Player1Win, 0, 0, game.getWidth(), game.getHeight(), null);
                        } else if (player2.hasWon()) {
                            g.drawImage(Assets.level2Player2Win, 0, 0, game.getWidth(), game.getHeight(), null);
                        }
                         nextButton.render(g);
                    } else {
                        g.drawImage(Assets.backgroundTwo, 0, 0, game.getWidth(), game.getHeight(), null);
                        player1.render(g);
                        player2.render(g);
                    }
                    break;
                 case THREE:
                    if (playerWon) {
                        if (player1.hasWon()) {
                            g.drawImage(Assets.level3Player1Win, 0, 0, game.getWidth(), game.getHeight(), null);
                        } else if (player2.hasWon()) {
                            g.drawImage(Assets.level3Player2Win, 0, 0, game.getWidth(), game.getHeight(), null);
                        }
                        // show overall Winner
                        if (player1WinCount > player2WinCount) {
                            // show overall winner 1
                            g.drawImage(Assets.overallWinner1, Sizes.overall.x, Sizes.overall.y, Sizes.overall.width, Sizes.overall.height, null);
                        } else {
                            // show overall winner 2
                            g.drawImage(Assets.overallWinner2, Sizes.overall.x, Sizes.overall.y, Sizes.overall.width, Sizes.overall.height, null);
                        }
                        homeButton.render(g);
                    } else {
                        g.drawImage(Assets.backgroundThree, 0, 0, game.getWidth(), game.getHeight(), null);
                        player1.render(g);
                        player2.render(g);
                    }
                    break;
            }
        }
    }
}

