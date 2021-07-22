<%--
  Created by IntelliJ IDEA.
  User: ethan
  Date: 7/6/2021
  Time: 4:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--
    http://localhost:8080/jsptage.jsp?name=value1&value=v1
--%>
<jsp:forward page="/jsptag2.jsp">
    <jsp:param name="value1" value="v1"/>
    <jsp:param name="value2" value="v2"/>
</jsp:forward>



</body>
</html>
