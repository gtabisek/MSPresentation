package com.substring.auth.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.substring.auth.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,UUID>{

	Optional<User> findByEmail(String email);
	boolean existsByEmail(String email);
	
	Optional<User> findById(UUID id);
}
