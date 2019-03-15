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
	
}
