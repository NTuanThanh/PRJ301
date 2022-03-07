/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.DepartmentDBContext;
import dal.StudentDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Department;
import model.Student;

/**
 *
 * @author pv
 */
public class StudentInsertController extends BaseAuthController {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DepartmentDBContext departmentDB = new DepartmentDBContext(); 
        ArrayList<Department> departs = departmentDB.getDeparts(); 
        request.setAttribute("departs", departs);
        request.getRequestDispatcher("../view/insert.jsp").forward(request, response);
    } 

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name"); 
        Date dob = Date.valueOf(request.getParameter("dob")); 
        boolean Gender = request.getParameter("gender").equals("male"); 
        Department d = new Department(); 
        d.setDid(Integer.parseInt(request.getParameter("did")));
        Student s = new Student();
        s.setId(id);
        s.setName(name);
        s.setDob(dob);
        s.setGender(Gender);
        s.setDepartment(d);
       StudentDBContext studentDB = new StudentDBContext(); 
        studentDB.Insert(s);
        response.sendRedirect("search");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    protected void proccessPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
