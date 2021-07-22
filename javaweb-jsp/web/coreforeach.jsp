<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: ethan
  Date: 7/6/2021
  Time: 4:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    ArrayList<String> person = new ArrayList<>();
    person.add(0,"a");
    person.add(1,"b");
    person.add(2,"c");
    person.add(3,"d");
    request.setAttribute("list",person);
%>
<%--var 每次遍历的遍历
items 要遍历的对象
--%>
<c:forEach var="i" items="${list}">
    <c:out value="${i}"></c:out>
    <br>
</c:forEach>
<br>
<c:forEach begin="1" end="3" step="2" var="i" items="${list}">
    <c:out value="${i}"></c:out>
    <br>
</c:forEach>

</body>
</html>
