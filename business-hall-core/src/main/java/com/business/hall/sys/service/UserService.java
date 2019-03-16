package com.business.hall.sys.service;


import com.business.hall.sys.dto.UserDto;
import com.business.hall.sys.model.User;

public interface UserService {

	User saveUser(UserDto userDto);
	
	User updateUser(UserDto userDto);

	String passwordEncoder(String credentials, String salt);

	User getUser(String username);

	void changePassword(String username, String oldPassword, String newPassword);

}
