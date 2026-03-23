package com.example.service;

import com.example.entity.ProductCatalogItem;
import com.example.repo.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }


    public List<ProductCatalogItem> fetch(int offset, int limit, String filter) {
        Pageable pageable = PageRequest.of(offset / limit, limit);

        if (filter == null || filter.isEmpty()) {
            return productRepository.findAll(pageable).getContent();
        } else {
            return productRepository.findByNameContainingIgnoreCase(filter, pageable).getContent();
        }
    }
    public int count(String filter) {
        if (filter == null || filter.isEmpty()) {
            return (int) productRepository.count();
        } else {
            return  productRepository.countByNameContainingIgnoreCase(filter);
        }
    }
}
