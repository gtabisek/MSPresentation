package com.substring.auth.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.substring.auth.entities.RefreshToken;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID>{
	Optional<RefreshToken> findByJti(String jti);
}
