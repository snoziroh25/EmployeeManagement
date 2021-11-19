package com.manage.QLNV.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employees")
public class Employee {

	String id;
	String fullName;
	String phone;
	String email;
	Date dob;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDob() {
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(this.dob);
	}

	public Date getDobTypeDate() {
		return this.dob;
	}

	public void setDob(String dob) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		this.dob = simpleDateFormat.parse(dob);
	}

	public Employee() {
		super();
	}

	public Employee(String fullName, String phone, String email, Date dob) {
		super();
		this.fullName = fullName;
		this.phone = phone;
		this.email = email;
		this.dob = dob;
	}

}
