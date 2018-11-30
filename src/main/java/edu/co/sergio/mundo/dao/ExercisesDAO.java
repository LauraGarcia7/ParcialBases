/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sergio.mundo.dao;

import edu.co.sergio.mundo.vo.Exercises;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Labing
 */
public class ExercisesDAO {
      public List<Exercises> findAll() {
		List<Exercises> Exercisess= null;
	    String query = "SELECT * FROM Exercises";
	    Connection connection = null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(ExercisesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	    try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    int ENO =0,MaxPt=0;
	    String CAT = null, Topic=null;
	
	    while (rs.next()){
	    	if(Exercisess == null){
	    		Exercisess= new ArrayList<Exercises>();
	    	}
	      
	        Exercises registro= new Exercises();
	        
	        CAT = rs.getString("CAT");
	        registro.setCAT(CAT) ;
                
                ENO = rs.getInt("ENO");
	        registro.setENO(ENO);
                
                Topic = rs.getString("Topic");
	        registro.setTopic(Topic) ;
                
                MaxPt = rs.getInt("MaxPt");
	        registro.setMaxPt(MaxPt) ;
	        
	        Exercisess.add(registro);
	    }
	    st.close();
	    
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener la lista de Exercises");
			e.printStackTrace();
		}
	    
	    return Exercisess;
	}

	
	/**
	 * Funcion que permite realizar la insercion de un nuevo registro en la tabla Students
	 * @param Students recibe un objeto de tipo Students 
	 * @return boolean retorna true si la operacion de insercion es exitosa.
	 */
	public boolean insert(Exercises t) {
		boolean result=false;
		Connection connection=null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(ExercisesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	    String query = " insert into Exercises (CAT,ENO,Topic,MaxPt)"  + " values (?,?,?,?)";
        PreparedStatement preparedStmt=null;
	    try {
			preparedStmt = connection.prepareStatement(query);
                        preparedStmt.setString (1, t.getCAT());
			preparedStmt.setInt (2, t.getENO());
                        preparedStmt.setString (3, t.getTopic());
                        preparedStmt.setInt (4, t.getMaxPt());
			result= preparedStmt.execute();
	    } catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Funcion que permite realizar la actualizacion de un nuevo registro en la tabla Students
	 * @param Students recibe un objeto de tipo Students 
	 * @return boolean retorna true si la operacion de actualizacion es exitosa.
	 */
	public boolean update(Exercises t) {
		boolean result=false;
		Connection connection= null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(ExercisesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
		String query = "update Exercises set CAT = ?, Topic = ?, MaxPt = ? where ENO = ?";
		PreparedStatement preparedStmt=null;
		try {
		    preparedStmt = connection.prepareStatement(query);
		    preparedStmt.setString(1, t.getCAT());
                    preparedStmt.setString(2, t.getTopic());
                    preparedStmt.setInt(3, t.getMaxPt());
                    preparedStmt.setInt   (4, t.getENO());
		    if (preparedStmt.executeUpdate() > 0){
		    	result=true;
		    }
			    
		} catch (SQLException e) {
				e.printStackTrace();
		}
			
		return result;
	}

	/**
	 * Funcion que permite realizar la eliminario de registro en la tabla Students
	 * @param Students recibe un objeto de tipo Students 
	 * @return boolean retorna true si la operacion de borrado es exitosa.
	 */
	public boolean delete(Exercises t) {
	   boolean result=false;
	   Connection connection = null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(ExercisesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	   String query = "delete from Exercises where ENO = ?";
	   PreparedStatement preparedStmt=null;
	   try {
		     preparedStmt = connection.prepareStatement(query);
		     preparedStmt.setInt(1, t.getENO());
		    result= preparedStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	   
	   return result;
	} 
}
