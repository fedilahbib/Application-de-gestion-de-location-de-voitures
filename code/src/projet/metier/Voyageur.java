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
public class Voyageur {
   
  public String numCov;

  public String NomEtPrenom;

  public String tel;


public  Voyageur(String numCov,String NomEtPrenom, String tel) {
 this.numCov=numCov;

  this.NomEtPrenom=NomEtPrenom;

  this.tel=tel;
}

public  Voyageur(String numCov , String tel)
{
 this.numCov=numCov;

  this.tel=tel;
}
   public String getnumCov()
  {
      if(numCov==null)
      return "''"; 
  else 
            return numCov; 

  }
   
      public String getNomEtPrenom()
  {
      if(NomEtPrenom==null)
      return "''"; 
  else 
            return NomEtPrenom; 

  }
   public String getTel()
  {
      if(tel==null)
      return "''"; 
  else 
            return tel; 

  }




}
