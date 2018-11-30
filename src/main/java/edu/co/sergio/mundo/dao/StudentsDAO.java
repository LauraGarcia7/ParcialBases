/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sergio.mundo.dao;

import edu.co.sergio.mundo.vo.Students;
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
public class StudentsDAO {
    public List<Students> findAll() {
		List<Students> Studentss= null;
	    String query = "SELECT * FROM Students";
	    Connection connection = null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	    try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    int id =0;
	    String nombre = null, apellido=null, email=null;
	
	    while (rs.next()){
	    	if(Studentss == null){
	    		Studentss= new ArrayList<Students>();
	    	}
	      
	        Students registro= new Students();
	        id = rs.getInt("SID");
	        registro.setSID(id);
	        
	        nombre = rs.getString("First");
	        registro.setFirst(nombre) ;
                
                apellido = rs.getString("Last");
	        registro.setLast(apellido) ;
                
                email = rs.getString("Email");
	        registro.setLast(email) ;
	        
	        Studentss.add(registro);
	    }
	    st.close();
	    
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener la lista de Students");
			e.printStackTrace();
		}
	    
	    return Studentss;
	}

	
	/**
	 * Funcion que permite realizar la insercion de un nuevo registro en la tabla Students
	 * @param Students recibe un objeto de tipo Students 
	 * @return boolean retorna true si la operacion de insercion es exitosa.
	 */
	public boolean insert(Students t) {
		boolean result=false;
		Connection connection=null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	    String query = " insert into Students (SID,First,Last,Email)"  + " values (?,?,?,?)";
        PreparedStatement preparedStmt=null;
	    try {
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setInt (1, t.getSID());
                        preparedStmt.setString (2, t.getFirst());
                        preparedStmt.setString (3, t.getLast());
                        preparedStmt.setString (4, t.getEmail());
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
	public boolean update(Students t) {
		boolean result=false;
		Connection connection= null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
		String query = "update Students set Fisrt = ?, Last = ?, Email = ? where SID = ?";
		PreparedStatement preparedStmt=null;
		try {
		    preparedStmt = connection.prepareStatement(query);
		    preparedStmt.setString(1, t.getFirst());
                    preparedStmt.setString(2, t.getLast());
                    preparedStmt.setString(3, t.getEmail());
                    preparedStmt.setInt   (4, t.getSID());
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
	public boolean delete(Students t) {
	   boolean result=false;
	   Connection connection = null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	   String query = "delete from Students where SID = ?";
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
