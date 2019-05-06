/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package answerquestgame;

import answerquestgame.Game.ScreenType;
import answerquestgame.Helpers.Assets;
import java.awt.Graphics;

/**
 *
 * @author juanpabloramos
 */
public class Leaderboard {
    private Game game;
    private NavigationButton backButton;
    private int topic;
    
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
     * 3 - geography
     * @param topic 
     */
    public void init(int topic) {
        backButton = new NavigationButton(37, 720, 120, 60, NavigationButton.NavButtonType.BACK, game);
        this.topic = topic;
        // get leaderboard for topic from database
        
    }
    
    /**
     * Makes changes to objects each frame
     */
    public void tick() {
        if (backButton.isPressed()) {
            game.setScreen(ScreenType.CHOOSE);
            game.getChooseScreen().setSleep();
        }
    }
    
    /**
     * Draws objects each frame
     * @param g 
     */
    public void render(Graphics g) {
        g.drawImage(Assets.leaderboard, 0, 0, game.getWidth(), game.getHeight(), null);
        backButton.render(g);
    }
}
