package com.ilyasgrid.inventoryservice.command.aggregation;

import com.ilyasgrid.inventoryservice.common.enums.ProductState;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import com.ilyasgrid.inventoryservice.common.command.CreateProductCommand;
import com.ilyasgrid.inventoryservice.common.event.ProductCreatedEvent;

@Aggregate
@Slf4j
public class ProductAggregate {
    @AggregateIdentifier
    private String productId;
    private String name;
    private double price;
    private int quantity;
    private ProductState state;
    private String categoryId;

    public ProductAggregate() { } // Required by Axon

    @CommandHandler
    public ProductAggregate(CreateProductCommand command) {
        if (command.price() <= 0) throw new RuntimeException("Price must be positive");

        AggregateLifecycle.apply(new ProductCreatedEvent(
                command.id(),
                command.name(),
                command.price(),
                command.quantity(),
                command.categoryId(),
                ProductState.AVAILABLE
        ));
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent event) {
        this.productId = event.id();
        this.name = event.name();
        this.price = event.price();
        this.quantity = event.quantity();
        this.state = event.state();
        this.categoryId = event.categoryId();
    }
}
