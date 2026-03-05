package com.example.demo.dto;

import java.time.LocalDate;

import com.example.demo.entity.CardType;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class CardResponse {
	
	private String maskedCardNumber;
    private CardType cardType;
    private LocalDate expiryDate;
   
}
