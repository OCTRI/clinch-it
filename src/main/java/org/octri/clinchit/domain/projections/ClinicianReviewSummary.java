package org.octri.clinchit.domain.projections;

import org.octri.clinchit.domain.ClinicianReview;
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
	
	@Value("#{target.getClinicianPriority().getDescription()}")
	String getClinicianPriority();
	
	@Value("#{target.getPatientReadiness().getDescription()}")
	String getPatientReadiness();
}

	
