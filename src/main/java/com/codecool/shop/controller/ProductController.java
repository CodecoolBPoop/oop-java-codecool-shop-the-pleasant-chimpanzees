package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    private ProductDao productDataStore = ProductDaoMem.getInstance();
    private ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
    private SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String categoryFilter = req.getParameter("category") == null ? "All" : req.getParameter("category");
        String supplierFilter = req.getParameter("supplier") == null ? "All" : req.getParameter("supplier");

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("categories", getCategoriesAtSelectedFirst(categoryFilter));
        context.setVariable("suppliers", getSuppliersAtSelectedFirst(supplierFilter));
        context.setVariable("products", filter(categoryFilter, supplierFilter, productDataStore));
        engine.process("product/index.html", context, resp.getWriter());

    }

    private List<ProductCategory> getCategoriesAtSelectedFirst(String categoryFilter){
        List<ProductCategory> returnList = new LinkedList<>(
                Arrays.asList(new ProductCategory("All","All", "All the products"))
        );

        returnList.addAll(productCategoryDataStore.getAll());

        if(!categoryFilter.equals("All")){
            returnList = returnList.stream().filter(p -> !p.getName().equals(categoryFilter)).collect(Collectors.toList());
            returnList.add(0, productCategoryDataStore.find(categoryFilter));
        }

        return returnList;
    }

    private List<Supplier> getSuppliersAtSelectedFirst(String supplierFilter){
        List<Supplier> returnList = new LinkedList<>(
                Arrays.asList(new Supplier("All", "All the suppliers"))
        );

        returnList.addAll(supplierDataStore.getAll());

        if(!supplierFilter.equals("All")){
            returnList = returnList.stream().filter(p -> !p.getName().equals(supplierFilter)).collect(Collectors.toList());
            returnList.add(0, supplierDataStore.find(supplierFilter));
        }
        return returnList;
    }

    private List<Product> filter(String categoryFilter, String supplierFilter, ProductDao productDataStore){
        List<Product> filteredBySupplier;
        List<Product> filteredByCategory;

        if(supplierFilter.equals("All")){
            filteredBySupplier = productDataStore.getAll();
        }else{
            filteredBySupplier = productDataStore
                    .getBy(supplierDataStore.find(supplierFilter));
        }

        if(categoryFilter.equals("All")){
            filteredByCategory = productDataStore.getAll();
        }else{
            filteredByCategory = productDataStore
                    .getBy(productCategoryDataStore.find(categoryFilter));
        }

        return getIntersection(filteredByCategory, filteredBySupplier);
    }

    private List<Product> getIntersection(List<Product> list1, List<Product> list2){
        List<Product> seekedProducts = new ArrayList<>();

        for (Product product : list1) {
            if(list2.contains(product)){
                seekedProducts.add(product);
            }
        }
        return seekedProducts;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        CartDao cartDataStore = CartDaoMem.getInstance();
        int idOfProduct = Integer.parseInt(req.getParameter("add"));

        if (cartDataStore.find(idOfProduct) == null) {
            cartDataStore.addToCart(productDataStore.find(idOfProduct));
        } else {
            cartDataStore.find(idOfProduct).changeBuyQtyNumber(1);
        }

        doGet(req,resp);
    }
}
