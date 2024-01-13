package com.lucashthiele.backendtestifood.domain.category;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "category")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(of = "id")
public class Category {
    @Id
    private String id;
    private String title;
    private String description;
    private Integer ownerId;

    public Category(CategoryDTO categoryData) {
        this.title = categoryData.title();
        this.description = categoryData.description();
        this.ownerId = categoryData.ownerId();
    }
}
