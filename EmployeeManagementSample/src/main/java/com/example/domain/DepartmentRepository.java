package com.example.domain;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.summary.DepartmentSummary;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	@Query(value=
			"SELECT d.department_id, department_name,"
			+ " SUM(sales) AS 'sum_sales', COUNT(employee_id) AS 'count_employee', AVG(sales) AS 'avg_sales'"
			+ " FROM departments d"
			+ " LEFT JOIN members m"
			+ " ON d.department_id = m.department_id"
			+ " GROUP BY d.department_id"
			+ " ORDER BY d.department_id asc", nativeQuery = true)
	public List<Object[]> getJoinDatas();
	
	default List<DepartmentSummary> findJoinData() {
		return getJoinDatas()
				.stream()
				.map(DepartmentSummary::new)
				.collect(Collectors.toList());
	}
}
