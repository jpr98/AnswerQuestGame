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
import java.awt.Graphics;
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
        for (int i=0; i<highscores.size(); i++) {
            System.out.println(highscores.get(i).getName());
        }
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
        g.drawString(highscores.get(0).getName(), 200, 30);
        //g.drawString(String.valueOf(highscores.get(0).getScore()), 30, 30);
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
