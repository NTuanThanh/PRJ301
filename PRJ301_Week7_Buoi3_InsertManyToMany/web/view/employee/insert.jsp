<%-- 
    Document   : insert.jsp
    Created on : Mar 7, 2022, 12:06:37 PM
    Author     : pv
--%>

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
        <form action="insert" method="POST"> 
            Id: <input type="text" name="id"/> <br/>
            Name: <input type="text" name="name" /> <br/>
            Gender: <input type="radio" name="gender" value="male"/> Male
            <input type="radio" name="gender" value="female"/> Female <br/>
            Dob: <input type="date" name="dob"/> <br/>
            Certificates: <br/>
            <c:forEach items="${requestScope.certs}" var="c">
                <input type="checkbox" name="cid" value="${c.id}"/> ${c.name} <br/>
            </c:forEach>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>