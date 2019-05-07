/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package answerquestgame;

/**
 *
 * @author juanpabloramos
 */
public class AnswerQuestGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Game game = new Game("AnswerQuest", 500, 700);
        game.start();
    }
    
}
