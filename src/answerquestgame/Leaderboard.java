/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package answerquestgame;

import answerquestgame.Game.ScreenType;
import answerquestgame.Helpers.Assets;
import answerquestgame.Helpers.Sizes;
import answerquestgame.Models.Score;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 * Constructor
 * @author juanpabloramos
 */
public class Leaderboard {
    private final Game game;
    private NavigationButton backButton;
    private int topic;
    private LinkedList<Score> highscores;
    
    /**
     * Creates a Leaderboard object for a game
     * @param game 
     */
    public Leaderboard(Game game) {
        this.game = game;
    }
    
    /**
     * Initializes the leaderboard for a given topic
     * 1 - math
     * 2 - spelling
     * 3 - science
     * @param topic 
     */
    public void init(int topic) {
        highscores = new LinkedList<>();
        backButton = new NavigationButton(Sizes.backButton, NavigationButton.NavButtonType.BACK, game);
        this.topic = topic;
        // get leaderboard for topic from database
        fetchHighscores();
    }
    
    /**
     * Gets highscores from database
     */
    private void fetchHighscores() {
        highscores = game.getDatabase().getHighscores(this.topic);
    }
    
    /**
     * Makes changes to objects each frame
     */
    public void tick() {
        if (backButton.isPressed()) {
            game.setScreen(ScreenType.CHOOSE);
            game.getChooseScreen().setSleep();
            backButton.tick();
        }
    }
    
    /**
     * Renders the highscores
     * @param g 
     */
    private void renderScores(Graphics g) {
        g.setFont(new Font("Courier", Font.BOLD, 30));
        g.setColor(Color.WHITE);
        g.drawString(highscores.get(0).getName(), Sizes.highscore1.namex, Sizes.highscore1.namey);
        g.drawString(highscores.get(1).getName(), Sizes.highscore2.namex, Sizes.highscore2.namey);
        g.drawString(highscores.get(2).getName(), Sizes.highscore3.namex, Sizes.highscore3.namey);
        g.drawString(highscores.get(3).getName(), Sizes.highscore4.namex, Sizes.highscore4.namey);
        g.drawString(highscores.get(4).getName(), Sizes.highscore5.namex, Sizes.highscore5.namey);
        g.drawString(highscores.get(5).getName(), Sizes.highscore6.namex, Sizes.highscore6.namey);
        g.drawString(Integer.toString(highscores.get(0).getScore()), Sizes.highscore1.scorex, Sizes.highscore1.scorey);
        g.drawString(Integer.toString(highscores.get(1).getScore()), Sizes.highscore2.scorex, Sizes.highscore2.scorey);
        g.drawString(Integer.toString(highscores.get(2).getScore()), Sizes.highscore3.scorex, Sizes.highscore3.scorey);
        g.drawString(Integer.toString(highscores.get(3).getScore()), Sizes.highscore4.scorex, Sizes.highscore4.scorey);
        g.drawString(Integer.toString(highscores.get(4).getScore()), Sizes.highscore5.scorex, Sizes.highscore5.scorey);
        g.drawString(Integer.toString(highscores.get(5).getScore()), Sizes.highscore6.scorex, Sizes.highscore6.scorey);
    }
   
    /**
     * Draws objects each frame
     * @param g 
     */
    public void render(Graphics g) {
        g.drawImage(Assets.leaderboard, 0, 0, game.getWidth(), game.getHeight(), null);
        renderScores(g);
        backButton.render(g);
    }
}
