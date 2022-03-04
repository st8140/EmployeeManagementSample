package com.example.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>,
	JpaSpecificationExecutor<Department> {

	@Query(value=
			"SELECT d.department_id, department_name, SUM(sales), COUNT(employee_id), AVG(sales)"
			+ "FROM departments d"
			+ "LEFT JOIN employees e"
			+ "ON d.department_id = e.department_id"
			+ "GROUP BY d.department_id"
			+ "ORDER BY d.department_id asc", nativeQuery = true)
	public List<Object[]> findJoinData();
}
