/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package answerquestgame.Helpers;

import java.sql.*;


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
 
   
   public void getMath(int difficulty) {
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
          String name = rs.getString("question");
          // print the results
          System.out.println(name);
        }
       }
       catch(Exception e)
       {
           System.out.println("Error " + e);
       }
   }
   
   public void getSpelling(int difficulty) {
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
          String name = rs.getString("question");
          // print the results
          System.out.println(name);
        }
       }
       catch(Exception e)
       {
           System.out.println("Error " + e);
       }
   }
   
   public void getGeography(int difficulty) {
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
          String name = rs.getString("question");
          // print the results
          System.out.println(name);
        }
       }
       catch(Exception e)
       {
           System.out.println("Error " + e);
       }
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
