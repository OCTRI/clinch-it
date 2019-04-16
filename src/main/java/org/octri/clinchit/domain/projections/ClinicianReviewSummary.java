package org.octri.clinchit.domain.projections;

import org.octri.clinchit.domain.ClinicianPriority;
import org.octri.clinchit.domain.ClinicianReview;
import org.octri.clinchit.domain.PatientReadiness;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

/**
 * Summarize the Clinician Review in a more natural way for REST access and the SDH view.
 * 
 * @author yateam
 *
 */
@Projection(name = "summary", types = ClinicianReview.class)
public interface ClinicianReviewSummary {

	Long getId();
	
	String getUpdatedAt();
		
	@Value("#{target.getPatient().getId()}")
	Long getPatientId();
	
	@Value("#{target.getDomain().getDescription()}")
	String getDomain();
	
	@Value("#{target.getClinicianPriority()}")
	ClinicianPriority getClinicianPriority();
	
	@Value("#{target.getPatientReadiness()}")
	PatientReadiness getPatientReadiness();
	
	Boolean getReferred();
	
	Boolean getReferralComplete();
}

	
