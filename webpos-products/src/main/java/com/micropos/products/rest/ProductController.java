package com.micropos.products.rest;


import com.micropos.api.dto.ProductDTO;
import com.micropos.products.model.Category;
import com.micropos.products.model.Product;
import com.micropos.products.model.ProductUpdateRequest;
import com.micropos.products.model.Settings;
import com.micropos.products.biz.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    private ModelMapper modelMapper;
    private ProductService productService;
    private RestTemplate restTemplate;
    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

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
//
//            //创建订单
//            // 设置请求头
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//
//            // 设置请求体
//            HttpEntity<List<ProductDTO>> requestEntity = new HttpEntity<>(products, headers);
//            String ret = restTemplate.postForObject("http://localhost:8083/createOrder", )
            return ResponseEntity.ok("Data updated!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update data: " + e.getMessage());
        }
    }

}
