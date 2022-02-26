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
 * @Classname LoginServlet
 * @Description TODO
 * @Date 2022/1/30 7:37 下午
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        DAOUtil<User> daoUtil = new DAOUtil<>(new User());
        if (daoUtil.exists(new String[]{"userName" , "password"} , new String[]{userName , password})){
            response.getWriter().write("1");
            Cookie userIdCookie = new Cookie("userId" , String.valueOf(daoUtil.get(new String[]{"userName", "password"}, new String[]{userName, password}).get(0).getUserId()));
            Cookie userNameCookie = new Cookie("userName" , userName);
            Cookie passwordCookie = new Cookie("password" , password);
            userIdCookie.setMaxAge(60*60*24*30);
            userNameCookie.setMaxAge(60*60*24*30);
            passwordCookie.setMaxAge(60*60*24*30);
            userIdCookie.setPath("/");
            userNameCookie.setPath("/");
            passwordCookie.setPath("/");
            response.addCookie(userIdCookie);
            response.addCookie(userNameCookie);
            response.addCookie(passwordCookie);
        }else{
            response.getWriter().write("0");
        }
    }
}
