package com.ilyasgrid.orderservice.query.entity;

import com.ilyasgrid.orderservice.common.dto.OrderLineRequestDTO;
import com.ilyasgrid.orderservice.common.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor @Builder
public class Order {
    @Id
    private String id;
    private String customerId;
    private String address;
    @Enumerated(EnumType.ORDINAL.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
}