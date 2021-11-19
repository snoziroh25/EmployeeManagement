package com.manage.QLNV.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.manage.QLNV.model.Employee;
import com.manage.QLNV.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	public EmployeeRepository employeeRepository;

	@Autowired
	public MongoTemplate mongoTemplate;

	public Collection<Employee> searchName(String textName) {
		return mongoTemplate.find(
				Query.query(new Criteria().orOperator(Criteria.where("fullName").regex(textName, "i"))),
				Employee.class);
	}

	public Collection<Employee> searchPhone(String textPhone) {
		return mongoTemplate.find(Query.query(new Criteria().orOperator(Criteria.where("phone").regex(textPhone, "i"))),
				Employee.class);
	}

	public Collection<Employee> searchEmail(String textEmail) {
		return mongoTemplate.find(Query.query(new Criteria().orOperator(Criteria.where("email").regex(textEmail, "i"))),
				Employee.class);
	}

	public <S extends Employee> S save(S entity) {
		return employeeRepository.save(entity);
	}

	public <S extends Employee> Iterable<S> saveAll(Iterable<S> entities) {
		return employeeRepository.saveAll(entities);
	}

	public Optional<Employee> findById(String id) {
		return employeeRepository.findById(id);
	}

	public boolean existsById(String id) {
		return employeeRepository.existsById(id);
	}

	public List<Employee> findAll() {
		List<Employee> listEmployee = employeeRepository.findAll();
		return listEmployee;
	}

	public Iterable<Employee> findAllById(Iterable<String> ids) {
		return employeeRepository.findAllById(ids);
	}

	public long count() {
		return employeeRepository.count();
	}

	public void deleteById(String id) {
		employeeRepository.deleteById(id);
	}

	public void delete(Employee entity) {
		employeeRepository.delete(entity);
	}

	public void deleteAllById(Iterable<? extends String> ids) {
		employeeRepository.deleteAllById(ids);
	}

	public void deleteAll(Iterable<? extends Employee> entities) {
		employeeRepository.deleteAll(entities);
	}

	public void deleteAll() {
		employeeRepository.deleteAll();
	}

}
