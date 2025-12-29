package com.ilyasgrid.inventoryservice.common.dto;

public record CreateProductRequestDTO(
        String name,
        double price,
        int quantity,
        String categoryId
) {
}
