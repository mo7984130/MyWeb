<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2021/12/11
  Time: 6:05 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>获取Cookie</title>
</head>

<script type="text/javascript" src="../../js/all.js"></script>

<body>

<div id="sideBorder">

    <%
        Cookie[] cookies  = request.getCookies();
        for (Cookie cookie : cookies) {
            out.print(cookie.getName() + " : " + cookie.getValue() + "<br/>");
        }
    %>

</div>

</body>
</html>
