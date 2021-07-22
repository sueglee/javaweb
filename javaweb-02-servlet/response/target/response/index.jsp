<html>
<body>
<h2>Hello World!</h2>

<%--提交路径要找到项目路径--%>
<form action="${pageContext.request.contextPath}/login" method=""get>
    username:<input type = "text" name = "username"><br>
    password:<input type="password" name="password"><br>
    <input type="submit">
</form>
</body>
</html>
