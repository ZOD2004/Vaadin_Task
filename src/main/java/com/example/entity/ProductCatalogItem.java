package com.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table
@Entity
public class ProductCatalogItem{
        @Id
        Long productId;
        String name;
        String description;
        String category;
        String brand;
        Double price;
        Integer stockQuantity;
        Boolean inStock;
}