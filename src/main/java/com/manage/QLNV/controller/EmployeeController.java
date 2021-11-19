package com.manage.QLNV.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.manage.QLNV.model.Employee;
import com.manage.QLNV.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value = { "/", "/index" })
	public String homePage(Model model) {
		model.addAttribute("employeeList", this.employeeService.findAll());
		return "index";
	}

	@PostMapping("index/add")
	public String addEmployee(@ModelAttribute Employee employee) {
		this.employeeService.save(employee);
		return "redirect:/index";
	}

	@GetMapping("index/delete/{id}")
	public String delete(@PathVariable(value = "id") String id) {
		this.employeeService.deleteById(id);
		return "redirect:/index";
	}

	@PostMapping("index/update")
	public String edit(@RequestParam("id") String id, @RequestParam("fullName") String name,
			@RequestParam("phone") String phone, @RequestParam("email") String email, @RequestParam("dob") String dob) {
		Employee employee = new Employee();
		employee = employeeService.findById(id).get();
		employee.setFullName(name);
		employee.setPhone(phone);
		employee.setEmail(email);
		try {
			employee.setDob(dob);
		} catch (ParseException e) {
		}
		employeeService.save(employee);
		return "redirect:/index";
	}

	@RequestMapping(value = "index/search")
	public String search(@RequestParam String searchName, @RequestParam String searchPhone,
			@RequestParam String searchEmail, @RequestParam String searchDateStart, @RequestParam String searchDateEnd,
			Model model) {
		Collection<Employee> attribute = employeeService.searchName(searchName);
		attribute.removeIf(e -> !e.getEmail().contains(searchEmail));
		attribute.removeIf(e -> !e.getPhone().contains(searchPhone));
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		System.out.print(searchDateStart);
		if (searchDateStart.equals("")) {
			searchDateStart = "01/01/1900";
		}
		if (searchDateEnd.equals("")) {
			searchDateStart = "01/01/3000";
		}
		try {
			Date dateStart = simpleDateFormat.parse(searchDateStart);
			Date dateEnd = simpleDateFormat.parse(searchDateEnd);
			attribute.removeIf(e -> !(!dateStart.after(e.getDobTypeDate()) && !dateEnd.before(e.getDobTypeDate())));
		} catch (Exception e) {
		}
		model.addAttribute("employeeList", attribute);
		return "index";
	}
}
