package com.manage.QLNV.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.manage.QLNV.model.Employee;
import com.manage.QLNV.repository.EmployeeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {

	@Autowired
	private EmployeeService employeeService;

	@MockBean
	private EmployeeRepository employeeRepository;

	@Test
	public void getAllTest() throws ParseException {
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		when(employeeRepository.findAll()).thenReturn(Stream
				.of(new Employee("Hieu", "0123456789", "hieu@gmail.com", simpleDateFormat.parse("29/08/2001")),
						new Employee("Quyen", "0966695315", "quyen@gmail.com", simpleDateFormat.parse("25/11/1998")))
				.collect(Collectors.toList()));
		assertEquals(2, employeeService.findAll().size());
	}

	@Test
	public void findByNameTest() {
		String keyword = "quyen";
		assertEquals(2, employeeService.searchName(keyword).size());
	}

	@Test
	public void createEmployeeTest() throws ParseException {
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Employee employee = new Employee("Hieu", "0123456789", "hieu@gmail.com", simpleDateFormat.parse("29/08/2001"));
		when(employeeRepository.save(employee)).thenReturn(employee);
		assertEquals(employee, employeeService.save(employee));
	}
}
