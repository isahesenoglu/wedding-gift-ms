package com.example.weddinggift.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String surname;
    private String nickName;
    private double amount;
    private String currency;
    private String paymentType;
    private String additionalText;
    private int status;
    private int dataStatus;
    private LocalDateTime insertDate;
    private LocalDateTime updateDate;
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

}
