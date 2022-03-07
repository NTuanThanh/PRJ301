<%-- 
    Document   : login
    Created on : Feb 28, 2022, 11:18:59 AM
    Author     : pv
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>       
    </head>
    <body>
        <form action = "login" method = "POST" > 
            Username :<input type="text" name = "user" /><br/>
            Password : <input type="password" name = "pass" /><br/>
            <input type="submit" value ="Login" />           
        </form>
    </body>
</html>
