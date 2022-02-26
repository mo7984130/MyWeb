package Servlet;

import entity.Advice;
import util.DAOUtil;
import util.SqlUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author mo7984130
 * @Classname AdviceServlet
 * @Description TODO
 * @Date 2021/12/28 11:17 下午
 */
public class AdviceServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");
        String name = request.getParameter("name");
        String advice = request.getParameter("advice");
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        SqlUtil.runSqlFile(new File(request.getSession().getServletContext().getRealPath("sql") + "/advice.sql"));

        Advice ad = new Advice();
        if ("".equals(name)){
            name = "匿名";
        }

        System.out.print(name + " 提出建议:    ");
        Advice.setName(name);
        System.out.print(advice + "    ");
        Advice.setAdvice(advice);
        Advice.setTime(date);
        System.out.println("时间:" + date);

        new DAOUtil<Advice>(ad).add(ad);

        PrintWriter pw = response.getWriter();

        pw.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/base.css\" />");
        pw.format("<div id=\"sideBorder\">%s</div>","感谢你提出的建议");
        pw.write("<a href=\"html/index/index.html\">点击回到主页</a>");

    }
}
