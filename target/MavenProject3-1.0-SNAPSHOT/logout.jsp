<%-- 
    Document   : logout
    Created on : 24/09/2019, 04:34:11 PM
    Author     : jose_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Custom styles for this template -->
    </head>
    <body class="text-center">
      
<%
    if(request.getSession().getAttribute("username")!=null){
            session.removeAttribute("username");
            session.invalidate();
            response.sendRedirect("login.jsp");
    }else{
        response.sendRedirect("login.jsp");
    }
%>

   </body>
    
</html>
