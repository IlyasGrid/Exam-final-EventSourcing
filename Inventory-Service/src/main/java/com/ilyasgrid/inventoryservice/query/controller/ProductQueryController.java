package com.ilyasgrid.inventoryservice.query.controller;

import com.ilyasgrid.inventoryservice.common.query.GetAllProductsQuery;
import com.ilyasgrid.inventoryservice.query.entity.Product;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/inventory/queries")
public class ProductQueryController {
    private final QueryGateway queryGateway;

    public ProductQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/all")
    public CompletableFuture<List<Product>> getAll() {
        return queryGateway.query(new GetAllProductsQuery(),
                ResponseTypes.multipleInstancesOf(Product.class));
    }
}
