package Servlet.login;

import entity.User;
import util.DAOUtil;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author mo7984130
 * @Classname CheckLoginServlet
 * @Description TODO
 * @Date 2022/1/31 2:15 下午
 */
public class CheckLoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String[] fields = new String[]{"userId" , "userName" , "password"};
        String[] values = new String[]{request.getParameter("userId") , request.getParameter("userName") , request.getParameter("password")};
        for (String value : values) {
            if ("null".equals(value)){
                response.getWriter().write("0");
                return;
            }
        }

        DAOUtil<User> daoUtil = new DAOUtil<>(new User());
        if (daoUtil.exists(fields , values)){
            response.getWriter().write("1");
        }else{
            response.getWriter().write("0");
        }
    }
}
