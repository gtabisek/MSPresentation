package com.substring.auth.services;

import com.substring.auth.dtos.UserDto;
import com.substring.auth.entities.User;

public interface UserService {
	
	UserDto createUser(UserDto userDto);
	
	UserDto getUserByEmail(String email);
	
	UserDto updateUser(UserDto userDto, String userId);
	
	void deleteUser(String userId);
	
	UserDto getUserById(String userId);
	
	Iterable<UserDto> getAllUsers();
	
}
