package com.book.fairy.sys.service;

import com.book.fairy.sys.model.Permission;

public interface PermissionService {

	void save(Permission permission);

	void update(Permission permission);

	void delete(Long id);
}
