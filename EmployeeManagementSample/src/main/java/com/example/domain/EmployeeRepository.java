package com.example.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

//public interface 名前 extends JpaRepository <エンティティ , IDタイプ>
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>,JpaSpecificationExecutor<Employee> {
	
//	@Query(value=
//			"SELECT department_id, department_name, SUM(sales), COUNT(employee_id), AVG(sales)"
//			+ "FROM Employee"
//			+ "LEFT JOIN Department"
//			+ "ON Department.department_id = Employee.department_id"
//			+ "GROUP BY Department.department_id"
//			+ "ORDER BY Department.department_id asc", nativeQuery=true )
//	List<Employee> findJoinData();
	
//	@Query("SELECT e, d"
//			+ "FROM Employee e, Department d"
//			+ "WHERE e.department_id = d.department_id"
//			+ "AND e.sales = :value")
//	public List<Object> findJoinData(@Param("value") String value);
}
