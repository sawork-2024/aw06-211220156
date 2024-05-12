package com.micropos.products.biz;

import com.micropos.products.model.Product;

import java.util.List;

public interface ProductService {


    public List<Product> products();
    public Product getProductById(String productId);

    public List<Product> products(String keyword);
    public void updateProduct(String productId, int quantity);
}
