package com.ilyasgrid.inventoryservice.common.event;

import com.ilyasgrid.inventoryservice.common.enums.ProductState;

public record ProductCreatedEvent(
        String id,
        String name,
        double price,
        int quantity,
        String categoryId,
        ProductState state

) {
}
