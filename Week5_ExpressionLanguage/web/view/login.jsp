<%-- 
    Document   : login
    Created on : Feb 14, 2022, 9:52:25 PM
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
        <form action = "login" method="POST" >
            Tài khoản :  <input type ="text" name ="user"
                        <c:if test = "${cookie != null}">
                            <c:forEach items="${cookie}" var="c">
                                <c:if test = "${c.key == 'user'}">
                                    value ="${c.value.value}"
                                </c:if>
                            </c:forEach>
                        </c:if> />
                                    </br>                              
           Mật khẩu  :  <input type="password" name = "pass" 
                       <c:if test = "${cookie != null}">
                           <c:forEach items="${cookie}" var="c">
                               <c:if test = "${c.key == 'pass'}">
                                   value ="${c.value.value}"
                               </c:if>
                       </c:forEach>
                     </c:if> /><br/>
           <input type = "submit" value = "Đăng nhập">
        </form>
    </body>
</html>
