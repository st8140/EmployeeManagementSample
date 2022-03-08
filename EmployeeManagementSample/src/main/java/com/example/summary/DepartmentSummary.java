package com.example.summary;

import java.math.BigDecimal;
import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentSummary {
	
	private Integer department_id;
	private String department_name;
	private BigDecimal  sum_sales;
	private BigInteger  count_employee;
	private BigDecimal  avg_sales;
	
	public DepartmentSummary(Object[] objects) { 
	    this((Integer) objects[0], (String) objects[1], (BigDecimal) objects[2], 
	    		(BigInteger) objects[3], (BigDecimal) objects[4]);
	  }


	

	

	

}
