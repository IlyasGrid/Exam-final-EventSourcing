package com.ilyasgrid.inventoryservice.common.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record CreateProductCommand(@TargetAggregateIdentifier
                                   String id,
                                   String name,
                                   double price,
                                   int quantity,
                                   String categoryId) {

}
