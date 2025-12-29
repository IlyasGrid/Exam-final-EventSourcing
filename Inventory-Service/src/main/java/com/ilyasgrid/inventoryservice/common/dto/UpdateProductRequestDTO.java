package com.ilyasgrid.inventoryservice.common.dto;

public record UpdateProductRequestDTO(
        String productId,
        String name,
        double price,
        int quantity
) {
}
