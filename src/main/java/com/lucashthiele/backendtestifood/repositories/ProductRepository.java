package com.lucashthiele.backendtestifood.repositories;

import com.lucashthiele.backendtestifood.domain.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
