package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/my-cart"})
public class CartController extends HttpServlet {

    CartDao cartDataStore = CartDaoMem.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());


        context.setVariable("cart", cartDataStore.getAll());

        engine.process("/product/cart.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getParameter("add") != null) {
            cartDataStore.find(Integer.parseInt(req.getParameter("add"))).changeBuyQtyNumber(1);
        }

        if(req.getParameter("remove") != null) {
            if (cartDataStore.find(Integer.parseInt(req.getParameter("remove"))).getBuyQty() > 1) {
                cartDataStore.find(Integer.parseInt(req.getParameter("remove"))).changeBuyQtyNumber(-1);
            } else {
                cartDataStore.find(Integer.parseInt(req.getParameter("remove"))).changeBuyQtyNumber(-1);
                cartDataStore.removeFromCart(Integer.parseInt(req.getParameter("remove")));
            }
        }


        doGet(req,resp);
    }
}
