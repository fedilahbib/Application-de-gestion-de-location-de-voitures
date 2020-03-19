/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.metier;

import java.util.Date;

/**
 *
 * @author fedi
 */
public class Assurance {
      private String DateDebutAss;

  private String DateFinAss;

  private String IdAss;

  private String idvehicule;
  
  
  /*
-- Structure de la table `assurance`
--

CREATE TABLE IF NOT EXISTS `assurance` (
  `IdAss` varchar(10) NOT NULL DEFAULT '',
  `matricule` varchar(10) DEFAULT NULL,
  `DateDebutAss` date DEFAULT NULL,
  `DateFinAss` date DEFAULT NULL,
  PRIMARY KEY (`IdAss`),
  KEY `matricule` (`matricule`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

  ALTER TABLE `assurance`
  ADD CONSTRAINT `assurance_ibfk_1` FOREIGN KEY (`matricule`) REFERENCES `vehicule` (`matricule`) ON DELETE SET NULL ON UPDATE CASCADE;

  
  
  */
    public Assurance(String IdAss, String idvehicule, String DateDebutAss, String DateFinAss) {
  

 this.IdAss=IdAss;

 this.idvehicule=idvehicule;
 this.DateDebutAss=DateDebutAss;

  this.DateFinAss=DateFinAss;
        
  }
    public Assurance(String IdAss) {

 this.IdAss=IdAss;
         }
    
    
      public String getDateDebutAss()
  {
      if(DateDebutAss==null)
      return "''"; 
  else 
            return DateDebutAss; 

  }

  public String getDateFinAss()
  {
       if(DateFinAss==null)
      return "''"; 
  else 
            return DateFinAss; 

  
  
  }


  public String getIdAss()
  {
  
     if(IdAss==null)
      return "''"; 
  else 
            return IdAss; 

  
  }

  public String getIdvehicule()
  {
  if(idvehicule==null)
      return "''"; 
  else 
            return idvehicule; 

 
  }


}
