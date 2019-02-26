package org.octri.clinchit.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class SdhDomainQuestion extends AbstractEntity {
	
	private static final long serialVersionUID = 2631309160251975924L;

	@NotNull
	@ManyToOne
	private SdhDomain sdhDomain;

	private Integer questionNumber;
	
	private String questionText;

	public SdhDomain getSdhDomain() {
		return sdhDomain;
	}

	public void setSdhDomain(SdhDomain sdhDomain) {
		this.sdhDomain = sdhDomain;
	}

	public Integer getQuestionNumber() {
		return questionNumber;
	}

	public void setQuestionNumber(Integer questionNumber) {
		this.questionNumber = questionNumber;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

}
