<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2021/12/11
  Time: 5:43 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<String> words = new ArrayList<String>();
    words.add("today");
    words.add("is");
    words.add("a");
    words.add("great");
    words.add("day");
%>

<html>
<head>
    <title>for循环</title>
</head>

<script type="text/javascript" src="../../js/all.js"></script>

<body>
<div id="sideBorder">

        <%for (String word : words) {%>
        <tr>
            <td><%= word %></td>
        </tr>
        <% } %>

</div>

</body>
</html>
