package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.CardResponse;
import com.example.demo.entity.Card;
import com.example.demo.exceptions.CardNotFoundException;
import com.example.demo.repository.CardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    public List<CardResponse> getCardsByAccountId(Long accountId) {

        List<Card> cards = cardRepository.findByAccountId(accountId);

        if (cards.isEmpty()) {
            throw new CardNotFoundException(
                    "No cards found for account: " + accountId);
        }

        return cards.stream()
                .map(this::mapToResponse)
                .toList();
    }

    private CardResponse mapToResponse(Card card) {
        return CardResponse.builder()
                .maskedCardNumber(mask(card.getCardNumber()))
                .cardType(card.getCardType())
                .expiryDate(card.getExpiryDate())
                .build();
    }

    private String mask(String number) {
        return number.substring(0, 4)
                + " **** **** "
                + number.substring(number.length() - 4);
    }
}