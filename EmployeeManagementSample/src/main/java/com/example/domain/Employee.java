package com.example.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
@Table(name="employees")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer employee_id;
	
	private String employee_name;
	private Integer gender;
	private String employee_phone;
	private String employee_email;
	private String password;
	
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date date_of_entry;
	
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date start_date;
	
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date end_date;
	
	private Integer sales;
	private String district_in_charge;
	private Integer customers;
	private Date updated_at;
	private Integer department_id;
	
	@OneToOne
	@JoinColumn(name="department_id", insertable=false, updatable=false)
	private Department dept;
}
