/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package answerquestgame.Helpers;

import java.sql.*;

/**
 *
 * @author juanpabloramos
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
   
   public void getData() {
        try {
            // ?user=sandbox
            String myDriver = "com.mysql.cj.jdbc.Driver";
            String myUrl = "jdbc:mysql://zerosumsandbox.c91oxw9rgzr9.us-east-1.rds.amazonaws.com:3306/sandbox";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "sandbox", "answerquest");

            Statement st = conn.createStatement();
//            String query = "SELECT * FROM Test";
//            ResultSet rs = st.executeQuery(query);
//            while (rs.next())
//            {
//              int id = rs.getInt("idTest");
//              // print the results
//              System.out.println(id);
//            }
            

            // note that i'm leaving "date_created" out of this insert statement
            st.executeUpdate("INSERT INTO Test (name, score) "
                +"VALUES ('JP', 12)");
//            conn.close();    
            System.out.println("okokok");
        } catch (Exception ex) {
            System.out.println(ex);
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
