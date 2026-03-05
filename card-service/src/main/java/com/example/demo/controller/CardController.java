package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.CardResponse;
import com.example.demo.service.CardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping("/account/{accountId}")
    public ResponseEntity<ApiResponse> getCards(@PathVariable Long accountId) {

        List<CardResponse> cards =
                cardService.getCardsByAccountId(accountId);

        ApiResponse response = ApiResponse.builder()
                .message("Cards fetched successfully")
                .data(cards)
                .build();

        return ResponseEntity.ok(response);
}
    }