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
import answerquestgame.NavigationButton.NavButtonType;
import java.awt.Graphics;
import java.util.LinkedList;

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
    
    private boolean playerWon;
    private NavigationButton nextButton;
    private NavigationButton homeButton;
    
    private boolean paused;
    private PauseScreen pauseScreen;
    
    public enum LevelNumber {
        ONE, TWO, THREE
    }

    public Level(LevelNumber level, Game game) { // add LevelNumber number as first parameter
        this.number = level;
        this.game = game;
        player1WinCount = 0;
        player2WinCount = 0;
    }

    public void init() {
        // preparing questions
        questions = new LinkedList<>();
        prepareTestQuestions();
        
        player1 = new Player(96, 530, 110, 110, questions, true, this);
        player2 = new Player(380, 530, 110, 110, questions, false, this);

        sleep = 0;
        playerWon = false;
        nextButton = new NavigationButton(game.getWidth()/2-100, game.getHeight()-180, 200, 100, NavButtonType.NEXTLEVEL, game);
        homeButton = new NavigationButton(game.getWidth()/2-100, game.getHeight()-180, 200, 100, NavButtonType.HOME, game);
        
        paused = false;
        pauseScreen = new PauseScreen(this);
        pauseScreen.init();
    }

    public Game getGame() {
        return game;
    }
    
    public LevelNumber getLevelNumber() {
        return number;
    }
    
    public boolean isPaused() {
        return paused;
    }
    
    public void setSleep() {
        sleep = 0;
    }
    
    public void setPlayer1Won(boolean playerWon) {
        this.playerWon = playerWon;
    }
    
    public void increasePlayerWinCount(boolean isPlayer1) {
        if (isPlayer1) {
            player1WinCount++;
        } else {
            player2WinCount++;
        }
    }

    public void prepareTestQuestions() {
        String one = "2x4";
        String onea = "8";
        String oneb = "6";
        questions.add(new Question(one, onea, oneb));

        String two = "5+9";
        String twoa = "14";
        String twob = "16";
        questions.add(new Question(two, twoa, twob));

        String three = "6+6";
        String threea = "12";
        String threeb = "66";
        questions.add(new Question(three, threea, threeb));

        String four = "8+2";
        String foura = "10";
        String fourb = "16";
        questions.add(new Question(four, foura, fourb));

        String five = "9x2";
        String fivea = "18";
        String fiveb = "81";
        questions.add(new Question(five, fivea, fiveb));

        String six = "10+1";
        String sixa = "11";
        String sixb = "101";
       questions.add(new Question(six, sixa, sixb));

        String seven = "9-3";
        String sevena = "6";
        String sevenb = "3";
        questions.add(new Question(seven, sevena, sevenb));

        String eight = "3+3";
        String eighta = "6";
        String eightb = "9";
        questions.add(new Question(eight, eighta, eightb));

        String nine = "2+6";
        String ninea = "8";
        String nineb = "9";
        questions.add(new Question(nine, ninea, nineb));

        String ten = "5+5";
        String tena = "10";
        String tenb = "25";
        questions.add(new Question(ten, tena, tenb));

        String eleven = "5x5";
        String elevena = "25";
        String elevenb = "10";
        questions.add(new Question(eleven, elevena, elevenb));

        String twelve = "2x2";
        String twelvea = "4";
        String twelveb = "8";
        questions.add(new Question(twelve, twelvea, twelveb));

        String thirteen = "7+8";
        String thirteena = "15";
        String thirteenb = "14";
        questions.add(new Question(thirteen, thirteena, thirteenb));

        String fourteen = "3x11";
        String fourteena = "33";
        String fourteenb = "14";
        questions.add(new Question(fourteen, fourteena, fourteenb));
    }

    public void changeLevel() {
        switch(number) {
            case ONE:
                scoresPlayer1[0] = player1.getScore();
                scoresPlayer2[0] = player2.getScore();
                number = LevelNumber.TWO;
                break;
            case TWO:
                scoresPlayer1[1] = player1.getScore();
                scoresPlayer2[1] = player2.getScore();
                number = LevelNumber.THREE;
                break;
            case THREE:
                scoresPlayer1[2] = player1.getScore();
                scoresPlayer2[2] = player2.getScore();
                break;
        }
        this.init();
    }
    
    private void checkP() {
        if (game.getKeyManager().p != paused) {
            paused = game.getKeyManager().p;
        }
    }
    
    public void tick() {
        checkP();
        if (paused) {
            if (pauseScreen.getHomeButton().isPressed() && sleep > 5) {
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
                this.init();
            }
        } else {
            player1.tick();
            player2.tick();
            if (playerWon) {
                player1.stop();
                player1.canMove(false);
                player2.stop();
                player2.canMove(false);
                if (nextButton.isPressed()) {
                    changeLevel();
                }
                if (homeButton.isPressed()) {
                    game.setScreen(ScreenType.MENU);
                }
            }
        }
        
    }

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
                        } else {
                            // show overall winner 2
                        }
                    } else {
                        g.drawImage(Assets.backgroundThree, 0, 0, game.getWidth(), game.getHeight(), null);
                        player1.render(g);
                        player2.render(g);
                    }
                    homeButton.render(g);
                    break;
            }
        }
    }
}

