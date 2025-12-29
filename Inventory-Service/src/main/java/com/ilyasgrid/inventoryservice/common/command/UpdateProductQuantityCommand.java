package com.ilyasgrid.inventoryservice.common.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record UpdateProductQuantityCommand(@TargetAggregateIdentifier
                                           String productId,
                                           int quantity) {
}
