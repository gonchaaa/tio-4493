package com.example.appeal_service.DTOs.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private Long id;
    private String accountNumber;
    private String iban;
    private String currencyType;
    private LocalDate expiredDate;
    private String status;
    private BigDecimal balance;
    private Long userId;
    private Long branchId;
}

