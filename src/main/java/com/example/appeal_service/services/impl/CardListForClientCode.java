package com.example.appeal_service.services.impl;

import com.example.appeal_service.DTOs.feign.AccountDTO;

import java.util.ArrayList;
import java.util.List;

public class CardListForClientCode {
    public List<Integer> getMockCardsByClientCode(String clientCode) {
        // Saxta kart məlumatları
        List<Integer> cards = new ArrayList<>();

        cards.add(1);
        cards.add(2);

        return cards;
    }

}
