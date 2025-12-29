package com.ilyasgrid.orderservice.query.service;

import com.ilyasgrid.orderservice.common.event.OrderCreatedEvent;
import com.ilyasgrid.orderservice.common.query.GetAllOrdersQuery;
import com.ilyasgrid.orderservice.query.entity.Order;
import com.ilyasgrid.orderservice.query.entity.OrderItem;
import com.ilyasgrid.orderservice.query.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderProjectionHandler {

    private final OrderRepository orderRepository;

    @EventHandler
    public void on(OrderCreatedEvent event) {
        // 1. Créer l'objet Order (Parent)
        Order order = Order.builder()
                .id(event.orderId())
                .address(event.address())
                .customerId(event.customerId())
                .status(event.status())
                .build();

        // 2. Transformer les DTOs de l'événement en Entités OrderItem (Enfants)
        List<OrderItem> orderItems = event.orderItems().stream()
                .map(dto -> OrderItem.builder()
                        .productId(dto.productId())
                        .quantity(dto.quantity())
                        .price(dto.price())
                        .order(order)
                        .build())
                .collect(Collectors.toList());

        // 3. Lier les items à la commande et sauvegarder (CascadeType.ALL fera le reste)
        order.setOrderItems(orderItems);
        orderRepository.save(order);
    }

    @QueryHandler
    public List<Order> handle(GetAllOrdersQuery query) {
        return orderRepository.findAll();
    }
}