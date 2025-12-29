package com.ilyasgrid.orderservice.command.controller;

import com.ilyasgrid.orderservice.common.command.CreateOrderCommand;
import com.ilyasgrid.orderservice.common.dto.OrderLineRequestDTO;
import com.ilyasgrid.orderservice.common.dto.OrderRequestDTO;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/orders/commands")
@AllArgsConstructor
public class OrderCommandController {

    private final CommandGateway commandGateway;

    @PostMapping("/create")
    public CompletableFuture<String> createOrder(@RequestBody OrderRequestDTO request) {
        return commandGateway.send(new CreateOrderCommand(
                UUID.randomUUID().toString(),
                request.customerId(),
                request.address(),
                request.orderItems()
        ));
    }
}
