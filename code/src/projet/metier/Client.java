package projet.metier;

/**
 *
 * @author fedi
 */
public class Client {
    
  private String cin;

  private String nom;

  private String prenom;

  private String adresse;

  private String tel;
 
  /*
  --
-- Structure de la table `client`
--

CREATE TABLE IF NOT EXISTS `client` (
  `cin` varchar(10) NOT NULL DEFAULT '',
  `nom` varchar(50) DEFAULT NULL,
  `prenom` varchar(50) DEFAULT NULL,
  `adresse` varchar(100) DEFAULT NULL,
  `tel` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`cin`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

  
  
  */
  public Client (String cin, String nom,String prenom,String adresse,String tel)
  {
      this.cin=cin;

 this.nom=nom;

this.prenom=prenom;

this.adresse=adresse;

 this.tel=tel;
  }
  
    public Client (String cin)
  {
      this.cin=cin;
  }
  
 public String getCin()       
 {
 if(cin==null)    
     return "";
 else
   return cin;  
 
 }
  public String getNom()
 {
     if(nom==null)    
     return "''";
 else
     return nom;}
  public String getPrenom()
 {if(prenom==null)    
     return "''";
 else
     return prenom;} 
  public String getAdresse()
 {if(adresse==null)    
     return "''";
 else
     return adresse;}
 public String getTel()         
 {if(tel==null)    
     return "''";
 else
     return tel;}
  

}
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  

  

