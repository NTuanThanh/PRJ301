<%-- 
    Document   : login
    Created on : Feb 16, 2022, 10:53:17 PM
    Author     : pv
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>       
    </head>
    <body>
        <form action = "login" method = "POST" > 
            Tài khoản :<input type="text" name = "user" /><br/>
            Mật khẩu : <input type="password" name = "pass" /><br/>
            <input type="submit" value ="Đăng nhập" />           
        </form>
    </body>
</html>

