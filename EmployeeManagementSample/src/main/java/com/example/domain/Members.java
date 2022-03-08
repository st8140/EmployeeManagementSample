package com.example.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
@Table(name="members")
public class Members {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private Integer employee_id;

	private Integer department_id;
	
	private Integer sales;
	private String district_in_charge;
	private Integer customers;
	
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date updated_at;
}
