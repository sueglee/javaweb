<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div style="text-align: center">
    <h1>register</h1>
<%--    表单以post方式提交表单到login请求--%>
    <form action="${pageContext.request.contextPath}/login" method="post">
        username:<input type="text" name="username" required><br>
        password:<input type="password" name="password"><br>
        <input type="checkbox" name="hobby" value="a">a<br>
        <input type="checkbox" name="hobby" value="b">b<br>
        <input type="checkbox" name="hobby" value="c">c<br>

        <input type="submit">
    </form>
</div>

</body>
</html>
