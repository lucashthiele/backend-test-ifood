package com.lucashthiele.backendtestifood.services;

import com.lucashthiele.backendtestifood.domain.product.Product;
import com.lucashthiele.backendtestifood.domain.product.ProductDTO;
import com.lucashthiele.backendtestifood.exceptions.ProductNotFoundException;
import com.lucashthiele.backendtestifood.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product create(ProductDTO productData){
        var product = new Product(
                null,
                productData.title(),
                productData.description(),
                productData.price(),
                productData.category(),
                productData.ownerId()
        );

        productRepository.save(product);
        return product;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product update(String id, ProductDTO productData){
        var product = getProductOrThrow(id);

        product.setTitle(productData.title());
        product.setDescription(productData.description());
        product.setPrice(productData.price());
        product.setCategory(productData.category());
        product.setOwnerId(productData.ownerId());

        productRepository.save(product);
        return product;
    }

    private Product getProductOrThrow(String id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Produto não encontrado"));
    }

    public void delete(String id){
        productRepository.deleteById(id);
    }
}
