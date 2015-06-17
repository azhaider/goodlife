package com.goodlife.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity (name = "SuperAdmin")
@Table(name="SUPER_ADMIN", catalog = "goodlife")
public class SuperAdmin implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@JoinColumn(name = "usr_id", unique = true, nullable = false)
	private Integer userId;
	
	public SuperAdmin() {
		super();
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}