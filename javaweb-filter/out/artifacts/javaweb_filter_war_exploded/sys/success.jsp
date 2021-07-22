<%--
  Created by IntelliJ IDEA.
  User: ethan
  Date: 10/7/2021
  Time: 下午 3:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Object userSession = request.getSession().getAttribute("USER_SESSION");
    /*if (userSession == null) {
        //response.sendRedirect("/servlet/login");
        response.sendRedirect("/Login.jsp");
    }*/

%>


<h1>主页</h1>

<P>
    <a href="/servlet/logout">logout</a>
</P>

</body>
</html>
