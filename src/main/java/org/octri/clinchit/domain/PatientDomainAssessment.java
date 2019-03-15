package org.octri.clinchit.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Summarizes the assessment of a patient for a given SdhDomain - the patient readiness,
 * clinician priority, and assessment date.
 * 
 * @author yateam
 *
 */
@Entity
public class PatientDomainAssessment extends AbstractEntity {
	
	private static final long serialVersionUID = 1613621648982130478L;

	@NotNull
	@ManyToOne
	private Patient patient;
	
	@NotNull
	@ManyToOne
	private SdhDomain domain;
	
	// Date? Or use updatedAt?
	
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public SdhDomain getDomain() {
		return domain;
	}

	public void setDomain(SdhDomain domain) {
		this.domain = domain;
	}

}
