package com.ilyasgrid.inventoryservice.query.service;

import com.ilyasgrid.inventoryservice.common.query.GetAllCategoriesQuery;
import com.ilyasgrid.inventoryservice.common.query.GetAllProductsQuery;
import com.ilyasgrid.inventoryservice.query.entity.Category;
import com.ilyasgrid.inventoryservice.query.entity.Product;
import com.ilyasgrid.inventoryservice.query.repository.CategoryRepository;
import com.ilyasgrid.inventoryservice.query.repository.ProductRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryQueryHandler {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public InventoryQueryHandler(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @QueryHandler
    public List<Product> handle(GetAllProductsQuery query) {
        return productRepository.findAll();
    }

    @QueryHandler
    public List<Category> handle(GetAllCategoriesQuery query) {
        return categoryRepository.findAll();
    }
}