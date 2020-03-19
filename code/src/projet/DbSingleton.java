/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author fedi
 */
public class DbSingleton {

   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL =   "jdbc:mysql://127.0.0.1/bd";
   static final String USER = "root";
   static final String PASS = "";
  private static Connection Connection;
 Statement stmt = null;
   


  private DbSingleton() 
  {
       try {
           Class.forName("com.mysql.jdbc.Driver");
      
       } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(new JFrame(), "erreur de connecter au base");
System.exit(0);

           //Logger.getLogger(DbSingleton.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       
       try {
           Connection = DriverManager.getConnection(DB_URL, USER, PASS);
       } catch (SQLException ex) {
          JOptionPane.showMessageDialog(new JFrame(), "erreur de ce connecter au base");
System.exit(0);
//Logger.getLogger(DbSingleton.class.getName()).log(Level.SEVERE, null, ex);
       }
  }
 
 
     public static Connection getBd() {
  if(Connection == null){
      new DbSingleton();
  }
    return Connection;      }

    
}
