package com.manage.QLNV.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.manage.QLNV.model.User;

public interface UserRepository extends MongoRepository<User, String> {
	User findByEmail(String email);
}
