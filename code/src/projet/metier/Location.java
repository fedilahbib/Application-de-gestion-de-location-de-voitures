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
public class Location {
    
  private String IdLocation;
  
  private String DateDebutLocation;

  private String DateFinLocation;

  private String clientIdf;

  private String vehiculeIdf;

  private float Montant;
  


    public Location(String IdLocation, String clientIdf, String vehiculeIdf, 
            String DateDebutLocation, String DateFinLocation, String Montant) {    
 this.IdLocation=IdLocation;

  this.DateDebutLocation=DateDebutLocation;

 this.DateFinLocation=DateFinLocation;

  this.clientIdf= clientIdf;

  this.vehiculeIdf=vehiculeIdf;

  try{
 this.Montant =Float.parseFloat(Montant);
  }catch(NumberFormatException e)
  {
  this.Montant=0.0f;
  }
  }

    
 public String getIdLocation()
  {
      if(IdLocation==null)
      return "''"; 
  else 
            return IdLocation; 

  }  
 public String getIdClient()
  {
      if(clientIdf==null)
      return "''"; 
  else 
            return clientIdf; 

  }    
 public String getIdVehicule()
  {
      if(vehiculeIdf==null)
      return "''"; 
  else 
            return vehiculeIdf; 

  }    
 public String getDateDebutLocation()
  {
      if(DateDebutLocation==null)
      return "''"; 
  else 
            return DateDebutLocation; 

  }    
 public String getDateFinLocation()
  {
      if(DateFinLocation==null)
      return "''"; 
  else 
            return DateFinLocation; 

  }    
 public String getMontantString()
  {
    
            return String.valueOf(Montant); 

  }    
 public Float getMontantFloat()
  {
     
            return Montant; 

  }    

    
}
