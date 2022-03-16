package com.example.domain;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.summary.DepartmentSummary;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
	
	/**
	 * 
	 * 部門別一覧表示用クエリ
	 */
	@Query(value=
			" SELECT d.department_id, department_name,"
			+ " SUM(sales) AS 'sum_sales', COUNT(employee_id) AS 'count_employee', AVG(sales) AS 'avg_sales', retirement "
			+ " FROM departments d"
			+ " LEFT JOIN members m"
			+ " ON d.department_id = m.department_id"
			+ " WHERE retirement = 0"
			+ " GROUP BY d.department_id"
			+ " ORDER BY d.department_id asc", nativeQuery = true)
	public List<Object[]> getJoinDatas();
	
	//Object型で取得後streamでDepartmentSummaryに流し込んでNEWしてる
	default List<DepartmentSummary> departmentJoinMembersData() {
		return getJoinDatas()
				.stream()
				.map(DepartmentSummary::new)
				.collect(Collectors.toList());
	}
}
