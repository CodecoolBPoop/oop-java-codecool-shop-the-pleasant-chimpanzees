package com.codecool.shop.controller;


import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.database.CartDaoJdbc;
import com.codecool.shop.dao.implementation.database.UserDaoJdbc;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.User;
import com.sun.net.httpserver.HttpServer;
import org.mindrot.jbcrypt.BCrypt;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        engine.process("/product/register.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserDao userDataStore = UserDaoJdbc.getInstance();
        CartDao cartDataStore = CartDaoJdbc.getInstance();

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String hashedPw = BCrypt.hashpw(password, BCrypt.gensalt());

        User user = new User(email, password);

        userDataStore.add(user);
        cartDataStore.add(userDataStore.findByEmail(email).getId());

        resp.sendRedirect("");
    }
}
