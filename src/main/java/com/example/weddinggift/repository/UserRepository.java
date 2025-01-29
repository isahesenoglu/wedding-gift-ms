package com.example.weddinggift.repository;

import com.example.weddinggift.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);

    User findByEmailAndPassword(String email, String password);


}
