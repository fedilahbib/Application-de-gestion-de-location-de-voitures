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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import projet.DAo.DAO;
import projet.DbSingleton;
import projet.metier.Marque;
import projet.metier.VisiteTechnique;

/**
 *
 * @author fedi
 */
public class MarqueDAO extends DAO<Marque> {

      /*
  --
-- Structure de la table `marque`
--

CREATE TABLE IF NOT EXISTS `marque` (
  `IdMarque` varchar(10) NOT NULL DEFAULT '',
  `caractéristiques` varchar(245) DEFAULT NULL,
  `tarifJour` float(10,3) DEFAULT NULL,
  `nomModele` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`IdMarque`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
  */

    @Override
    public Boolean insert(Marque objet) {
        
         try {
            Statement  stmt = DbSingleton.getBd().createStatement();
                    String sql = "insert into Marque values ("
                            +"'"+objet.getIdmarque()+"',"
                            +"'"+objet.getCaracteristiques()+"',"
                            +""+objet.getTarifJourString()+"," 
                            +"'"+objet.getMarque()+"'"
          +")";
           if (stmt.executeUpdate(sql)==0)
               return false;
           else 
               return true;
           
        } catch (SQLException ex) {
            Logger.getLogger(AssuranceDAO.class.getName()).log(Level.SEVERE, null, ex);
          return null;
        }
    }

    @Override
    public Marque Select(Marque objet) {
        if(objet!=null){
  try {
            Statement  stmt = DbSingleton.getBd().createStatement();
//             String sql = "SELECT * FROM  Marque where IdMarque ="+"'"+objet.getIdmarque()+"'";
System.err.println("id marque"+objet.getIdmarque());
            String sql ="SELECT * FROM  `marque` where IdMarque ="+"'"+objet.getIdmarque()+"'";
          ResultSet rs=stmt.executeQuery(sql);
             if(rs==null)
                 return null;
             else
             {rs.next();
 return new Marque(rs.getString("IdMarque"), rs.getString("caracteristiques"),String.valueOf(rs.getFloat("tarifJour")),rs.getString("nomModele"));
             }
        } catch (SQLException ex) {
            Logger.getLogger(AssuranceDAO.class.getName()).log(Level.SEVERE, null, ex);
          return null;
        }

    }return null;
}
    @Override
    public Boolean update(Marque objet) {
 try {
     //   +"	caracteristiques='"+objet.getCaracteristiques()+"',"
  //    +"tarifJour="+objet.getTarifJourString()+"," 
     if(objet!=null)
     {Statement  stmt = DbSingleton.getBd().createStatement();
            String sql = "update Marque set" +"	caracteristiques='"+objet.getCaracteristiques()+"',"+
                    " tarifJour="+objet.getTarifJourString()+","+ " nomModele='"+objet.getMarque()+"'"
                    + "where IdMarque =" +"'"+objet.getIdmarque()+"'";
            if(stmt.executeUpdate(sql)==0)
                return false ;
            else
               return true;
     }      
        } catch (SQLException ex) {
            Logger.getLogger(AssuranceDAO.class.getName()).log(Level.SEVERE, null, ex);
          return null;
        }
                  return null;
    }

    @Override
    public Boolean delete(Marque objet) {

try {
            Statement  stmt = DbSingleton.getBd().createStatement();
            String sql = "delete FROM Marque "+"where IdMarque =" +"'"+objet.getIdmarque()+"'";

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
            String sql = "SELECT * FROM Marque ";
            return stmt.executeQuery(sql);
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(new JFrame(), "erreur de ce connecter au base");
            
Logger.getLogger(AssuranceDAO.class.getName()).log(Level.SEVERE, null, ex);
          return null;
        }

    }
  
    public ResultSet selectCondition(String sql) {
 try {
            Statement  stmt = DbSingleton.getBd().createStatement();
            return stmt.executeQuery(sql);
        } catch (SQLException ex) {
          return null;
        }

    }  
}
