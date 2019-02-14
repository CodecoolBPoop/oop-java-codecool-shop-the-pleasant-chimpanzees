package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.implementation.memory.CartDaoMem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/payment"})
public class PaymentController extends HttpServlet {
    private float totalPrice;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        CartDao cartDataStore = CartDaoMem.getInstance();

        totalPrice = 0;
        for (int i = 0; i < cartDataStore.getAll().size(); i++)
            this.totalPrice += cartDataStore.getAll().get(i).getPriceQuantity();

        context.setVariable("totalPrice", totalPrice);
        context.setVariable("cart", cartDataStore.getAll());

        engine.process("/product/payment.html", context, resp.getWriter());

    }

}

