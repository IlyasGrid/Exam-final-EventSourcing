package com.ilyasgrid.orderservice.common.command;

import com.ilyasgrid.orderservice.common.dto.OrderLineRequestDTO;
import org.axonframework.modelling.command.AggregateIdentifier;

import java.util.List;

public record CreateOrderCommand(
        @AggregateIdentifier
        String orderId,
        String customerId,
        String address,
        List<OrderLineRequestDTO> orderItems
) {
}
