<%@ page import="java.lang.reflect.Array" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2021/12/11
  Time: 6:03 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>设置Cookie</title>
</head>

<script type="text/javascript" src="../../js/all.js"></script>

<body>

<%
    ArrayList<Cookie> cookies = new ArrayList<>();
    String[][] ss = new String[][]{
            {"account","mo7984130"}
    };
    for (String[] s : ss) {
        Cookie cookie = new Cookie(s[0],s[1]);
        cookie.setMaxAge(60 * 60 * 24);
        response.addCookie(cookie);
    }
%>

<div id="sideBorder">
    <%
        response.sendRedirect("getCookie.jsp");
    %>

</div>

</body>
</html>
