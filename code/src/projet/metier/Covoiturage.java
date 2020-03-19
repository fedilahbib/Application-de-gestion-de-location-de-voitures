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
public class Covoiturage {
  

    private String idcovoiturage;

  private String idLocation;
  
  private String DateDebutCovoiturage;

  private String DateFinCovoiturage;

  private String lieuDepart;

  private String lieuDestination;

  private Integer nbPlaceDispo;
/*
 
--
-- Structure de la table `covoituragee`
--

CREATE TABLE IF NOT EXISTS `covoituragee` (
  `IdCovoiturage` varchar(10) NOT NULL DEFAULT '',
  `IdLocation` varchar(10) DEFAULT NULL,
  `DateDebutLocation` date DEFAULT NULL,
  `DateFinLocation` date DEFAULT NULL,
  `lieuDepart` varchar(100) DEFAULT NULL,
  `lieuDestination` varchar(100) DEFAULT NULL,
  `nbPlaceDispo` int(6) DEFAULT NULL,
  PRIMARY KEY (`IdCovoiturage`),
  KEY `fk_Location` (`IdLocation`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------
 --
-- Contraintes pour la table `covoituragee`
--
ALTER TABLE `covoituragee`
  ADD CONSTRAINT `fk_Location` FOREIGN KEY (`IdLocation`) REFERENCES `location` (`IdLocation`);

  
  */ 

  public  Covoiturage (String covoiturageIdf,String idLocation,String DateDebutCovoiturage,String DateFinCovoiturage,

String lieuDepart,String lieuDestination,String nbPlaceDispo){
    
this.idcovoiturage=covoiturageIdf;

this.idLocation=idLocation;

this.DateDebutCovoiturage=DateDebutCovoiturage;


this.DateFinCovoiturage=DateFinCovoiturage;

this. lieuDepart=lieuDepart;

this.lieuDestination=lieuDestination;
try{
this.nbPlaceDispo=Integer.parseInt(nbPlaceDispo);
}catch(NumberFormatException e)
{
this.nbPlaceDispo=0;
}

}

  
    public  Covoiturage (String covoiturageIdf)
    {this.idcovoiturage=covoiturageIdf;
}

 public String  Getidcovoiturage()
{
return idcovoiturage;
}
public String GetidLocation()
{
return idLocation;

}
public String GetDateDebutLocation()
{
return DateDebutCovoiturage;

}
public String GetDateFinLocation()
{
return DateFinCovoiturage;

}
public String GetlieuDepart()
{
return lieuDepart;

}
public String GetlieuDestination()
{
return lieuDestination;

}
public String GetnbPlaceDispo()
{
return String.valueOf(nbPlaceDispo);

}
public int GetnbPlaceDispoInteger()
{
return nbPlaceDispo;

}




}
