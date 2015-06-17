package com.goodlife.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table (name = "MULTI_CHOICE_OPTION", catalog = "goodlife")
public class MultiChoiceOption implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "option_id", nullable = true, unique = true, columnDefinition = "MEDIUMINT AUTO_INCREMENT")
	private Integer optionId;
	
	@JoinColumn(name = "mc_q_id", nullable  = false)
	private Integer multiQuesId;
	
	@Column(name = "choice_txt")
	private String choiceText;
	
	@Column(name = "published", columnDefinition = "TINYINT(1) DEFAULT 0")
	private Boolean published;

	public MultiChoiceOption() {
		super();
	}

	public MultiChoiceOption(Integer optionId, Integer multiQuesId,
			String choiceText, Boolean published) {
		super();
		this.optionId = optionId;
		this.multiQuesId = multiQuesId;
		this.choiceText = choiceText;
		this.published = published;
	}

	public Integer getOptionId() {
		return optionId;
	}

	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}

	public Integer getMultiQuesId() {
		return multiQuesId;
	}

	public void setMultiQuesId(Integer multiQuesId) {
		this.multiQuesId = multiQuesId;
	}

	public String getChoiceText() {
		return choiceText;
	}

	public void setChoiceText(String choiceText) {
		this.choiceText = choiceText;
	}

	public Boolean getPublished() {
		return published;
	}

	public void setPublished(Boolean published) {
		this.published = published;
	}
	
}
