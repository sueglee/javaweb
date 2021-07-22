<%--
  Created by IntelliJ IDEA.
  User: ethan
  Date: 22/7/2021
  Time: 上午 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
<%--  get 有大小限制
      post无大小限制
--%>
  <form action="${pageContext.request.contextPath}/upload.do" enctype="multipart/form-data" method="post">
    <input type="text" name="username"><br/>
    <p><input type="file" name="file1"></p>
    <p><input type="file" name="file2"></p>

    <p><input type="submit" value="submit"> | <input type="reset" value="reset"></p>
  </form>



  </body>
</html>
