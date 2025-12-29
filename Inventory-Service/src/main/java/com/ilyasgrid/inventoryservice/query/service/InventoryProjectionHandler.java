package com.ilyasgrid.inventoryservice.query.service;

import com.ilyasgrid.inventoryservice.common.event.ProductCreatedEvent;
import com.ilyasgrid.inventoryservice.common.query.GetAllProductsQuery;
import com.ilyasgrid.inventoryservice.query.entity.Category;
import com.ilyasgrid.inventoryservice.query.entity.Product;
import com.ilyasgrid.inventoryservice.query.repository.CategoryRepository;
import com.ilyasgrid.inventoryservice.query.repository.ProductRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryProjectionHandler {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public InventoryProjectionHandler(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) {
        Category category = categoryRepository.findById(event.categoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = new Product();
        product.setId(event.id());
        product.setName(event.name());
        product.setPrice(event.price());
        product.setQuantity(event.quantity());
        product.setState(event.state());
        product.setCategory(category);

        productRepository.save(product);
    }

    @QueryHandler
    public List<Product> handle(GetAllProductsQuery query) {
        return productRepository.findAll();
    }
}
