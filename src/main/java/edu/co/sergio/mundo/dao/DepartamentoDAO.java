package edu.co.sergio.mundo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import edu.co.sergio.mundo.vo.Students;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Isabel-Fabian
 * @since 12/08/2015
 * @version 2
 * Clase que permite la gestion de la tabla Depto en la base de datos.
 * 
 * CREATE TABLE Depto(
 *     id_depto integer,
 *     nom_depto varchar(40),
 *     PRIMARY KEY(id_depto)
 * );
 */
 

public class DepartamentoDAO implements IBaseDatos<Students> {

	/**
	 * Funcion que permite obtener una lista de los Studentss existentes en la base de datos
	 * @return List<Students> Retorna la lista de Studentss existentes en la base de datos
	 */
	public List<Students> findAll() {
		List<Students> Studentss= null;
	    String query = "SELECT * FROM Depto";
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
	    String nombre = null;
	
	    while (rs.next()){
	    	if(Studentss == null){
	    		Studentss= new ArrayList<Students>();
	    	}
	      
	        Students registro= new Students();
	        id = rs.getInt("id_depto");
	        registro.setId_Students(id);
	        
	        nombre = rs.getString("nom_depto");
	        registro.setNom_Students(nombre) ;
	        
	        Studentss.add(registro);
	    }
	    st.close();
	    
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener la lista de Studentss");
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
	    String query = " insert into Depto (id_depto,nom_depto)"  + " values (?,?)";
        PreparedStatement preparedStmt=null;
	    try {
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setInt (1, t.getId_Students());
                        preparedStmt.setString (2, t.getNom_Students());
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
		String query = "update Depto set nom_depto = ? where id_depto = ?";
		PreparedStatement preparedStmt=null;
		try {
		    preparedStmt = connection.prepareStatement(query);
		    preparedStmt.setString(1, t.getNom_Students());
                    preparedStmt.setInt   (2, t.getId_Students());
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
	   String query = "delete from Depto where id_depto = ?";
	   PreparedStatement preparedStmt=null;
	   try {
		     preparedStmt = connection.prepareStatement(query);
		     preparedStmt.setInt(1, t.getId_Students());
		    result= preparedStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	   
	   return result;
	}
}
