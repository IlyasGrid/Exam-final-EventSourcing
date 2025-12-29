package com.ilyasgrid.orderservice.query.repository;

import com.ilyasgrid.orderservice.query.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
