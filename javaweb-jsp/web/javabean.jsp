<%@ page import="com.lee.pojo.People" %><%--
  Created by IntelliJ IDEA.
  User: ethan
  Date: 7/6/2021
  Time: 5:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    People people = new People();
//    等价于useBean
//    people.setAddress("a");
//    people.setId(1);
//    people.setAge(1);
//    people.setName("lee");
%>
<jsp:useBean id="people" class="com.lee.pojo.People" scope="page"></jsp:useBean>
<jsp:setProperty name="people" property="address" value="a"></jsp:setProperty>
<jsp:setProperty name="people" property="id" value="1"></jsp:setProperty>
<jsp:setProperty name="people" property="age" value="1"></jsp:setProperty>
<jsp:setProperty name="people" property="name" value="lee"></jsp:setProperty>

<%=people.getAddress()%>

<br>
name is :<jsp:getProperty name="people" property="name"/>
id is :<jsp:getProperty name="people" property="id"/>
age is :<jsp:getProperty name="people" property="age"/>
address is :<jsp:getProperty name="people" property="address"/>

</body>
</html>
