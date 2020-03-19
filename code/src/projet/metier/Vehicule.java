/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.metier;

/**
 *
 * @author fedi
 */
public class Vehicule {
    
    
  private String matricule;

  private String idMarque;
/*
  public String idvisiteTech;

  public String IdAssurance;
  
*/
  

    public Vehicule(String matricule, String marqueIdf, String visiteTechIdf, String AssuranceIdf) {
this.matricule=matricule;
 this.idMarque=marqueIdf;
/*this.idvisiteTech=visiteTechIdf;
  this.IdAssurance=AssuranceIdf;
  */  }

    public Vehicule(String string, String string0) {
this.matricule=string;
 this.idMarque=string0;

    }

    public Vehicule(String matricule) {
this.matricule=matricule;
    }

   public String getmatricule()
  {
      if(matricule==null)
      return "''"; 
  else 
            return matricule; 

  }
    
    public String getidMarque()
  {
      if(idMarque==null)
      return "''"; 
  else 
            return idMarque; 

  }/*
     public String getidvisiteTech()
  {
      if(idvisiteTech==null)
      return "''"; 
  else 
            return matricule; 

  }
      public String getIdAssurance()
  {
      if(IdAssurance==null)
      return "''"; 
  else 
            return IdAssurance; 

  }
    */
}
