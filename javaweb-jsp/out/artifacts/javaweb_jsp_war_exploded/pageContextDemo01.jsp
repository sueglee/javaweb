<%--
  Created by IntelliJ IDEA.
  User: ethan
  Date: 7/6/2021
  Time: 3:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    pageContext.setAttribute("name1","lee1");//只在一个页面中有效
    request.setAttribute("name2","lee2");//只在一次请求有效，请求转发也会转发
    session.setAttribute("name3","lee3");//一次会话中有效
    application.setAttribute("name4","lee4");//在服务器中有效，打开服务器到关闭服务器
%>
<%
//通过pageContext取出保存的值
    String name1 = (String) pageContext.findAttribute("name1");
    String name2 = (String) pageContext.findAttribute("name2");
    String name3 = (String) pageContext.findAttribute("name3");
    String name4 = (String) pageContext.findAttribute("name4");

    pageContext.forward("/pageDemo02.jsp");
%>

<h1>value is</h1>
<h3>${name1}</h3>
<h3>${name2}</h3>
<h3>${name3}</h3>
<h3>${name4}</h3>

<h3><%=name2%></h3>

</body>
</html>
