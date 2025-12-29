package com.ilyasgrid.inventoryservice.query.entity;

import com.ilyasgrid.inventoryservice.common.enums.ProductState;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Builder
@Data
public class Product {
    @Id
    private String id;
    private String name;
    private double price;
    private int quantity;
    private ProductState state;
    @ManyToOne
    private Category category;

}
