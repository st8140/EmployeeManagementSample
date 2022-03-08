package com.example.domain;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.summary.EmployeeSummary;

//public interface 名前 extends JpaRepository <エンティティ , IDタイプ>
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>,JpaSpecificationExecutor<Employee> {
	
	/**
	 * 社員別一覧表示用クエリ
	 */
	@Query(value=
			"SELECT e.employee_id, employee_name, district_in_charge, sales, customers, updated_at, department_id"
			+ " FROM employees e"
			+ " LEFT JOIN members m"
			+ " ON e.employee_id = m.employee_id"
			+ " WHERE department_id = :department_id"
			+ " ORDER BY e.employee_id ASC", nativeQuery=true)
	List<Object[]> getJoinDatas(@Param("department_id") Integer department_id);
	
	default List<EmployeeSummary> employeeJoinMembersData(Integer department_id) {
		return getJoinDatas(department_id)
				.stream()
				.map(EmployeeSummary::new)
				.collect(Collectors.toList());
	}
}
