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
public class StudentUpdateController extends BaseAuthController {

   
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String raw_id = request.getParameter("id");
        int id = Integer.parseInt(raw_id); 
        // get information of Student have that id
        StudentDBContext studentDB = new StudentDBContext(); 
        Student Student = studentDB.getStudent(id);
        // get all Department to show update view
        DepartmentDBContext departDB = new DepartmentDBContext();
        ArrayList<Department> departs = departDB.getDeparts(); 
        request.setAttribute("student", Student);
        request.setAttribute("departs", departs);
        request.getRequestDispatcher("../view/update.jsp").forward(request, response);
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
        String raw_sid = request.getParameter("sid"); 
        String raw_name = request.getParameter("sname"); 
        String raw_dob = request.getParameter("dob");
        String raw_gender = request.getParameter("gender");
        String raw_did = request.getParameter("did"); 
        int sid = Integer.parseInt(raw_sid); 
        String sname = raw_name; 
        Date dob = Date.valueOf(raw_dob); 
        boolean gender = raw_gender.equals("male"); 
        int did = Integer.parseInt(raw_did); 
        Department d = new Department(); 
        d.setDid(did);
        Student s = new Student(did, sname, gender, dob, d); 
        StudentDBContext studentDB = new StudentDBContext();
        studentDB.Update(s);
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
