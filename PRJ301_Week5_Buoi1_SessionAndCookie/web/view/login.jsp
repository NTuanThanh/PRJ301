<%-- 
    Document   : login
    Created on : Feb 14, 2022, 9:52:25 PM
    Author     : pv
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%
           Cookie [] cookies = request.getCookies(); 
        %>
    </head>
    <body>
        <form action = "login" method="POST" >
            Tài khoản :  <input type ="text" name ="user"
                                <% if(cookies != null){
                                     for (Cookie c : cookies) {
                                         if(c.getName().equals("user")){ %>
                                            value ="<%= c.getValue() %>"  
                                        <%}        
                                     }
                                } %>  
                                /> <br/>
           Mật khẩu  :  <input type="text" name = "pass" 
                               <% if(cookies != null){
                                     for (Cookie c : cookies) {
                                         if(c.getName().equals("pass")){ %>
                                            value ="<%= c.getValue() %>"  
                                        <%}        
                                     }
                                } %>
                               /> <br/>
           <input type = "submit" value = "Đăng nhập">
        </form>
    </body>
</html>
