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
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Certificate;
import model.Employee;

/**
 *
 * @author pv
 */
public class UpdateController extends BaseAuthController {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EmployeeDBContext employeeDB = new EmployeeDBContext(); 
        CertificateDBContext certDB = new CertificateDBContext();         
        ArrayList<Employee> employees = employeeDB.getEmployees();
        ArrayList<Certificate> certs = certDB.getCerts();        
        request.setAttribute("employees", employees);
        request.setAttribute("certs", certs);
        request.getRequestDispatcher("view/employee/update.jsp").forward(request, response);
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
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EmployeeDBContext employeeDB = new EmployeeDBContext(); 
        // all Employees
        ArrayList<Employee> idEmployees = employeeDB.getAllIdEmployee();
        String temp[] = request.getParameterValues("cid"); 
        if (temp != null) {
            for (Employee e : idEmployees) {
                for (String t : temp) {
                    String[] eid_cid = t.split("[_]");
                    int tempEid = Integer.parseInt(eid_cid[0]);
                    int tempCid = Integer.parseInt(eid_cid[1]);
                    if (e.getId() == tempEid) {
                        Certificate c = new Certificate();
                        c.setId(tempCid);
                        e.getCerts().add(c);
                    }
                }
            }
        }
        // Đã lấy được toàn bộ thông tin của các employee chứa certificate hoặc không chứa
        // Xóa toàn bộ dữ liệu trong bảng Employee_Certificate để update lại 
        employeeDB.DeleteAll();
        // Insert lại từng emloyee dữ liệu đã được lấy
        for (Employee e : idEmployees) {
            employeeDB.Update_Employee_Certificate(e);
        }
        response.getWriter().print("Updated successfully");
        
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
