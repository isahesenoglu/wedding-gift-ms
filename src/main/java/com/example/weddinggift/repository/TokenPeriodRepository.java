package com.example.weddinggift.repository;

import com.example.weddinggift.model.TokenPeriod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenPeriodRepository extends JpaRepository<TokenPeriod,Long> {
    TokenPeriod findTopByOrderByInsertDateDesc();  // Ən son əlavə edilmiş periodu gətiririk

}
