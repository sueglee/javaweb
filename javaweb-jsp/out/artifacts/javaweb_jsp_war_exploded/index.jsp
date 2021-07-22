<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
<%--  jsp表达式
将东西输出到客户端
--%>
<%=new java.util.Date()%>
<%--脚本片段--%>
<%
  int sum = 0;
  for (int i = 0; i < 10; i++) {
    sum += i;
  }
  out.println("<h1>sum="+sum+"</h1>");
%>

  $END$
  </body>
</html>
