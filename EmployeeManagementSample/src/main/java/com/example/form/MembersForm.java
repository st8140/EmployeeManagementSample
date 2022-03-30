package com.example.form;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.domain.Department;
import com.example.domain.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MembersForm {
	
//	@NotBlank
	private List<String> district_in_charge;
	
	private List<Integer> sales;
	private List<Integer> customers;
	
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private List<Date> updated_at;
	
//	@NotBlank
//	@Length(min=1, max=20, groups=ValidGroup1.class)
	private List<String> employee_name;
	
//	@NotNull(message="{gender_check}")
	private List<Integer> gender;
	
//	@NotBlank
//	@Pattern(regexp="^(070|080|090)-[0-9]{4}-[0-9]{4}$",
//		groups=ValidGroup1.class, message="{phone_check}")
	private List<String> employee_phone;
	
//	@NotBlank
//	@Email(groups=ValidGroup1.class)
	private List<String> employee_email;
	
//	@DateTimeFormat(pattern="yyyy/MM/dd")
//	@NotNull(message="{date_of_entry_check}")
	private List<Date> date_of_entry;
	
	
	
	
//	@NotBlank
//	private String district_in_charge;
//	
//	private Integer sales;
//	private Integer customers;
//	
//	@DateTimeFormat(pattern="yyyy/MM/dd")
//	private Date updated_at;
//	
//	@NotBlank
//	@Length(min=1, max=20, groups=ValidGroup1.class)
//	private String employee_name;
//	
//	@NotNull(message="{gender_check}")
//	private Integer gender;
//	
//	@NotBlank
//	@Pattern(regexp="^(070|080|090)-[0-9]{4}-[0-9]{4}$",
//		groups=ValidGroup1.class, message="{phone_check}")
//	private String employee_phone;
//	
//	@NotBlank
//	@Email(groups=ValidGroup1.class)
//	private String employee_email;
//	
//	@DateTimeFormat(pattern="yyyy/MM/dd")
//	@NotNull(message="{date_of_entry_check}")
//	private Date date_of_entry;
	
	private Employee employee;
	private Department department;
	
	public MembersForm(List<Integer> sales, List<Integer> customers) {
		this();
		this.sales = sales;
		this.customers = customers;
		this.employee.setPassword("password");
	}
}
