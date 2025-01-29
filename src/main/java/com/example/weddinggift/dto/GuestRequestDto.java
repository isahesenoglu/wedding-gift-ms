package com.example.weddinggift.dto;

import com.example.weddinggift.enums.Currency;
import com.example.weddinggift.enums.PaymentType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuestRequestDto {

    private String name;
    private String surname;
    private String nickName;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    private String additionalText;
    private Long userId;
}
