/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package answerquestgame.Helpers;

import answerquestgame.Models.Score;
import java.sql.*;
import answerquestgame.Models.Question;
import java.util.LinkedList;

/**
 *
 * @author User
 */
public class Database {
    private Connection con;
    private Statement st;
    private ResultSet rs;
    
   public Database()
   {
        // TODO code application logic here
         try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://zerosumsandbox.c91oxw9rgzr9.us-east-1.rds.amazonaws.com:3306/?user=sandbox", "sandbox", "answerquest" );
            st = con.createStatement();
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
   }
 
   
   public LinkedList<Question> getMath(int difficulty) {
       LinkedList<Question> questions;
       questions = new LinkedList<>();
       try {
        String myDriver = "com.mysql.cj.jdbc.Driver";
        String myUrl = "jdbc:mysql://zerosumsandbox.c91oxw9rgzr9.us-east-1.rds.amazonaws.com:3306/sandbox";
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, "sandbox", "answerquest");

        Statement st = conn.createStatement();
        
        String query = "SELECT * FROM Question WHERE level =" + difficulty;
        ResultSet rs = st.executeQuery(query);
        
        while (rs.next())
        {
          String question = rs.getString("question");
          String correctAns = rs.getString("correctAns");
          String wrongAns = rs.getString("incorrectAns");
          int score = rs.getInt("scoreToSum");
          
          questions.add(new Question(question, correctAns, wrongAns, score));
        }
       }
       catch(Exception e)
        {
           System.out.println("Error " + e);
        }
        return questions;
   }
   
   public LinkedList<Question> getSpelling(int difficulty) {
       LinkedList<Question> questions;
       questions = new LinkedList<>();
       try {
        String myDriver = "com.mysql.cj.jdbc.Driver";
        String myUrl = "jdbc:mysql://zerosumsandbox.c91oxw9rgzr9.us-east-1.rds.amazonaws.com:3306/sandbox";
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, "sandbox", "answerquest");

        Statement st = conn.createStatement();

        String query = "SELECT * FROM Question WHERE level =" + (3 + difficulty);
        ResultSet rs = st.executeQuery(query);
        
        while (rs.next())
        {
          String question = rs.getString("question");
          String correctAns = rs.getString("correctAns");
          String wrongAns = rs.getString("incorrectAns");
          int score = rs.getInt("scoreToSum");
          
          questions.add(new Question(question, correctAns, wrongAns, score));
        }
       }
       catch(Exception e)
       {
           System.out.println("Error " + e);
       }
       return questions;
   }
   
   public LinkedList<Question> getScience(int difficulty) {
       LinkedList<Question> questions;
       questions = new LinkedList<>();
       try {
        String myDriver = "com.mysql.cj.jdbc.Driver";
        String myUrl = "jdbc:mysql://zerosumsandbox.c91oxw9rgzr9.us-east-1.rds.amazonaws.com:3306/sandbox";
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, "sandbox", "answerquest");

        Statement st = conn.createStatement();

        String query = "SELECT * FROM Question WHERE level =" + (6 + difficulty);
        ResultSet rs = st.executeQuery(query);
      
        while (rs.next())
        {
          String question = rs.getString("question");
          String correctAns = rs.getString("correctAns");
          String wrongAns = rs.getString("incorrectAns");
          int score = rs.getInt("scoreToSum");
          
          questions.add(new Question(question, correctAns, wrongAns, score));
        }
        
       }
       catch(Exception e)
       {
           System.out.println("Error " + e);
       }
       
       return questions;
   }
   
   
   
   // GEt Math highscores
   // Math is 1, Spelling is 2, Geography is 3.
   public LinkedList<Score> getHighscores(int type) {
       LinkedList<Score> highscores;
       highscores = new LinkedList<>();
       try {
        String myDriver = "com.mysql.cj.jdbc.Driver";
        String myUrl = "jdbc:mysql://zerosumsandbox.c91oxw9rgzr9.us-east-1.rds.amazonaws.com:3306/sandbox";
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, "sandbox", "answerquest");

        Statement st = conn.createStatement();

        String query = "";
        switch(type)
        {
            case 1:
                query = "SELECT name, scoreOne FROM sandbox.Player ORDER BY scoreOne DESC LIMIT 6;";
                ResultSet rs = st.executeQuery(query);
                while (rs.next())
                {
                  String name = rs.getString("name");
                  int score = rs.getInt("scoreOne");
                  highscores.add(new Score(name, score));
                }
                break;
            case 2:
                 query = "SELECT name, scoreTwo FROM sandbox.Player ORDER BY scoreTwo DESC LIMIT 6;";
                 ResultSet rs2 = st.executeQuery(query);
                 while (rs2.next())
                 {
                   String name = rs2.getString("name");
                   int score = rs2.getInt("scoreTwo");
                   highscores.add(new Score(name, score));
                 }
                break;
            case 3:
                 query = "SELECT name, scoreThree FROM sandbox.Player ORDER BY scoreThree DESC LIMIT 6;";
                 ResultSet rs3 = st.executeQuery(query);
                 while (rs3.next())
                 {
                   String name = rs3.getString("name");
                   int score = rs3.getInt("scoreThree");
                   highscores.add(new Score(name, score));
                 }
                break;
        }
        
       }
       catch(Exception e)
       {
           System.out.println("Error " + e);
       }
       
       return highscores;
   }
   
   public void insertScore(String name, int score, int topic) {
       try {
            String myDriver = "com.mysql.cj.jdbc.Driver";
            String myUrl = "jdbc:mysql://zerosumsandbox.c91oxw9rgzr9.us-east-1.rds.amazonaws.com:3306/sandbox";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "sandbox", "answerquest");

            Statement st = conn.createStatement();
            if (topic == 1) {
                 st.executeUpdate("INSERT INTO Player (name, scoreOne) "
                +"VALUES ('" + name + "',"+score+")");
            } else if (topic == 2) {
                st.executeUpdate("INSERT INTO Player (name, scoreTwo) "
                +"VALUES ('" + name + "',"+score+")");
            } else if (topic == 3) {
                st.executeUpdate("INSERT INTO Player (name, scoreThree) "
                +"VALUES ('" + name + "',"+score+")");
            }
           
       } catch (Exception e) {
           System.out.println("Error: " + e.getLocalizedMessage());
       }
   }
   
}
