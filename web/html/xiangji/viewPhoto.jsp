<%@ page import="util.DAOUtil" %>
<%@ page import="entity.Xiangji" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="static java.lang.String.format" %>
<%--
  Author: mo7984130
  Date: 2022/2/11
  Time: 11:36 下午
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>照片查看</title>
</head>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/all.js"></script>

<body>

<div id="sideBorder">
    <div class="container">

        <%
            String formatString = "<div class=\"col-xs-3 \"><a href=\"../../%s\"><img class=\"img-thumbnail\" src=\"../../%s\"></a></div>\n";
            int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
            Xiangji xiangji = new Xiangji();
            DAOUtil<Xiangji> daoUtil = new DAOUtil<>(xiangji);
            ArrayList<Xiangji> list = daoUtil.list((pageIndex -1) * 24 , 24);
            int databaseCount = daoUtil.getTotal()/24 + 1;
            int count = 0;
            for (int i = 0 ; i < list.size() ; i++) {

                Xiangji j = list.get(i);

                if (count == 0) {
                    out.println("<div class=\"row\">");
                }

                out.println(format(formatString, j.getWebPath(), j.getWebPath()));
                count++;

                if (count == 4 || i == list.size()-1) {
                    out.println("</div>");
                    count = 0;
                }

            }
        %>

    </div>

    <nav>
        <ul class="pager">
            <li><a href="
                <%
                if(pageIndex == 0){
                    out.print("viewPhoto.jsp?pageIndex=" + 0);
                }else {
                    out.print("viewPhoto.jsp?pageIndex=" + (pageIndex-1));
                }
                %>
                ">上一页</a></li>
            <li><a href="
            <%
            if(pageIndex == databaseCount){
                    out.print("viewPhoto.jsp?pageIndex=" + pageIndex);
                }else {
                    out.print("viewPhoto.jsp?pageIndex=" + (pageIndex+1));
                }
            %>
            ">下一页</a></li>
        </ul>
    </nav>

    <!-- <nav>
        <ul class="pagination pagination-lg">
            <li>
                <a href="?pageIndex=

                " aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <li><a href="#">1</a></li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <li><a href="#">4</a></li>
            <li><a href="#">5</a></li>
            <li>
                <a href="#" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>
    -->

</div>

</body>
</html>
