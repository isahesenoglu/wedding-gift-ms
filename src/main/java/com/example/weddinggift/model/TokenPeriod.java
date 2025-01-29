package com.example.weddinggift.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.extern.java.Log;

import java.time.LocalDateTime;

@Entity
@Data
public class TokenPeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private int period;
    private LocalDateTime insertDate;
}
