/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.DAo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import projet.metier.Client;
import projet.DAo.DAO;
import projet.DbSingleton;

/**
 *
 * @author fedi
 */
public class ClientDAO extends DAO<Client>{

    @Override
    public Boolean insert(Client objet) {    
              String sql = "insert into client values ("
                            +"'"+objet.getCin()+"',"
                            +"'"+objet.getNom()+"',"
                            +"'"+objet.getPrenom()+"'," 
                            +"'"+objet.getAdresse()+"',"
                            +"'"+objet.getTel()+"'"
                            +")";
                    try {
            Statement  stmt = DbSingleton.getBd().createStatement();
           
                       int executeQuery = stmt.executeUpdate(sql);
            if(DbSingleton.getBd()!=null)
            {System.err.println("erreur d inset");
            return false ;}
            else
               return true;
           } catch (SQLException ex) {
            Logger.getLogger(AssuranceDAO.class.getName()).log(Level.SEVERE, null, ex);
          return false;
        }
        
        

    }

    @Override
    public Client Select(Client objet) {      
           try {
            Statement  stmt = DbSingleton.getBd().createStatement();
            String sql = "SELECT * FROM client where cin  =" +"'"+objet.getCin()+"';";
             ResultSet rs=stmt.executeQuery(sql);
             if(rs==null)
                 return null;
             else
             {rs.next();
                 return new Client(rs.getString("cin"), rs.getString("nom"),rs.getString("prenom"), rs.getString("adresse"), rs.getString("tel"));
             }
             } catch (SQLException ex) {
           // Logger.getLogger(AssuranceDAO.class.getName()).log(Level.SEVERE, null, ex);
          return null;
        }
        
           
     
    }

    @Override
    public Boolean update(Client objet) {
        if(objet!=null)   
        {try {
            Statement  stmt = DbSingleton.getBd().createStatement();
            //String sql = "UPDATE `client` SET `nom`='salim',`prenom`='ddd',`adresse`='ddd',`tel`='' where 'cin'='09874899'";
            String sql =  "UPDATE  `client`SET "               
                            +"`nom`='"+objet.getNom()+"',"
                            +"`prenom`='"+objet.getPrenom()+"'," 
                            +"`adresse`='"+objet.getAdresse()+"',"
                            +"`tel`='"+objet.getTel()+"' "
                    +"where cin  =" +"'"+objet.getCin()+"'";
            
                    if(stmt.executeUpdate(sql)==0)
                    {                  
                        return false ;
                    }else
               return true;
        } catch (SQLException ex) {
            Logger.getLogger(AssuranceDAO.class.getName()).log(Level.SEVERE, null, ex);
          return null;
        }
        
        }
        return null;
    }

    @Override
    public Boolean delete(Client objet) {
           try {
            Statement  stmt = DbSingleton.getBd().createStatement();
            String sql = "delete FROM client "+"where cin  =" +"'"+objet.getCin()+"';";

            if(stmt.executeUpdate(sql)==0)
                return false ;
            else
               return true;
           } catch (SQLException ex) {
            Logger.getLogger(AssuranceDAO.class.getName()).log(Level.SEVERE, null, ex);
          return null;
        }
       
    }

   
    @Override
    public ResultSet selectTable() {
 try {
            Statement  stmt = DbSingleton.getBd().createStatement();
            String sql = "SELECT * FROM client ;";
            return stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(AssuranceDAO.class.getName()).log(Level.SEVERE, null, ex);
          return null;
        }
    }
    
}
