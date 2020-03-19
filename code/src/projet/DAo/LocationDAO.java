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
import projet.DAo.DAO;
import projet.DbSingleton;
import projet.metier.Client;
import projet.metier.Location;

/**
 *
 * @author fedi
 */
public class LocationDAO extends DAO<Location>{

      /*
  
  --
-- Structure de la table `location`
--

CREATE TABLE IF NOT EXISTS `location` (
  `IdLocation` varchar(10) NOT NULL DEFAULT '',
  `cin` varchar(10) DEFAULT NULL,
  `matricule` varchar(10) DEFAULT NULL,
  `DateDebutLocation` date DEFAULT NULL,
  `DateFinLocation` date DEFAULT NULL,
  `Montant` decimal(20,5) DEFAULT NULL,
  PRIMARY KEY (`IdLocation`),
  KEY `matricule` (`matricule`),
  KEY `cin` (`cin`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

  -- Contraintes pour la table `location`
--
ALTER TABLE `location`
  ADD CONSTRAINT `location_ibfk_3` FOREIGN KEY (`cin`) REFERENCES `client` (`cin`) ON DELETE SET NULL,
  ADD CONSTRAINT `location_ibfk_4` FOREIGN KEY (`matricule`) REFERENCES `vehicule` (`matricule`) ON DELETE SET NULL;

--
  
  */
    @Override
    public Boolean insert(Location objet) {
      try {
            String sql = "INSERT INTO `location` VALUES  ("
                            +"'"+objet.getIdLocation()+"',"
                            +"'"+objet.getIdClient()+"',"
                            +"'"+objet.getIdVehicule()+"'," 
                            +"'"+objet.getDateDebutLocation()+"',"
                            +"'"+objet.getDateFinLocation()+"'"
                            +","+objet.getMontantString()+")";
   
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
    public Location Select(Location objet) {
 try {
            Statement  stmt = DbSingleton.getBd().createStatement();
            String sql = "SELECT * FROM Location where IdLocation  =" +"'"+objet.getIdLocation()+"'";
             ResultSet rs=stmt.executeQuery(sql);
             if(rs==null)
                 return null;
             else
             {rs.next();
                 return new Location(rs.getString("IdLocation"),rs.getString("cin"), rs.getString("matricule"), rs.getString("DateDebutLocation"), rs.getString("DateFinLocation"), rs.getString("Montant"));
             }
        } catch (SQLException ex) {
            Logger.getLogger(AssuranceDAO.class.getName()).log(Level.SEVERE, null, ex);
          return null;
        }
        

    }

    @Override
    public Boolean update(Location objet) {
 if(objet!=null){
        try {
           Statement  stmt = DbSingleton.getBd().createStatement();
            String sql = "update  Location set "
                            +"cin='"+objet.getIdClient()+"',"
                            +"matricule='"+objet.getIdVehicule()+"'," 
                            +"DateDebutLocation='"+objet.getDateDebutLocation()+"',"
                    +"DateFinLocation='"+objet.getDateFinLocation()+"',"
                            +"Montant='"+objet.getMontantString()+"' "
                    +"where IdLocation =" +"'"+objet.getIdLocation()+"';";
            
                    if(stmt.executeUpdate(sql)==0)
                return false ;
            else
               return true;
        } catch (SQLException ex) {
            Logger.getLogger(AssuranceDAO.class.getName()).log(Level.SEVERE, null, ex);
          return null;
        }
 }return null;
    }

    
    
    @Override
    public Boolean delete(Location objet) {
 try {
                Statement  stmt = DbSingleton.getBd().createStatement();
            String sql = "delete FROM Location "+"where IdLocation =" +"'"+objet.getIdLocation()+"'";
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
            String sql = "SELECT * FROM Location";
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
