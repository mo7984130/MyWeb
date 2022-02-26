<%@ page import="util.DAOUtil" %>
<%@ page import="entity.MD5" %>
<%@ page import="java.util.List" %><%--
  Author: mo7984130
  Date: 2022/1/30
  Time: 6:14 下午
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>下载文件</title>
</head>

<script type="text/javascript" src="${pageContext.request.contextPath}/css/login.css"></script>

<body>

<%
    DAOUtil<MD5> daoUtil = new DAOUtil<>(new MD5());
    List<MD5> md5s = daoUtil.list();
%>

<div id="sideBorder">

    <ul>

        <%
            String toFormat = "<li><a href=\"%s\" download=\"%s\">%s</a><br/></li>";
            for (MD5 md5 : md5s) {%>
                <li><a class="text-lg" href="<%=md5.getWebPath()%>" download="<%=md5.getFileName()%>"><%=md5.getFileName()%></a>
            <%}
        %>

    </ul>

</div>

</body>
</html>
