package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    List<UserModel> findByEmail(String email);
    List<UserModel> findByCpf(String cpf);

    // @Query("SELECT u FROM User u WHERE u.email = :loginValue or u.email = :loginValue")
    // List<User> login(@Param("loginValue") String loginValue);
}