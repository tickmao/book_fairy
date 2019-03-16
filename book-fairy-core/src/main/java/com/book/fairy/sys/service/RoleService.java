package com.book.fairy.sys.service;

import com.book.fairy.sys.dto.RoleDto;

public interface RoleService {

	void saveRole(RoleDto roleDto);

	void deleteRole(Long id);
}
