package com.lucashthiele.backendtestifood.services;

import com.lucashthiele.backendtestifood.domain.category.Category;
import com.lucashthiele.backendtestifood.domain.product.Product;
import com.lucashthiele.backendtestifood.domain.product.ProductDTO;
import com.lucashthiele.backendtestifood.exceptions.CategoryNotFoundException;
import com.lucashthiele.backendtestifood.exceptions.ProductNotFoundException;
import com.lucashthiele.backendtestifood.repositories.CategoryRepository;
import com.lucashthiele.backendtestifood.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SqsMessageSenderService sqsMessageSender;

    public Product create(ProductDTO productData){
        var product = new Product(productData);
        product.setCategory(getCategoryOrThrow(productData.categoryId()));
        productRepository.save(product);
        sendUpdateCatalogMessage(product.getOwnerId());
        return product;
    }

    private Category getCategoryOrThrow(String categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product update(String id, ProductDTO productData){
        var product = getProductOrThrow(id);

        product.setTitle(productData.title());
        product.setDescription(productData.description());
        product.setPrice(productData.price());
        product.setCategory(getCategoryOrThrow(productData.categoryId()));
        product.setOwnerId(productData.ownerId());

        productRepository.save(product);
        sendUpdateCatalogMessage(product.getOwnerId());
        return product;
    }

    private Product getProductOrThrow(String id) {
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    public void delete(String id){
        productRepository.deleteById(id);
        sendUpdateCatalogMessage(getProductOrThrow(id).getOwnerId());
    }

    private void sendUpdateCatalogMessage(Integer ownerId){
        sqsMessageSender.sendMessage(ownerId.toString());
    }
}
