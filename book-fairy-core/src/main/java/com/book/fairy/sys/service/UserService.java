package com.book.fairy.sys.service;


import com.book.fairy.sys.dto.UserDto;
import com.book.fairy.sys.model.User;

public interface UserService {

	User saveUser(UserDto userDto);
	
	User updateUser(UserDto userDto);

	String passwordEncoder(String credentials, String salt);

	User getUser(String username);

	void changePassword(String username, String oldPassword, String newPassword);

}
