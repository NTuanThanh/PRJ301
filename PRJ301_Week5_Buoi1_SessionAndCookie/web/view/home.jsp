<%-- 
    Document   : home
    Created on : Feb 14, 2022, 11:30:47 PM
    Author     : pv
--%>

<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/homeCss.css">
        <%
           Account account = (Account)session.getAttribute("account"); 
        %>
    </head>
    <body>
        <!--This is header-->
        <div class = "header">
            <div class = "bander">
                <h1 class = "name_banner"> Quản Lý Thư Viện </h1>
                <a href="#">Thông tin FPT University</a>
            </div>
            <ul class = "info_header">
                <%if(account != null){%>
                    <li>
                        <a class = "welcome">Wecome <span><%= account.getFullName() %></span></a>
                    </li>
                    <li>
                        <a href="#">Infomation <%= account.getUser() %></a>
                    </li>
                    <li>
                        <a href="logout">Logout</a>
                    </li>
                <%}%>
                <%if(account == null) { %>
                    <li>
                        <a href="login">Login</a> 
                    </li>
                <% }%>
            </ul>
        </div>
        <!--This is body-->
        <div class = "main_body">  
        </div>
        <!--This is footer -->
        <div class = "footer">         
        </div>
    </body>
</html>
