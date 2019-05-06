/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package answerquestgame.Helpers;

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
   
   public LinkedList<Question> getGeography(int difficulty) {
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
   
   public void insertPlayerData(String name, int score) {
       try {
            String myDriver = "com.mysql.cj.jdbc.Driver";
            String myUrl = "jdbc:mysql://zerosumsandbox.c91oxw9rgzr9.us-east-1.rds.amazonaws.com:3306/sandbox";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "sandbox", "answerquest");

            Statement st = conn.createStatement();
            
            st.executeUpdate("INSERT INTO Player (name, score) "
                +"VALUES ('JP',13)");
       } catch (Exception e) {
           System.out.println("Error: " + e.getLocalizedMessage());
       }
   }
   
}
