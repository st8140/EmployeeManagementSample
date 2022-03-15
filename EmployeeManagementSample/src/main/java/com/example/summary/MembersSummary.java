package com.example.summary;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MembersSummary {
	
	private Integer employee_id;
	private Integer department_id;
	private String employee_name;
	private String district_in_charge;
	private Integer sales;
	private Integer customers;
	
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date updated_at;
	
	private boolean retirement;
	
	public MembersSummary(Object[] objects) {
		this((Integer) objects[0], (Integer) objects[1], (String) objects[2], (String) objects[3], (Integer) objects[4],
				(Integer) objects[5], (Date) objects[6], (boolean) objects[7]);
	}
}
