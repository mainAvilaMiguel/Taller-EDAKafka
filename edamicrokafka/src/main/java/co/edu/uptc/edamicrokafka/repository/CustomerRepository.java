package co.edu.uptc.edamicrokafka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uptc.edamicrokafka.model.customer.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    
}
