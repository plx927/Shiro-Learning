package com.panlingxiao.shiro.servlet;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by panlingxiao on 2016/9/18.
 * 处理登入的Servlet
 */
@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet{


    private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);
    public static final String LOGIN_JSP = "/WEB-INF/jsp/login.jsp";
    public static final String MAIN_JSP = "/WEB-INF/jps/main.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(LOGIN_JSP).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);

        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            log.info("用户{}认证成功", subject.getPrincipal());
            resp.sendRedirect(req.getServletContext().getContextPath()+"/user/index.jsp");
        } catch (AuthenticationException e) {
            log.warn("用户认证失败:token is {}",token);
            req.setAttribute("authError","用户名或者密码错误");
            req.getRequestDispatcher(LOGIN_JSP).forward(req, resp);
        }
    }
}
