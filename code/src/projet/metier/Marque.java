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
public class Marque {
    
  private String idmarque;

  private String caracteristiques;

  private float tarifJour;

  private String Marque;

    public Marque(String marqueIdf, String caracteristiques, String tarifJour ,String Modele) {
        this.idmarque =marqueIdf;
  this.caracteristiques=caracteristiques;
try{
  this.tarifJour =Float.parseFloat(tarifJour);
}catch(NumberFormatException e){
    System.err.println("exe");
    this.tarifJour =0;
}
  this.Marque =Modele;        
    }
 public Marque(String marqueIdf) {

        this.idmarque =marqueIdf;
      
 }

  public String getIdmarque()
  {

     if(idmarque==null)
      return "''"; 
  else 
            return idmarque; 

  
  }

  
   public void setIdmarque(String s)
  {
  
     this.idmarque=s; 

  
  }

  
  public String getCaracteristiques()
  {
  
     if(caracteristiques==null)
      return "''"; 
  else 
            return caracteristiques; 

  
  }

  public String getMarque()
  {
  
     if( Marque==null)
      return "''"; 
  else 
            return Marque; 

  
  }

  public String getTarifJourString()
  {
  
     
         return String.valueOf(this.tarifJour); 
 

  
  }


public Float getTarifJourFloat(  )
  {
  
    
      return tarifJour; 
 
  
  }



}
