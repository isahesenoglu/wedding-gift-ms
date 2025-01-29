package com.example.weddinggift.repository;

import com.example.weddinggift.model.Credential;
import com.example.weddinggift.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface CredentialRepository extends JpaRepository<Credential,Long> {
    @Query("SELECT c FROM Credential c WHERE c.user.id = :userId AND c.dataStatus = 1")
    Optional<Credential> findActiveCredentialsByUserId(Long userId);

    List<Credential> findByUserAndStatusAndDataStatus(User user, int status, int dataStatus);
    Optional<Credential> findByToken(String token);

    @Query("SELECT c FROM Credential c WHERE c.expirationDate IS NOT NULL AND c.expirationDate < :now AND c.dataStatus = 1 and c.status = 1")
    List<Credential> findByExpirationDateBefore(LocalDateTime now);


}
