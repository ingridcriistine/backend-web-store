package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface Cart extends JpaRepository<Cart, Long> {
    List<Cart> findByUser(User user);
    List<Cart> findByCartProduct(String product);
}
