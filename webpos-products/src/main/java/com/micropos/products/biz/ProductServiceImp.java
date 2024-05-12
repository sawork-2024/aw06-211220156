package com.micropos.products.biz;

import com.micropos.products.db.ProductDB;
import com.micropos.products.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImp implements ProductService {

    private ProductDB productDB;

    @Autowired
    public void setPosDB(ProductDB productDB) {
        this.productDB = productDB;
    }


    @Override
//    @Cacheable(value = "products")
    public List<Product> products() {
        return productDB.getProducts();
    }

    @Override
    public List<Product> products(String keyword) { return productDB.getProductsByKeyword(keyword); }



    @Override
//    @Cacheable(value = "products", key = "#productId")
    public Product getProductById(String productId) {
        return productDB.getProduct(productId);
    }

    @Override
//    @CacheEvict(value = "products", allEntries = true)
    public void updateProduct(String productId, int quantity) {
        productDB.updateProduct(productId, quantity);
    }

}
