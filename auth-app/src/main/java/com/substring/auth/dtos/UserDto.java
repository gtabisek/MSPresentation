package com.substring.auth.dtos;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.substring.auth.entities.Provider;
import com.substring.auth.entities.Role;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
	
	private UUID id;
	
	private String email;
	
	private String name;
	private String password;
	private String image;
	private boolean enable=true;
	private Instant createdAt=Instant.now();
	private Instant updatedAt=Instant.now();
	
	private Provider provider=Provider.LOCAL;
	
	private Set<RoleDto> roles=new HashSet<>();

}
