package com.example.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
@Table(name="employees")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer employee_id;
	
	@NotBlank
	private String employee_name;
	
	@NotNull(message="{gender_check}")
	private Integer gender = 1;
	
	@NotBlank
	@Pattern(regexp="^(070|080|090)-[0-9]{4}-[0-9]{4}$",
		groups=ValidGroup1.class, message="{phone_check}")
	private String employee_phone;
	
	@NotBlank
	@Email(groups=ValidGroup1.class)
	private String employee_email;
	
	@NotBlank
	@Length(min=4, max=8, groups=ValidGroup1.class)
	@Pattern(regexp="^[a-zA-Z0-9]+$", groups=ValidGroup1.class,
			message="{password_check}")
	private String password;
	
	@DateTimeFormat(pattern="yyyy/MM/dd")
	@NotNull(message="{date_of_entry_check}")
	private Date date_of_entry;
}
