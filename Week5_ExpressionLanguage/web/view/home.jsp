<%-- 
    Document   : home
    Created on : Feb 14, 2022, 11:30:47 PM
    Author     : pv
--%>

<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/homeCss.css">
    </head>
    <body>
        <!--This is header-->
        <div class = "header">
            <div class = "bander">
                <h1 class = "name_banner"> Quản Lý Thư Viện </h1>
                <a href="#">Thông tin FPT University</a>
            </div>
            <ul class = "info_header">
                <c:if test="${sessionScope.account != null}">
                    <li>
                        <a class = "welcome">Wecome <span>${sessionScope.account.fullName}</span></a>
                    </li>
                    <li>
                        <a href="#">Thông tin : ${sessionScope.account.user}</a>
                    </li>
                    <li>
                        <a href="logout">Đăng xuất</a>
                    </li>
                </c:if>
                <c:if test ="${sessionScope.account == null}">
                    <li>
                        <a href="login">Đăng nhập</a> 
                    </li>
                </>
                </c:if>
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
