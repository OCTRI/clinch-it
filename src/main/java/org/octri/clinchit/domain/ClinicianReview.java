package org.octri.clinchit.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Tracks a clinician's review of a specific patient and domain. Probably unique so there's only ever one per?
 * @author yateam
 *
 */
@Entity
public class ClinicianReview extends AbstractEntity {

	private static final long serialVersionUID = 188657131010908283L;

	@NotNull
	@ManyToOne
	private Patient patient;
	
	@NotNull
	@ManyToOne
	private SdhDomain domain;
	
	@ManyToOne
	private ClinicianPriority clinicianPriority;
	
	@ManyToOne
	private PatientReadiness patientReadiness;
	
	@NotNull
	private Boolean referred;
	
	@NotNull
	private Boolean referralComplete;
	
	@NotNull
	public Boolean flagged;

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

	public ClinicianPriority getClinicianPriority() {
		return clinicianPriority;
	}

	public void setClinicianPriority(ClinicianPriority clinicianPriority) {
		this.clinicianPriority = clinicianPriority;
	}

	public PatientReadiness getPatientReadiness() {
		return patientReadiness;
	}

	public void setPatientReadiness(PatientReadiness patientReadiness) {
		this.patientReadiness = patientReadiness;
	}

	public Boolean getReferred() {
		return referred;
	}

	public void setReferred(Boolean referred) {
		this.referred = referred;
	}

	public Boolean getReferralComplete() {
		return referralComplete;
	}

	public void setReferralComplete(Boolean referralComplete) {
		this.referralComplete = referralComplete;
	}

	public Boolean getFlagged() {
		return flagged;
	}

	public void setFlagged(Boolean flagged) {
		this.flagged = flagged;
	}
	
}
