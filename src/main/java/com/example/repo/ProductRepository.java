package com.example.repo;

import com.example.entity.ProductCatalogItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductCatalogItem,Long> {
    Page<ProductCatalogItem> findAll(Pageable pageable);
    Page<ProductCatalogItem> findByNameContainingIgnoreCase(String name,
                                                             Pageable pageable);
}
