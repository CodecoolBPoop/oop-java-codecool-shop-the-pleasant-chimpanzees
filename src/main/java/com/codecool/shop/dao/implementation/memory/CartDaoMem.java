package com.codecool.shop.dao.implementation.memory;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartDaoMem implements CartDao {

    private List<Product> data = new ArrayList<>();
    private static CartDaoMem instance = null;
    private int cartId;
    private int productId;

    private CartDaoMem() {
    }

    public static CartDaoMem getInstance() {
        if (instance == null)
                instance = new CartDaoMem();
        return instance;
    }

    public void addToCart(Product product) {
        product.changeBuyQtyNumber(1);
        data.add(product);
    }

    @Override
    public void removeFromCart(int id) {
        data.remove(find(id));
    }


    public void emptyCart() {
        data.clear();
    }


    @Override
    public void addToCart(int cartId, int productId) {

    }

    @Override
    public void add(int userId) {
    }

    @Override
    public void removeFromCart(int cartId, int productId) {

    }

    @Override
    public void emptyCart(int cartId) {

    }

    @Override
    public Cart findCart(int cartId) {
        return null;
    }

    @Override
    public Product find(int id) {
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public int getCartIdByEmail(String email) {
        return 0;
    }

    @Override
    public List<Product> getAll() {
        return data;
    }

}
