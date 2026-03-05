package com.substring.auth.services.impl;

import java.util.HashSet;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.substring.auth.config.AppConstants;
import com.substring.auth.dtos.UserDto;
import com.substring.auth.entities.Provider;
import com.substring.auth.entities.Role;
import com.substring.auth.entities.User;
import com.substring.auth.exceptions.ResourceNotFoundException;
import com.substring.auth.helpers.UserHelper;
import com.substring.auth.repositories.RoleRepository;
import com.substring.auth.repositories.UserRepository;
import com.substring.auth.services.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public UserDto createUser(UserDto userDto) {
		if(userDto.getEmail()==null||userDto.getEmail().isBlank())
		{
			throw new IllegalArgumentException("Email is required");
		}
		
		if(userRepository.existsByEmail(userDto.getEmail())) {
			throw new IllegalArgumentException("User with this Email already Exists");
		}
		if(userDto.getPassword()==null||userDto.getPassword().isBlank())
		{
			throw new IllegalArgumentException("Password is required!");
		}
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		
		User user=modelMapper.map(userDto, User.class);
		
		user.setProvider(userDto.getProvider()!=null?userDto.getProvider():Provider.LOCAL);
		//role assign here to user for authorization
		Role role=roleRepository.findByName("ROLE_"+AppConstants.GUEST_ROLE).orElse(null);
		if (user.getRoles() == null) {
		    user.setRoles(new HashSet<>());
		}
		user.getRoles().add(role);
		
		
		User savedUser=userRepository.save(user);
		
		return modelMapper.map(savedUser, UserDto.class);
	}

	@Override
	public UserDto getUserByEmail(String email) {
		
		User user=userRepository
		.findByEmail(email)
		.orElseThrow(()-> new ResourceNotFoundException("User not found with given email id"));
		return modelMapper.map(user, UserDto.class);
	}

	
	@Override
	public UserDto updateUser(UserDto userDto, String userId) {
		
		UUID uid=UserHelper.parseUUID(userId);
		User existingUser=userRepository
				.findById(uid)
				.orElseThrow(()-> new ResourceNotFoundException("User not found with given id"));
		if(userDto.getName()!=null)
			existingUser.setName(userDto.getName());
		if(userDto.getImage()!=null)
			existingUser.setImage(userDto.getImage());
		//update password change logic
		if(userDto.getPassword() != null && !userDto.getPassword().isBlank()) {
		    existingUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
		}
		if(userDto.getProvider()!=null)
			existingUser.setProvider(userDto.getProvider());
		existingUser.setEnable(userDto.isEnable());
		User updatedUser=userRepository.save(existingUser);
		return modelMapper.map(updatedUser, UserDto.class);
	}

	@Override
	public void deleteUser(String userId) {
		
		UUID uid= UserHelper.parseUUID(userId);
		User user=userRepository.findById(uid).orElseThrow(()->new ResourceNotFoundException("User not found with given id"));
		userRepository.delete(user);
		
	}

	@Override
	public UserDto getUserById(String userId) {
		
		UUID uid=UserHelper.parseUUID(userId);
		User user=userRepository.findById(uid).orElseThrow(()-> new ResourceNotFoundException("User not found with given id"));
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	@Transactional
	public Iterable<UserDto> getAllUsers() {
		
		
		return userRepository
				.findAll()
				.stream()
				.map( user -> modelMapper.map(user, UserDto.class))
				.toList();
	}

}
