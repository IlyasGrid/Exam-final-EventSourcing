package com.ilyasgrid.orderservice.command.aggregate;

import com.ilyasgrid.orderservice.common.command.CreateOrderCommand;
import com.ilyasgrid.orderservice.common.enums.OrderStatus;
import com.ilyasgrid.orderservice.common.event.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
public class OrderAggregate {
    @AggregateIdentifier
    private String orderId;
    private String customerId;
    private String address;
    private OrderStatus status;

    public OrderAggregate() {
    }

    @CommandHandler
    public OrderAggregate(CreateOrderCommand command) {
        // Business Rule: Order must have at least one item
        if (command.orderItems() == null || command.orderItems().isEmpty()) {
            throw new RuntimeException("An order must have at least one item");
        }

        AggregateLifecycle.apply(new OrderCreatedEvent(
                command.orderId(),
                command.customerId(),
                command.address(),
                command.orderItems(),
                OrderStatus.APPROVED
        ));
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent event) {
        this.orderId = event.orderId();
        this.customerId = event.customerId();
        this.address = event.address();
        this.status = event.status();
    }
}