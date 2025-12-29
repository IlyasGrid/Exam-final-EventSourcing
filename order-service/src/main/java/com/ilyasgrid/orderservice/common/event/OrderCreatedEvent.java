package com.ilyasgrid.orderservice.common.event;

import com.ilyasgrid.orderservice.common.dto.OrderLineRequestDTO;
import com.ilyasgrid.orderservice.common.enums.OrderStatus;

import java.util.List;

public record OrderCreatedEvent(
        String orderId,
        String customerId,
        String address,
        List<OrderLineRequestDTO> orderItems,
        OrderStatus status

) {
}
