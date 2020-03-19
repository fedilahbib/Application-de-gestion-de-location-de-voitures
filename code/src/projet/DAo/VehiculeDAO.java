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
import projet.metier.Vehicule;

/**
 *
 * @author fedi
 */
public class VehiculeDAO extends DAO<Vehicule>{
    
      /*
  --
-- Structure de la table `vehicule`
--
CREATE TABLE IF NOT EXISTS `vehicule` (
  `matricule` varchar(10) NOT NULL DEFAULT '',
  `IdMarque` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`matricule`),
  KEY `fk_Marque` (`IdMarque`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

  -- Contraintes pour la table `vehicule`
--
ALTER TABLE `vehicule`
  ADD CONSTRAINT `fk_Marque` FOREIGN KEY (`IdMarque`) REFERENCES `marque` (`IdMarque`);
  */

    @Override
    public Boolean insert(Vehicule objet) {
    try {
            Statement  stmt = DbSingleton.getBd().createStatement();
                  String sql = "insert into Vehicule values ("
                            +"'"+objet.getmatricule()+"',"
                           +"'"+objet.getidMarque()+"'"
                            +");";
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
    public Vehicule Select(Vehicule objet) {
      if(objet!=null){
        try {
            Statement  stmt = DbSingleton.getBd().createStatement();
             String sql = "SELECT * FROM Vehicule where matricule  =" +"'"+objet.getmatricule()+"';";
             ResultSet rs=stmt.executeQuery(sql);
            
             if(rs.next()==false)
                 return null;
             else
             {
                 return new Vehicule(rs.getString("matricule"), rs.getString("IdMarque"));
             }
        } catch (SQLException ex) {
          return null;
        }

      }          return null;

    }

    @Override
    public Boolean update(Vehicule objet) {
 if(objet!=null)
 {        try {
            Statement  stmt = DbSingleton.getBd().createStatement();
             String sql = "update  Vehicule set "
                            +"IdMarque='"+objet.getidMarque()+"' "
                    +"where matricule  =" +"'"+objet.getmatricule()+"'";
           if(stmt.executeUpdate(sql)==0)
                return false ;
            else
               return true;
        } catch (SQLException ex) {
            Logger.getLogger(AssuranceDAO.class.getName()).log(Level.SEVERE, null, ex);
        } }      
            return null;

    }

    @Override
    public Boolean delete(Vehicule objet) {
      try {
            Statement  stmt = DbSingleton.getBd().createStatement();
                       String sql = "delete FROM Vehicule "+"where matricule  =" +"'"+objet.getmatricule()+"';";
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
            String sql = "SELECT * FROM Vehicule";
            return stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(AssuranceDAO.class.getName()).log(Level.SEVERE, null, ex);
          return null;
        }  }
    
    
    public ResultSet selectCondtion( String sql) {
 try {
            Statement  stmt = DbSingleton.getBd().createStatement();
            return stmt.executeQuery(sql);
        } catch (SQLException ex) {
          return null;
        }  }
    
}
