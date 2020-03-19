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
import projet.metier.VisiteTechnique;

/**
 *
 * @author fedi
 */
public class VisiteTechniqueDAO extends DAO<VisiteTechnique> {
/*
--
-- Structure de la table `visitetechnique`
--
CREATE TABLE IF NOT EXISTS `visitetechnique` (
  `IdVT` varchar(10) NOT NULL DEFAULT '',
  `matricule` varchar(10) DEFAULT NULL,
  `DateDebutVisite` date DEFAULT NULL,
  `DateFinVisite` date DEFAULT NULL,
  PRIMARY KEY (`IdVT`),
  KEY `fk_Vehicule2` (`matricule`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
  --
-- Contraintes pour la table `visitetechnique`
--
ALTER TABLE `visitetechnique`
  ADD CONSTRAINT `fk_Vehicule2` FOREIGN KEY (`matricule`) REFERENCES `vehicule` (`matricule`);
  */
    @Override
    public Boolean insert(VisiteTechnique objet) {
 try {
            Statement  stmt = DbSingleton.getBd().createStatement();
             String sql = "insert into VisiteTechnique values ("
                            +"'"+objet.getIdVt()+"',"
                            +"'"+objet.getIdvehicule()+"',"
                            +"'"+objet.getDateDebutVisite()+"'," 
                            +"'"+objet.getDateFinVisite()+"'"
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
    public VisiteTechnique Select(VisiteTechnique objet) {
 try {
            Statement  stmt = DbSingleton.getBd().createStatement();
            String sql = "SELECT * FROM  VisiteTechnique where IdVT =" +"'"+objet.getIdVt()+"'";
          ResultSet rs=stmt.executeQuery(sql);
             if(rs.next()==false)
                 return null;
             else
             {
                 return new VisiteTechnique(rs.getString("IdVT"), rs.getString("matricule"),rs.getString("DateDebutVisite"), rs.getString("DateFinVisite"));
             }
        } catch (SQLException ex) {
            Logger.getLogger(AssuranceDAO.class.getName()).log(Level.SEVERE, null, ex);
          return null;
        }
        
}

    @Override
    public Boolean update(VisiteTechnique objet) {
 if(objet!=null)
 {try {
            Statement  stmt = DbSingleton.getBd().createStatement();
           String sql = "update  VisiteTechnique set "
                            +"DateDebutVisite='"+objet.getDateDebutVisite()+"'," 
                            +"DateFinVisite='"+objet.getDateFinVisite()+"'"
                     
                    +"where IdVT  =" +"'"+objet.getIdVt()+"'";
            
                    if(stmt.executeUpdate(sql)==0)
                       return true;
        } catch (SQLException ex) {
            Logger.getLogger(AssuranceDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false ;
        }
 }              return null;

    }

    @Override
    public Boolean delete(VisiteTechnique objet) {
     return false;
    }

    @Override
    public ResultSet selectTable() {
 try {
            Statement  stmt = DbSingleton.getBd().createStatement();
            String sql = "SELECT * FROM VisiteTechnique ";
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
