package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
	 List<Card> findByAccountId(Long accountId);
}
