package com.ilyasgrid.orderservice.query.controller;

import com.ilyasgrid.orderservice.common.query.GetAllOrdersQuery;
import com.ilyasgrid.orderservice.common.query.GetOrderByIdQuery;
import com.ilyasgrid.orderservice.query.entity.Order;
import lombok.AllArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/orders/queries")
@AllArgsConstructor
public class OrderQueryController {

    private final QueryGateway queryGateway;

    @GetMapping("/all")
    public CompletableFuture<List<Order>> getAllOrders() {
        return queryGateway.query(
                new GetAllOrdersQuery(),
                ResponseTypes.multipleInstancesOf(Order.class)
        );
    }

    @GetMapping("/byId/{id}")
    public CompletableFuture<Order> getOrderById(@PathVariable String id) {
        return queryGateway.query(
                new GetOrderByIdQuery(id),
                ResponseTypes.instanceOf(Order.class)
        );
    }
}