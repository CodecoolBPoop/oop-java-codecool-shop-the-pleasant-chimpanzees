package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartDaoMem implements CartDao {

    private List<Product> data = new ArrayList<>();
    private static CartDaoMem instance = null;

    private CartDaoMem() {
    }

    public static CartDaoMem getInstance() {
        if (instance == null)
                instance = new CartDaoMem();
        return instance;
    }

    @Override
    public void addToCart(Product product) {
        product.setId(data.size()+1);
        data.add(product);
    }

    @Override
    public void removeFromCart(int id) {
        data.remove(find(id));
    }

    @Override
    public void emptyCart() {
        data.clear();
    }

    @Override
    public Product find(int id) {
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Product> getAll() {
        return data;
    }
}
