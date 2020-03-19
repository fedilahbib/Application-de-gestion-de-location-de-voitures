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
import projet.metier.Covoiturage;
import projet.DAo.DAO;
import projet.DbSingleton;
import projet.metier.Client;

/**
 *
 * @author fedi
 */
public class CovoiturageDAO extends DAO<Covoiturage> {
    
       /*
          IdCovoiturage VARCHAR(10)

   IdLocation VARCHAR(10) ,
  
   DateDebutLocation DATE ,

   DateFinLocation DATE , 

   lieuDepart VARCHAR(100),

   lieuDestination VARCHAR(100),

   nbPlaceDispo INTEGER(6)
  */      

    @Override
    public Boolean insert(Covoiturage objet) {
        try {
             String sql = "insert into Covoituragee values ("
                            +"'"+objet.Getidcovoiturage()+"',"
                            +"'"+objet.GetidLocation()+"',"
                            +"'"+objet.GetDateDebutLocation()+"'," 
                            +"'"+objet.GetDateFinLocation()+"',"
                            +"'"+objet.GetlieuDepart()+"',"
                            +"'"+objet.GetlieuDestination()+"',"       
                            +""+objet.GetnbPlaceDispo()+""
                            +");";
            Statement  stmt = DbSingleton.getBd().createStatement();
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
    public Covoiturage Select(Covoiturage objet) {
           try {
            Statement  stmt = DbSingleton.getBd().createStatement();
            String sql = "SELECT * FROM Covoituragee where IdCovoiturage =" +"'"+objet.Getidcovoiturage()+"';";
             ResultSet rs=stmt.executeQuery(sql);
             if(rs==null)
                 return null;
             else
             {
                 if(rs.next()==false)
                 return null;
             else
                 return new Covoiturage(rs.getString("IdCovoiturage"), rs.getString("IdLocation"), rs.getString("DateDebutLocation"), rs.getString("DateFinLocation"), rs.getString("lieuDepart"), rs.getString("lieuDestination"), rs.getString("nbPlaceDispo"));
             }} catch (SQLException ex) {
            Logger.getLogger(AssuranceDAO.class.getName()).log(Level.SEVERE, null, ex);
          return null;
        }
          

    }

    @Override
    public Boolean update(Covoiturage objet) {
        if(objet!=null){   
        try {
             Statement  stmt = DbSingleton.getBd().createStatement();
            String sql = "update  Covoituragee set "
                    +"IdLocation='"+objet.GetidLocation()+"',"
                          +"DateDebutLocation='"+objet.GetDateDebutLocation()+"'," 
                            +"DateFinLocation='"+objet.GetDateFinLocation()+"'," 
                            +"lieuDepart='"+objet.GetlieuDepart()+"',"
                            +"lieuDestination='"+objet.GetlieuDestination()+"',"
                    +"nbPlaceDispo='"+objet.GetnbPlaceDispo()+"' "
                    +"where IdCovoiturage  =" +"'"+objet.Getidcovoiturage()+"';";
            
                    if(stmt.executeUpdate(sql)==0)
                return false ;
            else
               return true;
      
        } catch (SQLException ex) {
            Logger.getLogger(AssuranceDAO.class.getName()).log(Level.SEVERE, null, ex);
          return null;
        }
        }else{return false;}
    }

    @Override
    public Boolean delete(Covoiturage objet) {
           try {
           Statement  stmt = DbSingleton.getBd().createStatement();
            String sql = "delete FROM Covoituragee " +"where IdCovoiturage  =" +"'"+objet.Getidcovoiturage()+"';";

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
            String sql = "SELECT * FROM Covoituragee";
            return stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(AssuranceDAO.class.getName()).log(Level.SEVERE, null, ex);
          return null;
        }
    }
       public ResultSet selectCondtion( String sql) {
 try {
            Statement  stmt = DbSingleton.getBd().createStatement();
            return stmt.executeQuery(sql);
        } catch (SQLException ex) {
          return null;
        }  }
    
}
