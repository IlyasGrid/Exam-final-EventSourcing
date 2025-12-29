package com.ilyasgrid.orderservice.common.dto;


public record OrderLineRequestDTO(
        String productId,
        int quantity,
        double price
) {
}
