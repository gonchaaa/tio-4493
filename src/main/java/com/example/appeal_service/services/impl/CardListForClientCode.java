package com.example.appeal_service.services.impl;

import com.example.appeal_service.DTOs.feign.AccountDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class CardListForClientCode {

        public List<AccountDTO> getCardsByClientCode(String clientCode) {
            return List.of(
                    new AccountDTO(1L, "1234 5678 9012 3456", "AZ21NABZ00000000123456789012", "AZN",
                            LocalDate.now().plusYears(2), "ACTIVE", new BigDecimal("1500.00"), 1001L, 2001L),
                    new AccountDTO(2L, "9876 5432 1098 7654", "AZ33NABZ00000000987654321098", "USD",
                            LocalDate.now().plusYears(1), "ACTIVE", new BigDecimal("3200.00"), 1001L, 2001L)
            );
        }
}

