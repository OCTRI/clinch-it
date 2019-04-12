package org.octri.clinchit.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class SdhDomainQuestionnaire extends AbstractEntity {
	
	private static final long serialVersionUID = 2631309160251975924L;

	@NotNull
	@ManyToOne
	private SdhDomain sdhDomain;

	@NotNull
	@Lob
	@Column(columnDefinition = "TEXT")
	private String questionJson;
	
	public SdhDomain getSdhDomain() {
		return sdhDomain;
	}

	public void setSdhDomain(SdhDomain sdhDomain) {
		this.sdhDomain = sdhDomain;
	}

	public String getQuestionJson() {
		return questionJson;
	}

	public void setQuestionText(String questionJson) {
		this.questionJson = questionJson;
	}

}
