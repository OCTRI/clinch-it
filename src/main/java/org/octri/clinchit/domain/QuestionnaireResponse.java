package org.octri.clinchit.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Summarizes the responses of a patient for a given SdhDomainQuestionnaire 
 * 
 * @author yateam
 *
 */
@Entity
public class QuestionnaireResponse extends AbstractEntity {
	
	private static final long serialVersionUID = 1613621648982130478L;

	@NotNull
	@ManyToOne
	private Patient patient;
	
	@NotNull
	@ManyToOne
	private SdhDomainQuestionnaire sdhDomainQuestionnaire;
	
	@NotNull
	@Lob
	@Column(columnDefinition = "TEXT")
	private String answerJson;
	
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public SdhDomainQuestionnaire getSdhDomainQuestionnaire() {
		return sdhDomainQuestionnaire;
	}

	public void setSdhDomainQuestionnaire(SdhDomainQuestionnaire sdhDomainQuestionnaire) {
		this.sdhDomainQuestionnaire = sdhDomainQuestionnaire;
	}

	public String getAnswerJson() {
		return answerJson;
	}

	public void setAnswerJson(String answerJson) {
		this.answerJson = answerJson;
	}

}
