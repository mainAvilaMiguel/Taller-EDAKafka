package co.edu.uptc.edamicrokafka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.uptc.edamicrokafka.model.order.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId(String customerId);
}
