/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.login;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author pv
 */
public abstract class BaseAuthController extends HttpServlet {
    
    private boolean isAuthenticated(HttpServletRequest request){
        Account account = (Account)request.getSession().getAttribute("account"); 
        return account != null; 
    }
            
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(isAuthenticated(request)){
            // bussiness logic
            processGet(request, response);           
        }else{
           response.getWriter().print("Access Denied!");
        }
    }
    protected abstract void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(isAuthenticated(request)){
            // bussiness logic
            processPost(request, response);
        }else{
           response.getWriter().print("Access Denied!");
        }
    }
    protected abstract void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
