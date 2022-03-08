package com.example.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

//public interface 名前 extends JpaRepository <エンティティ , IDタイプ>
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>,JpaSpecificationExecutor<Employee> {
	
}
