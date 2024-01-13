package com.lucashthiele.backendtestifood.controllers;

import com.lucashthiele.backendtestifood.domain.product.Product;
import com.lucashthiele.backendtestifood.domain.product.ProductDTO;
import com.lucashthiele.backendtestifood.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    @Transactional
    public ResponseEntity<Product> create(
            @RequestBody ProductDTO productData,
            UriComponentsBuilder uriBuilder
    ){
        var product = productService.create(productData);
        var uri = uriBuilder.path("/product/{id}").buildAndExpand(product.getId()).toUri();

        return ResponseEntity.created(uri).body(product);
    }

    @GetMapping
    public ResponseEntity<List<Product>> read() {
        var products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<Product> update(
            @PathVariable String id,
            @RequestBody ProductDTO productData
    ) {
        var product = productService.update(id, productData);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable String id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
