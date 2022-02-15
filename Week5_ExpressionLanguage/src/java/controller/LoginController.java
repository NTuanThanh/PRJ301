/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.AccountDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author pv
 */
public class LoginController extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("view/login.jsp").forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("user"); 
        String pass = request.getParameter("pass"); 
        AccountDBContext accountDB = new AccountDBContext();
        Account account = accountDB.getAccount(user, pass);
        // If login successfull, then create 2 Cookie ( User and Pass)
        if(account != null){
            // Create cookie ( to remember user and password to login again dont need input)
            Cookie c_user = new Cookie("user", account.getUser()); 
            Cookie c_pass = new Cookie("pass", account.getPassword()); 
            c_user.setMaxAge(100);
            c_pass.setMaxAge(100);
            response.addCookie(c_user);
            response.addCookie(c_pass);
            // Create a Session to save Account value Account ( Phân quyền cho account )
            HttpSession session = request.getSession();
            session.setAttribute("account", account);
            response.sendRedirect("home");            
        }else{
            response.getWriter().print("Username or password incorrect");
        }
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
