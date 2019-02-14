package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import org.mindrot.jbcrypt.BCrypt;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        // Let's get the input secretly.
        String email = req.getParameter("email");
        String hashedEmail = BCrypt.hashpw(email, BCrypt.gensalt());
        String password = req.getParameter("password");
        String hashedPw = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.printf("Guys, email is %s, hashed %s.%nPw is %s, hashed %s.%n", email, hashedEmail, password, hashedPw);

        // Lets see if we have them in our database.
        boolean validLogin = false;

        // Check that an unencrypted password matches one that has previously been hashed
        if (BCrypt.checkpw(password, hashedPw)) {
            System.out.printf("Match!");
        }
        else {
            System.out.printf("No match.");
        }

    }
}
