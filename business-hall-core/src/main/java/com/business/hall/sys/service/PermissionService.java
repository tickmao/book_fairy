package com.business.hall.sys.service;

import com.business.hall.sys.model.Permission;

public interface PermissionService {

	void save(Permission permission);

	void update(Permission permission);

	void delete(Long id);
}
