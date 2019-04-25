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
public class Level {
    private Game game;
    private Player player1;
    private Player player2;
    
    private boolean player1Won;

    public Level(Game game) {
        this.game = game;
    }

    public void init() {
        Assets.init();

        player1 = new Player(96, 530, 110, 110, true, this);
        player2 = new Player(380, 530, 110, 110, false, this);

        player1Won = false;
    }

    public void setPlayer1Won(boolean player1Won) {
        this.player1Won = player1Won;
    }

    public Game getGame() {
        return game;
    }

    public void tick() {
        player1.tick();
        player2.tick();
        if (player1Won) {
            player1.stop();
            player1.canMove(false);
            player2.stop();
            player2.canMove(false);
        }
    }

    public void render(Graphics g) {
        g.drawImage(Assets.background, 0, 0, game.getWidth(), game.getHeight(), null);
        player1.render(g);
        player2.render(g);
    }
}
