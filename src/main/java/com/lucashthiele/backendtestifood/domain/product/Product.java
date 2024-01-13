package com.lucashthiele.backendtestifood.domain.product;

import com.lucashthiele.backendtestifood.domain.category.Category;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Product {
    @Id
    private String id;
    private String title;
    private String description;
    private Double price;
    private Category category;
    private Integer ownerId;

    public Product(ProductDTO productData) {
        this.title = productData.title();
        this.description = productData.description();
        this.price = productData.price();
        this.ownerId = productData.ownerId();
    }
}
