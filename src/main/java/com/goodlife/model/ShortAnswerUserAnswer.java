package com.goodlife.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "SHORT_ANS_USER_ANS", catalog = "goodlife")
public class ShortAnswerUserAnswer implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@JoinColumn(name = "sub_chap_id", nullable = false)
	private Integer subChapId;
	
	@Id
	@JoinColumn(name = "sa_q_id")
	private Integer saQId;
	
	@Column(name = "usr_ans", nullable = false)
	private String userAnswer;
	
	@JoinColumn(name = "usr_id", nullable = false)
	private Integer userId;
	
	@Column(name = "aprvd", columnDefinition = "TINYINT", length = 1)
	private Boolean aprvd;

	public ShortAnswerUserAnswer() {
		super();
	}

	public ShortAnswerUserAnswer(Integer subChapId, Integer saQId,
			String userAnswer, Integer userId, Boolean aprvd) {
		super();
		this.subChapId = subChapId;
		this.saQId = saQId;
		this.userAnswer = userAnswer;
		this.userId = userId;
		this.aprvd = aprvd;
	}

	public Integer getSubChapId() {
		return subChapId;
	}

	public void setSubChapId(Integer subChapId) {
		this.subChapId = subChapId;
	}

	public Integer getSaQId() {
		return saQId;
	}

	public void setSaQId(Integer saQId) {
		this.saQId = saQId;
	}

	public String getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Boolean isAprvd() {
		return aprvd;
	}

	public void setAprvd(Boolean aprvd) {
		this.aprvd = aprvd;
	}
}
