<%-- 
    Document   : update
    Created on : Feb 12, 2022, 3:44:39 PM
    Author     : pv
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.Department"%>
<%@page import="model.Student"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%
          Student s = (Student)request.getAttribute("student"); 
          ArrayList<Department> departs = (ArrayList<Department>) request.getAttribute("departs"); 
        %>
    </head>
    <body>
        <form action = "update" method="POST">
            id : <%=s.getId()%> <input type="hidden" name = "sid" value = "<%=s.getId()%>" /><br/>
            Name : <input type="text" name ="sname" value = "<%=s.getName()%>" /> <br/>
            Gender : <input type = radio name = "gender" value = "male" 
                     <%if(s.isGender()){ %>
                        checked="checked"
                      <% }%> /> Male 
                     <input type = radio name = "gender" value = "female" 
                     <%if(!s.isGender()){ %>
                        checked="checked"
                      <% }%> /> Female <br/> 
            Dob : <input type="Date" name ="dob" value ="<%= s.getDob()%>" /> <br/>
            Department :<select name ="did">
             <%
               for (Department d : departs) { %>
               <option value="<%= s.getId()%>" 
                   <%=s.getDepartment().getDid()==d.getDid()?"selected=\"selected\"":"" %>    
               ><%= d.getDname()%></option>        
             <% }           
             %>        
            </select> <br/>
            <input value = "Update" type="submit" />
        </form>
    </body>
</html>
