package com.example.weddinggift.dto;

import com.example.weddinggift.enums.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    @NotNull(message = "Ad boş buraxıla bilməz")
    private String name;
    private String surname;
    private String middleName;
    private String email;
    private String mobile;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private double firstPayment;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate weddingDate;
    private int status = 1;
    private int dataStatus = 1;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime insertDate = LocalDateTime.now();
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate = LocalDateTime.now();


}
