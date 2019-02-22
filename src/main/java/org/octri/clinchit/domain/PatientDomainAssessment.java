package org.octri.clinchit.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.octri.clinchit.view.Labelled;

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
	
	@ManyToOne
	private ClinicianPriority clinicianPriority;
	
	@ManyToOne
	private PatientReadiness patientReadiness;
	
	@Column(columnDefinition = "TEXT DEFAULT NULL")
	private String clinicianNotes;

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

	public String getClinicianNotes() {
		return clinicianNotes;
	}

	public void setClinicianNotes(String clinicianNotes) {
		this.clinicianNotes = clinicianNotes;
	}

}
