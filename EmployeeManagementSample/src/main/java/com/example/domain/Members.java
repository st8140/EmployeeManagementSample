package com.example.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="members")
@NoArgsConstructor
//@IdClass(MembersPK.class)
public class Members {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	//@Id
	//private Integer employee_id;
   //private Integer department_id;
	
	private Integer sales;
	private String district_in_charge;
	private Integer customers;
	
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date updated_at;
	
	private boolean retirement;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="employee_id", insertable=true, updatable=true)
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name="department_id", insertable=true, updatable=false)
	private Department department;
}
