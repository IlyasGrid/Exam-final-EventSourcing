//package com.ilyasgrid.orderservice.query.service;
//
//import com.ilyasgrid.orderservice.common.event.OrderCreatedEvent;
//import com.ilyasgrid.orderservice.query.entity.Order;
//import com.ilyasgrid.orderservice.query.entity.OrderItem;
//import com.ilyasgrid.orderservice.query.repository.OrderRepository;
//import org.axonframework.eventhandling.EventHandler;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class OrderProjectionHandler {
//    private final OrderRepository orderRepository;
//
//    public OrderProjectionHandler(OrderRepository orderRepository) {
//        this.orderRepository = orderRepository;
//    }
//
//    @EventHandler
//    public void on(OrderCreatedEvent event) {
//        Order order = Order.builder()
//                .id(event.orderId())
//                .address(event.address())
//                .status(event.status())
//                .orderItems(event.orderItems().stream().map(dto ->
//                        OrderItem.builder()
//                                .productId(dto.productId())
//                                .quantity(dto.quantity())
//                                .price(dto.price())
//                                .build()
//                ).toList())
//                .build();
//
//        // Convert DTOs to JPA Entities
//        List<OrderItem> items = event.orderItems().stream().map(dto ->
//                OrderItem.builder()
//                        .productId(dto.getProductId())
//                        .quantity(dto.getQuantity())
//                        .price(dto.getPrice())
//                        .order(order)
//                        .build()
//        ).collect(Collectors.toList());
//
//        order.setOrderItems(items);
//        orderRepository.save(order);
//    }
//}
