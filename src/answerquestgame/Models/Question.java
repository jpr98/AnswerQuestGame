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
    
    public Question(String question, String correctAns, String wrongAns) {
        this.question = question;
        this.correctAns = correctAns;
        this.wrongAns = wrongAns;
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
   
}
