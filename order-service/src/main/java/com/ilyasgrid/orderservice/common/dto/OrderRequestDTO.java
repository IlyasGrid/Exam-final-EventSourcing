package com.ilyasgrid.orderservice.common.dto;

import java.util.List;

public record OrderRequestDTO(
        String customerId,
        String address,
        List<OrderLineRequestDTO> orderItems
) {
}
