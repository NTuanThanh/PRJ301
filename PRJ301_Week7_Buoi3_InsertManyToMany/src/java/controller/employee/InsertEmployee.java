/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.employee;

import controller.login.BaseAuthController;
import dal.CertificateDBContext;
import dal.EmployeeDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Certificate;
import model.Employee;

/**
 *
 * @author pv
 */
public class InsertEmployee extends BaseAuthController {

   
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CertificateDBContext db = new CertificateDBContext();
        ArrayList<Certificate> certs = db.getCerts();
        request.setAttribute("certs", certs);
        request.getRequestDispatcher("view/employee/insert.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String raw_id = request.getParameter("id");
        String raw_name = request.getParameter("name");
        String raw_gender = request.getParameter("gender");
        String raw_dob = request.getParameter("dob");
        String[] certs = request.getParameterValues("cid");
        
        //validate data
        int id = Integer.parseInt(raw_id);
        String name = raw_name;
        boolean gender = raw_gender.equals("male");
        Date dob = Date.valueOf(raw_dob);
        
        
        Employee e = new Employee();
        e.setId(id);
        e.setName(name);
        e.setDob(dob);
        e.setGender(gender);
        e.setAccount((Account)request.getSession().getAttribute("account"));
        for (String cert : certs) {
            Certificate c = new Certificate();
            c.setId(Integer.parseInt(cert));
            e.getCerts().add(c);
        }
        EmployeeDBContext db = new EmployeeDBContext();
        db.insert(e);
        response.getWriter().print("done");
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

}
