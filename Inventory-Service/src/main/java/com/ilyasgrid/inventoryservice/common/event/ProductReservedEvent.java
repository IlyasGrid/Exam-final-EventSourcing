package com.ilyasgrid.inventoryservice.common.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record ProductReservedEvent(@TargetAggregateIdentifier
                                   String productId,
                                   int quantity) {
}
