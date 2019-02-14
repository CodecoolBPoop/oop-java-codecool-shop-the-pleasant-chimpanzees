package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.database.CartDaoJdbc;
import com.codecool.shop.dao.implementation.database.ProductDaoJdbc;
import com.codecool.shop.dao.implementation.memory.CartDaoMem;
import com.codecool.shop.dao.implementation.memory.ProductDaoMem;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.User;
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
    private float totalPrice;
    CartDao cartDataStore = CartDaoJdbc.getInstance();
    ProductDao productDao = ProductDaoMem.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String email = req.getSession().getAttribute("userName").toString();

        int cartId = cartDataStore.getCartIdByEmail(email);

        for (Product product: cartDataStore.getAll(cartId)) {
            product.getDefaultPrice();
        }

        this.totalPrice = 0;

        for (int i = 0; i < cartDataStore.getAll(cartId).size(); i++) {
            this.totalPrice += cartDataStore.getAll(cartId).get(i).getAllPrice();
        }

        context.setVariable("totalPrice", this.totalPrice);

        context.setVariable("cart", cartDataStore.getAll(cartId));
        

        engine.process("/product/cart.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {




        String email = req.getSession().getAttribute("userName").toString();

        int cartId = cartDataStore.getCartIdByEmail(email);

        if(req.getParameter("add") != null) {
            cartDataStore.addToCart(cartId, Integer.parseInt(req.getParameter("add")));
        }

        if(req.getParameter("remove") != null) {
            cartDataStore.removeFromCart(cartId,Integer.parseInt(req.getParameter("remove")));
            /*if (cartDataStore.find(Integer.parseInt(removeId)).getBuyQty() > 1) {
                cartDataStore.find(Integer.parseInt(removeId).changeBuyQtyNumber(-1);

            } else {
                cartDataStore.find(removeId.changeBuyQtyNumber(-1));
                cartDataStore.removeFromCart(removeId);
            }*/
        }

        doGet(req,resp);
    }
}
