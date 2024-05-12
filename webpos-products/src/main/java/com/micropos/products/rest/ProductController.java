package com.micropos.products.rest;

import com.micropos.products.dto.ProductDTO;
import com.micropos.products.model.Category;
import com.micropos.products.model.Product;
import com.micropos.products.model.ProductUpdateRequest;
import com.micropos.products.model.Settings;
import com.micropos.products.biz.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {
    @Autowired
    private ModelMapper modelMapper;

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/settings")
    public ResponseEntity<List<Settings>> listSettings() {
        List<Settings> settings = new ArrayList<>();
        settings.add(new Settings(1,
                new Settings.Setting("Standalone Point of Sale", "Store-Pos", "10086", "10087", "15968774896", "", "$", "10", "", ""),
                "d36d"));
        return new ResponseEntity<>(settings, HttpStatus.OK);
    }
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> listCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("1711853606", "drink", 1711853606));
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> listProducts() {
        return new ResponseEntity<>(
                productService.products().stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable String productId) {
        System.out.println("in get product by id");
        Product product = productService.getProductById(productId);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(modelMapper.map(product, ProductDTO.class), HttpStatus.OK);
    }

    @PatchMapping("/products/{productId}")
    public ResponseEntity<String> patchProduct(@PathVariable String productId, @RequestBody ProductUpdateRequest request) {
        System.out.println("in patchProduct");
        try {
            System.out.println(productId);
            System.out.println(request.getQuantity());
            productService.updateProduct(productId, request.getQuantity());
            return ResponseEntity.ok("Data updated!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update data: " + e.getMessage());
        }
    }

}
