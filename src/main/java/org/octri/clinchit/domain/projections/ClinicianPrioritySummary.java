package org.octri.clinchit.domain.projections;

import org.octri.clinchit.domain.ClinicianPriority;
import org.springframework.data.rest.core.config.Projection;

/**
 * Summarize the Clinician Priority in a more natural way for REST access and the SDH view.
 * 
 * @author yateam
 *
 */
@Projection(name = "summary", types = ClinicianPriority.class)
public interface ClinicianPrioritySummary extends IdDescriptionSummary{

}

	
