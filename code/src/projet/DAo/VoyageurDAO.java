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
import projet.DbSingleton;
import projet.metier.Client;
import projet.metier.Voyageur;

/**
 *
 * @author fedi
 */
public class VoyageurDAO extends DAO<Voyageur> {

      
  /*
  --
-- Structure de la table `voyageur`
--

CREATE TABLE IF NOT EXISTS `voyageur` (
  `IdCovoiturage` varchar(10) DEFAULT NULL,
  `NomEtPrenom` varchar(100) DEFAULT NULL,
  `tel` varchar(22) NOT NULL DEFAULT '',
  PRIMARY KEY (`tel`),
  KEY `IdCovoiturage` (`IdCovoiturage`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
  
  --
-- Contraintes pour la table `voyageur`
--
ALTER TABLE `voyageur`
  ADD CONSTRAINT `voyageur_ibfk_1` FOREIGN KEY (`IdCovoiturage`) REFERENCES `covoituragee` (`IdCovoiturage`) ON DELETE SET NULL ON UPDATE CASCADE;
  */
    @Override
    public Boolean insert(Voyageur objet) {
 try {
            Statement  stmt = DbSingleton.getBd().createStatement();
                          String sql = "insert into Voyageur values ("
                            +"'"+objet.getnumCov()+"',"
                            +"'"+objet.getNomEtPrenom()+"',"
                            +"'"+objet.getTel()+"'" 
                    
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
    public Voyageur Select(Voyageur objet) {
 try {
            Statement  stmt = DbSingleton.getBd().createStatement();
      String sql = "SELECT * FROM Voyageur where ( tel =" +"'"+objet.getTel()+"' ) AND( IdCovoiturage =" +"'"+objet.getnumCov()+"')";
            ResultSet rs=stmt.executeQuery(sql);
              if(rs==null)
                 return null;
             else
              {if(rs.next()==false)
              {
              return null;
              }else
                 return new Voyageur(rs.getString("IdCovoiturage"), rs.getString("NomEtPrenom"), rs.getString("tel"));     
              }
              } catch (SQLException ex) {
            Logger.getLogger(AssuranceDAO.class.getName()).log(Level.SEVERE, null, ex);
          return null;
        }
        
             
    }

    @Override
    public Boolean update(Voyageur objet) {
        if(objet!=null){
 try {
            Statement  stmt = DbSingleton.getBd().createStatement();
          String sql = "update  Voyageur set "
                            +"NomEtPrenom='"+objet.getNomEtPrenom()+"',"                    
                    +"where ( tel =" +"'"+objet.getTel()+"' ) AND( IdCovoiturage =" +"'"+objet.getnumCov()+"'	) ;";
                    if(stmt.executeUpdate(sql)==0)
                return false ;
            else
               return true;
        } catch (SQLException ex) {
            Logger.getLogger(AssuranceDAO.class.getName()).log(Level.SEVERE, null, ex);
          return null;
        }
    }          return false;

    }

    @Override
    public Boolean delete(Voyageur objet) {
if(objet!=null)
{ try {
            Statement  stmt = DbSingleton.getBd().createStatement();
            String sql = "delete FROM Voyageur "+"where ( tel =" +"'"+objet.getTel()+"' ) AND( IdCovoiturage =" +"'"+objet.getnumCov()+"');";


            if(stmt.executeUpdate(sql)==0)
                return false ;
            else
               return true;
        } catch (SQLException ex) {
            Logger.getLogger(AssuranceDAO.class.getName()).log(Level.SEVERE, null, ex);
          return null;
        }
}return false;      
    }

    @Override
    public ResultSet selectTable() {
 try {
            Statement  stmt = DbSingleton.getBd().createStatement();
            String sql = "SELECT * FROM Voyageur";
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
        }  
    }
    

    
}
