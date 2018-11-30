/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sergio.mundo.dao;

import edu.co.sergio.mundo.vo.Results;
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
public class ResultsDAO {
       public List<Results> findAll() {
		List<Results> Resultss= null;
	    String query = "SELECT * FROM Results";
	    Connection connection = null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(ResultsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	    try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    int SID =0,ENO=0,Points=0;
	    String CAT = null;
	
	    while (rs.next()){
	    	if(Resultss == null){
	    		Resultss= new ArrayList<Results>();
	    	}
	      
	        Results registro= new Results();
	        SID = rs.getInt("SID");
	        registro.setSID(SID);
	        
	        CAT = rs.getString("CAT");
	        registro.setCAT(CAT) ;
                
                ENO = rs.getInt("ENO");
	        registro.setENO(ENO);
                
                Points = rs.getInt("Points");
	        registro.setPoints(Points);
	        
	        Resultss.add(registro);
	    }
	    st.close();
	    
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener la lista de Results");
			e.printStackTrace();
		}
	    
	    return Resultss;
	}

	
	/**
	 * Funcion que permite realizar la insercion de un nuevo registro en la tabla Students
	 * @param Students recibe un objeto de tipo Students 
	 * @return boolean retorna true si la operacion de insercion es exitosa.
	 */
	public boolean insert(Results t) {
		boolean result=false;
		Connection connection=null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(ResultsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	    String query = " insert into Results (SID,CAT,ENO,Points)"  + " values (?,?,?,?)";
        PreparedStatement preparedStmt=null;
	    try {
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setInt (1, t.getSID());
                        preparedStmt.setString (2, t.getCAT());
                        preparedStmt.setInt (3, t.getENO());
                        preparedStmt.setInt (4, t.getPoints());
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
	public boolean update(Results t) {
		boolean result=false;
		Connection connection= null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(ResultsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
		String query = "update Results set CAT = ?, ENO = ?, Points = ? where SID = ?";
		PreparedStatement preparedStmt=null;
		try {
		    preparedStmt = connection.prepareStatement(query);
		    preparedStmt.setString(1, t.getCAT());
                    preparedStmt.setInt(2, t.getENO());
                    preparedStmt.setInt(3, t.getPoints());
                    preparedStmt.setInt (4, t.getSID());
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
	public boolean delete(Results t) {
	   boolean result=false;
	   Connection connection = null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(ResultsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	   String query = "delete from Results where SID = ?";
	   PreparedStatement preparedStmt=null;
	   try {
		     preparedStmt = connection.prepareStatement(query);
		     preparedStmt.setInt(1, t.getSID());
		    result= preparedStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	   
	   return result;
	}
}
