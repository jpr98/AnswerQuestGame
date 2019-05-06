/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package answerquestgame.Models;

/**
 *
 * @author juanpabloramos
 */
public class Question {
    private String question;
    private String correctAns;
    private String wrongAns;
    private int scoreToSum;
    private int level;
    
    public Question(String question, String correctAns, String wrongAns, int scoreToSum, int level) {
        this.question = question;
        this.correctAns = correctAns;
        this.wrongAns = wrongAns;
        this.scoreToSum = scoreToSum;
        this.level = level;
    }
    
    public String getQuestion() {
        return question;
    }
    
    public String getCorrectAns() {
        return correctAns;
    }
    
    public String getWrongAns() {
        return wrongAns;
    }
    
     public int getScoreToSum() {
        return scoreToSum;
    }
     
     public int getLevel() {
        return level;
    }
   
}
