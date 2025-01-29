package com.example.weddinggift.model;

import com.example.weddinggift.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String middleName;
    @Column(unique = true)
    private String email;
    private String password;
    private String mobile;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private double firstPayment;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate weddingDate;
    private int status = 1;
    private int dataStatus = 1;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime insertDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;

}
