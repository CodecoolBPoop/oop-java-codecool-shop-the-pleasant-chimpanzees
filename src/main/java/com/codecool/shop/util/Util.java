package com.codecool.shop.util;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductDao;

import javax.servlet.http.HttpServletRequest;

public class Util {

    private static Util instance = null;

    public static Util getInstance() {
        if (instance == null)
            instance = new Util();
        return instance;
    }

    public void addToCartRequest(HttpServletRequest req, CartDao cartDataStore, ProductDao productDataStore) {
        if(req.getParameter("add") != null){
            int productId = Integer.parseInt(req.getParameter("add"));
            cartDataStore.addToCart(productDataStore.find(productId));
        }
    }
}
