<%-- 
    Document   : insert
    Created on : Feb 9, 2022, 1:50:02 PM
    Author     : pv
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.Department"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%
            ArrayList<Department> departs = (ArrayList<Department>) request.getAttribute("departs"); 
        %>
    </head>
    <body>
        <form action = "insert" method = "POST">
            ID : <input type ="text" name = "id"/> <br/>
            Name : <input type = "text" name = "name"/> <br/>
            Dob : <input type = "date" name = "dob" /> <br/>
            Gender : <input type = "radio" name = "gender" value = "male" /> Male
            <input type = "radio" name ="gender" value="female" /> Female <br/>
            Department : <select name = "did">
                <%
                   for (Department d : departs) { %>
                   <option value = "<%= d.getDid() %>" ><%= d.getDname() %></option>        
                <% }
                %>
            </select><br/>
            <input type="submit" value = "Insert" />            
        </form>
         
    </body> 
</html>
