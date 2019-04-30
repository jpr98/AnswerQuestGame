/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package answerquestgame.Play;

import answerquestgame.Helpers.Assets;
import answerquestgame.Models.Question;
import answerquestgame.Game;
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

    private boolean player1Won;
    
    enum LevelNumber {
        ONE, TWO, THREE, FOUR
    }

    public Level(Game game) { // add LevelNumber number as first parameter
        this.number = LevelNumber.ONE;
        this.game = game;
    }

    public void init() {
        Assets.init();
        
        // preparing questions
        questions = new LinkedList<>();
        prepareTestQuestions();
        
        player1 = new Player(96, 530, 110, 110, questions, true, this);
        player2 = new Player(380, 530, 110, 110, questions, false, this);

        player1Won = false;
    }

    public void setPlayer1Won(boolean player1Won) {
        this.player1Won = player1Won;
    }

    public Game getGame() {
        return game;
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
        String foura = "16";
        String fourb = "10";
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
        switch(number) {
            case ONE:
                if (player1Won) {
                    g.drawImage(Assets.menuBackground, 0, 0, game.getWidth(), game.getHeight(), null);
                } else {
                    g.drawImage(Assets.background, 0, 0, game.getWidth(), game.getHeight(), null);
                    player1.render(g);
                    player2.render(g);
                }
                break;
        }
        
    }
}
