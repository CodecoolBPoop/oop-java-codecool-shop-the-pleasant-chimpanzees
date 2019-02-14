package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.database.ProductDaoJdbc;
import com.codecool.shop.dao.implementation.database.SupplierDaoJdbc;
import com.codecool.shop.dao.implementation.memory.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.memory.ProductDaoMem;
import com.codecool.shop.dao.implementation.memory.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        DBUtil.ConnectionData data = new DBUtil.ConnectionData(
                "src/Data/prod_config.txt",
                "src/Data/test_config.txt");
        DBUtil.getInstance().connect(data);

        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();


        //setting up a new product category
        ProductCategory superhero = new ProductCategory("Superhero", "Comic book", "Superhero comics are one of the most common genres of American comic books. The genre rose to prominence in the 1930s and became extremely popular in the 1940s and has remained the dominant form of comic book in North America since the 1960s. Superhero comics feature stories about superheroes and the universes these characters inhabit.");
        productCategoryDataStore.add(superhero);
        ProductCategory humor = new ProductCategory("Humor", "Comic book", "???");
        productCategoryDataStore.add(humor);
        ProductCategory horror = new ProductCategory("Horror", "Comic book", "???");
        productCategoryDataStore.add(horror);
    }
}
