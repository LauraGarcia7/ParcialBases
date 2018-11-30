package com.crunchify.jsp.servlet;
 
import edu.co.sergio.mundo.dao.StudentsDAO;
import edu.co.sergio.mundo.vo.Students;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import javax.servlet.RequestDispatcher;
 
/**
 * @author Crunchify.com
 */
 
public class HelloCrunchify extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // reading the user input
        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        
        //Se debe incluir validaciones - Lo recuerda: Gestion de Excepciones.
        StudentsDAO dao = new StudentsDAO();
        
        Students Students = new Students();
        Students.setId_Students(Integer.parseInt(id));
        Students.setNom_Students(nombre);
        dao.insert(Students);
        
        //Listando la informacion  
        List<Students> Studentss =  dao.findAll();
        request.setAttribute("Studentss", Studentss);
       
       
        //Redireccionando la informacion
        RequestDispatcher redireccion = request.getRequestDispatcher("index.jsp");
        redireccion.forward(request, response);
        
        
        
        }
}
