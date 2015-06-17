package com.goodlife.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity (name = "Instructor")
@Table(name="INSTRUCTOR", catalog = "goodlife")
public class Instructor implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	@JoinColumn(name = "usr_id", unique = true, nullable = false)
	private Integer userId;
	
	@Id
	@Column(name = "roster_id", unique = true, nullable = true, columnDefinition = "MEDIUMINT AUTO_INCREMENT")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer rosterId;
	
	@Column(name = "n_stdnt")
	private Integer numStudent;
	
	@Column(name = "tot_cap")
	private Integer totalCapacity;
	
	@Column(name = "start_dt")
	private Date startDate;

	
	public Instructor() {
		super();
	}

	public Instructor(Integer userId, Integer rosterId, Integer numStudent,
			Integer totalCapacity, Date startDate) {
		super();
		this.userId = userId;
		this.rosterId = rosterId;
		this.numStudent = numStudent;
		this.totalCapacity = totalCapacity;
		this.startDate = startDate;
	}

	public Integer getRosterId() {
		return rosterId;
	}

	public void setRosterId(Integer rosterId) {
		this.rosterId = rosterId;
	}

	public Integer getNumStudent() {
		return numStudent;
	}

	public void setNumStudent(Integer numStudent) {
		this.numStudent = numStudent;
	}

	public Integer getTotalCapacity() {
		return totalCapacity;
	}

	public void setTotalCapacity(Integer totalCapacity) {
		this.totalCapacity = totalCapacity;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}