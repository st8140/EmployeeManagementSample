package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.domain.Members;
import com.example.domain.MembersRepository;
import com.example.summary.MembersSummary;

@Service
public class MembersService {
	
	@Autowired
	MembersRepository mRepository;
	
	/**
	 * 社員別一覧検索
	 */
	public List<MembersSummary> employeeJoinMembersList(Integer department_id) {
		return mRepository.employeeJoinMembersData(department_id);
	}
	
	/**
	 * 社員ID検索
	 */
	@GetMapping("employee_id")
	public Members findOne(@PathVariable("employee_id") Integer employee_id) {
		return mRepository.findById(employee_id);
	} 
	
	/**
	 * 新規複数登録
	 */
	@Transactional
	public void multipleRegistrations(Members members) {
		mRepository.save(members);
	}
	
	/**
	 * 更新処理
	 */
	@Transactional
	public void update(Members member) {
		mRepository.save(member);			
	}
	
	/**
	 * 退職処理
	 */
	@Transactional
	public void retirement(boolean retirement, Integer employee_id) {
		mRepository.updateByRetirement(retirement, employee_id);
	}
	
	/**
	 * 在籍社員検索
	 */
	public List<MembersSummary> findEnrollmentEmployeeList(Integer department_id) {
		return mRepository.findEnrollmentData(department_id);
	}
	
	
	/**
	 * 退職社員検索
	 */
	public List<MembersSummary> findRetirementEmployeeList(Integer department_id) {
		return mRepository.findRetirementData(department_id);
	}
}
