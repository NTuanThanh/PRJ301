<%-- 
    Document   : student
    Created on : Jan 22, 2022, 5:53:01 PM
    Author     : pv
--%>

<%@page import="model.Student"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Department"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student</title>
        <%
           ArrayList<Department> departs = (ArrayList<Department>) request.getAttribute("departs");  
           ArrayList<Student> students = (ArrayList<Student>) request.getAttribute("students"); 
           int did = (Integer) request.getAttribute("did"); 
        %>
    </head>
    <body>
        <script>
             function submitData(){
                 document.getElementById("search_form").submit(); 
             }
        </script>
        <form action = "search" method = "GET" id = "search_form" onchange = "submitData()">
            Department : <select name = "did">
                <option value = "-1" >--Select a Department--</option> 
                <%for (Department d : departs) { %>
                <option
                    <%= d.getDid() == did ? "selected = \"selected\"" : ""%>
                    value = "<%= d.getDid() %>" ><%= d.getDname() %></option>        
                <%}%>
            </select>
        </form>
        <table border = "1px">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Gender</th>
                <th>Date of birth</th>
                <th>Department</th>
                <th></th>
                <th></th>
            </tr>
            </br>
            <%for (Student s : students){ %>
            <tr>
                <td><%= s.getId() %></td>
                <td><%= s.getName()%></td>
                <td><%= s.isGender() == true ? "Male" : "Female"%></td>
                <td><%= s.getDob()%></td>
                <td><%= s.getDepartment().getDname()%></td>
                <td> <a href="update?id=<%=s.getId()%>">Edit</a></td>
                <td><a href="#">Delete</a></td>
            </tr>   
            <%}%>
        </table>
        <a href = "insert"> Insert student click here ! </a>
    </body>
</html>
