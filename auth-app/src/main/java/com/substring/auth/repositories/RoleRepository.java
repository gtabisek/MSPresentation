package com.substring.auth.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.substring.auth.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,UUID>{

	Optional<Role> findByName(String name);
	
}
