package com.business.hall.sys.service;

import com.business.hall.sys.dto.RoleDto;

public interface RoleService {

	void saveRole(RoleDto roleDto);

	void deleteRole(Long id);
}
