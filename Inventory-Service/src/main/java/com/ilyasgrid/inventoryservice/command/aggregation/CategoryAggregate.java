package com.ilyasgrid.inventoryservice.command.aggregation;

import com.ilyasgrid.inventoryservice.common.command.CreateCategoryCommand;
import com.ilyasgrid.inventoryservice.common.event.CategoryCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;

@Aggregate
@Slf4j
public class CategoryAggregate {
    @AggregateIdentifier
    private String categoryId;
    private String name;

    public CategoryAggregate() {
    }

    @CommandHandler
    public CategoryAggregate(CreateCategoryCommand command) {
        AggregateLifecycle.apply(new CategoryCreatedEvent(
                command.id(),
                command.name()
        ));
    }

    @EventSourcingHandler
    public void on(CategoryCreatedEvent event) {
        this.categoryId = event.id();
        this.name = event.name();
    }
}