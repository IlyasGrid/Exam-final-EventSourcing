package com.ilyasgrid.inventoryservice.command.controllers;

import com.ilyasgrid.inventoryservice.common.command.CreateProductCommand;
import com.ilyasgrid.inventoryservice.common.dto.CreateProductRequestDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/inventory/commands")
public class ProductCommandController {
    private final CommandGateway commandGateway;

    public ProductCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("/create")
    public CompletableFuture<String> createProduct(@RequestBody CreateProductRequestDTO dto) {
        return commandGateway.send(new CreateProductCommand(
                UUID.randomUUID().toString(),
                dto.name(),
                dto.price(),
                dto.quantity(),
                dto.categoryId()
        ));
    }
}
