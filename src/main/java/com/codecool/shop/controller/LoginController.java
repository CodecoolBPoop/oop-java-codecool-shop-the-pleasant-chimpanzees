package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.database.UserDaoJdbc;
import com.codecool.shop.model.User;
import org.mindrot.jbcrypt.BCrypt;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        engine.process("product/login.html", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());


        // Let's get the input secretly.
        String email = req.getParameter("email");
        String hashedEmail = BCrypt.hashpw(email, BCrypt.gensalt());
        String password = req.getParameter("password");
        String hashedPw = BCrypt.hashpw(password, BCrypt.gensalt());

        // Lets see if we have them in our database.
        boolean loggedIn = false;
        User found = UserDaoJdbc.getInstance().findByEmail(email);
        String emailInDb = found.getEmail();
        String pwInDb = found.getPassword();
        if (email.equals(emailInDb) && password.equals(pwInDb)) {
            loggedIn = true;
        }
        context.setVariable("loggedIn", loggedIn);

        // Check that an unencrypted password matches one that has previously been hashed
        if (BCrypt.checkpw(password, hashedPw)) {
            System.out.printf("Match!");
        }
        else {
            System.out.printf("No match.");
        }

        //if request is not from HttpServletRequest, you should do a typecast before
        HttpSession session = req.getSession(true);
        //save message in session
        session.setAttribute("loggedIn", loggedIn);
        session.setAttribute("userName", found.getEmail());
        resp.sendRedirect("product/index.html");

    }
}
