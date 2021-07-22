<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--引入jsp核心标签库--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h4>if test</h4>
<hr>
<form action="coreif.jsp" method="post">
<%--    EL表达式获取表单中的数据${param.username}--%>
    <input type="text" name="username" value="${param.username}">
    <input type="submit" value="register">
</form>

<%--判断用户名--%>
<%
//    if (request.getParameter("username").equals("admin")) {
//        out.print("hello");
//    }
%>
<c:if test="${param.username=='admin'}" var="isAdmin" scope="request">
    <c:out value="admian hello"></c:out>
</c:if>


</body>
</html>
