/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.DAo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author fedi
 */
public abstract class DAO<T> {

  //public Connector connection;
    Connection conection;

  public abstract Boolean insert(T objet);

  public abstract T Select(T objet);

  public abstract Boolean update(T objet);

  public abstract Boolean delete(T objet);
   public abstract ResultSet selectTable();

  public void DAO(Connection conection) {

      
  
  }
}