package com.example.specification;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


public class EmployeeSpecification<T> {

	/**
	 * 性別で検索
	 */
	public Specification<T> genderEqual(Integer gender) {
		
		return gender == null ? null : new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root,
					CriteriaQuery<?> query, CriteriaBuilder builder) {
				
				return builder.equal(root.get("gender"), gender);
			}
		};	
	}
	
	/**
	 * メールアドレスで検索
	 */
	public Specification<T> employeeEmaiContains(String employee_email) {
		return StringUtils.isEmpty(employee_email) ? null : (root, query, builder) -> {
			return builder.like(root.get("employee_email"), '%' + employee_email + '%');
		};
	}
	
	/**
	 * startDateで検索
	 */
	public Specification<T> startDateGreaterThanEqual(Date start_date) {
		return start_date == null ? null : (root, query, builder) -> {
			//greaterThanOrEqualTo 第一数の要素が第二引数と等しいか大きいことをチェック
			return builder.greaterThanOrEqualTo(root.get("start_date"), start_date);
		};
	}
	
	/**
	 * endDateで検索
	 */
	public Specification<T> endDateLessThanEqual(Date end_date) {
		return end_date == null ? null :(root, query, builder) -> {
			//lessThanOrEqualTo 第一数の要素が第二引数と等しいか小さいことをチェック
			return builder.lessThanOrEqualTo(root.get("end_date"), end_date);
		};
	}
	
	
	
}
