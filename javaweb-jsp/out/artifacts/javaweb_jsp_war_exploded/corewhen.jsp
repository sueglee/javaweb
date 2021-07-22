<%--
  Created by IntelliJ IDEA.
  User: ethan
  Date: 7/6/2021
  Time: 4:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:set var="score" value="8"></c:set>
<c:choose>
    <c:when test="${score>=99}">a</c:when>
    <c:when test="${score>=88}">b</c:when>
    <c:when test="${score>=77}">c</c:when>
</c:choose>

</body>
</html>
