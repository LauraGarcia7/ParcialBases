/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sergio.mundo.dao;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fabian Giraldo
 */
public class CreateDataBase {
     public static void run(){
//          String sql = "CREATE TABLE Depto(id_depto integer,nom_depto varchar(40), PRIMARY KEY(id_depto))";
          String sql = "CREATE TABLE Students(SID integer,First varchar(30), Last varchar(30), Email varchar(50), PRIMARY KEY(SID);"
                  + " CREATE TABLE Exercises(CAT varchar(30),ENO integer, Topic varchar(30), MaxPt integer, primary key (CAT, ENO);"
                  + "CREATE TABLE Results(SID integer,CAT varchar(30),ENO integer, Point integer, PRIMARY KEY(ENO), foreign key (SID) references students(SID),foreign key (CAT, ENO) references exercises(CAT, ENO));";
         Connection connection = null;
            try {
                connection = Conexion.getConnection();
                Statement stmt = connection.createStatement();
                stmt.executeUpdate(sql);
                
            } catch (URISyntaxException ex) {
                Logger.getLogger(StudentsDAO.class.getName()).log(Level.SEVERE, null, ex);
                Logger.getLogger(ExercisesDAO.class.getName()).log(Level.SEVERE, null, ex);
                Logger.getLogger(ResultsDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
             Logger.getLogger(CreateDataBase.class.getName()).log(Level.SEVERE, null, ex);
         }
          
     }
}
