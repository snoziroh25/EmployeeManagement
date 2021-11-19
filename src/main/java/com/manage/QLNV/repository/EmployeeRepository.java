package com.manage.QLNV.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.manage.QLNV.model.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

}
