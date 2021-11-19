package com.manage.QLNV.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.manage.QLNV.model.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
	Role findByRole(String role);
}
