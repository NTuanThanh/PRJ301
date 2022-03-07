<%-- 
    Document   : update
    Created on : Mar 7, 2022, 12:52:55 PM
    Author     : pv
--%>
<%@page import="model.Certificate"%>
<%@page import="model.Employee"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

    </head>
    <body>
        <form action = "certs" method="POST">
            <table border = "1px"> 
                <tr>
                    <td>Employee</td>
                    <c:forEach items = "${requestScope.certs}" var = "c">
                        <td>${c.name}</td>
                    </c:forEach>
                </tr>          
                <c:forEach items = "${requestScope.employees}" var = "e">
                    <tr>
                        <td>${e.name}</td>
                        <c:forEach items = "${requestScope.certs}" var = "c" >
                            <td>
                                <input type ="checkbox" name = "cid" value="${e.id}_${c.id}"
                                   <c:forEach items = "${e.certs}" var = "ce">
                                       <c:if test="${ce.id == c.id}">  
                                           checked="checked"
                                       </c:if> 
                                   </c:forEach>/>
                            </td>                      
                        </c:forEach>
                    </tr>                    
                </c:forEach>
            </table>
            <input type ="submit" value="Update" />
        </form>  
    </body>
</html>
