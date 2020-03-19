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
public class VisiteTechnique {
    
    
      private String DateDebutVisite;

  private String DateFinVisite;

  private String vtIdf;
  
  private String idvehicule;

  
    public VisiteTechnique(String vtIdf, String idvehicule, String DateDebutVisite, String DateFinVisite) {
 
       
     this.DateDebutVisite=DateDebutVisite;


  this.DateFinVisite= DateFinVisite;


  this.vtIdf=  vtIdf;

  
 this.idvehicule=idvehicule;

    }
public VisiteTechnique(String vtIdf) {
 
  this.vtIdf=  vtIdf;

  

    }
   
      public String getDateDebutVisite()
  {
      if(DateDebutVisite==null)
      return "''"; 
  else 
            return DateDebutVisite; 

  }

  public String getDateFinVisite()
  {
       if(DateFinVisite==null)
      return "''"; 
  else 
            return DateFinVisite; 

  
  
  }


  public String getIdVt()
  {
  
     if(vtIdf==null)
      return "''"; 
  else 
            return vtIdf; 

  
  }

  public String getIdvehicule()
  {
  if(idvehicule==null)
      return "''"; 
  else 
            return idvehicule; 

 
  }



}




