package com.example.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class MembersPK implements Serializable {
	
	private Integer id;
	private Integer employee_id;
}
