package com.micropos.products.db;

import com.micropos.products.model.Product;

import java.util.List;

public interface ProductDB {

    public List<Product> getProducts();


    public Product getProduct(String productId);

    List<Product> getProductsByKeyword(String keyword);

    void updateProduct(String productId, int quantity);
}
