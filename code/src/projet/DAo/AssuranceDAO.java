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
import projet.metier.Assurance;
import projet.metier.Assurance;
import projet.DAo.DAO;
import projet.DbSingleton;

/**
 *
 * @author fedi
 */
public class AssuranceDAO extends DAO<Assurance> {

    @Override
    public Boolean insert(Assurance objet) {
           try {
            Statement  stmt = DbSingleton.getBd().createStatement();
  String sql = "insert into assurance values ("
                            +"'"+objet.getIdAss()+"',"
                            +"'"+objet.getIdvehicule()+"',"
                            +"'"+objet.getDateDebutAss()+"'," 
                            +"'"+objet.getDateFinAss()+"'"
          +")";
           if (stmt.executeUpdate(sql)==0)
               return false;
           else 
               return true;
           
        } catch (SQLException ex) {
            Logger.getLogger(AssuranceDAO.class.getName()).log(Level.SEVERE, null, ex);
                         return false;

        }


    }

    @Override
    public Assurance Select(Assurance objet) {
        if(objet!=null){try {
            Statement  stmt = DbSingleton.getBd().createStatement();
            String sql = "SELECT * FROM assurance "
                    + " where IdAss=" +"'"+objet.getIdAss()+"';";
           ResultSet rs=stmt.executeQuery(sql);
            if (false==rs.next())
                return null;
            else
            {
               return new Assurance(rs.getString("IdAss"), rs.getString("matricule"), rs.getString("DateDebutAss"), rs.getString("DateFinAss"));
            }
           } catch (SQLException ex) {
            Logger.getLogger(AssuranceDAO.class.getName()).log(Level.SEVERE, null, ex);
          return null;
        }
        
        }                return null;  
    }

    @Override
    public Boolean update(Assurance objet) {
   if(objet!=null)
   {
        try {
            Statement  stmt = DbSingleton.getBd().createStatement();
            String sql = "update assurance set "
                       +"DateDebutAss= '"+objet.getDateDebutAss()+"' , "
                       +"DateFinAss= '"+objet.getDateFinAss()+"' "
                       +"where IdAss= '"+objet.getIdAss()+"';";
             if(stmt.executeUpdate(sql)==0)
                 return false;
             else
                 return true;
        } catch (SQLException ex) {
            Logger.getLogger(AssuranceDAO.class.getName()).log(Level.SEVERE, null, ex);
          return false;
        }
        
   }    return null;
    }

    @Override
    public Boolean delete(Assurance objet) {
       
return false;
    }

    @Override
    public ResultSet selectTable(){
        
        try {
            Statement  stmt = DbSingleton.getBd().createStatement();
            String sql = "SELECT * FROM assurance;";
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
