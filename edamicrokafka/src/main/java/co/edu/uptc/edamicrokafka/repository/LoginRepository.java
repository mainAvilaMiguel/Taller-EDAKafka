package co.edu.uptc.edamicrokafka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.uptc.edamicrokafka.model.login.Login;

public interface LoginRepository extends JpaRepository<Login, Integer> {
    Login findByCustomerId(String customerId);
}
