package com.example.domain;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.summary.MembersSummary;

@Repository
public interface MembersRepository extends JpaRepository<Members, MembersPK>{

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
	
	default List<MembersSummary> employeeJoinMembersData(Integer department_id) {
		return getJoinDatas(department_id)
				.stream()
				.map(MembersSummary::new)
				.collect(Collectors.toList());
	}
	
	Members findById(Integer employee_id);

	/**
	 * UPDATEクエリ
	 * @param district_in_charge
	 * @param sales
	 * @param customers
	 * @param updated_at
	 * @param employee_id
	 */
//	@Modifying
//	@Query(value=
//			"UPDATE members SET"
//			+ " district_in_charge = :district_in_charge,"
//			+ " sales = :sales,"
//			+ " customers = :customers,"
//			+ " updated_at = :updated_at"
//			+ " WHERE employee_id = :employee_id", nativeQuery=true)
//	void updateMember(@Param("district_in_charge") String district_in_charge,
//			@Param("sales") Integer sales, @Param("customers") Integer customers, 
//			@Param("updated_at") Date updated_at, @Param("employee_id") Integer employee_id);
}
