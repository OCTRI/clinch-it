package org.octri.clinchit.domain.projections;

import org.octri.clinchit.domain.PatientReadiness;
import org.springframework.data.rest.core.config.Projection;

/**
 * Summarize the Patient Readiness in a more natural way for REST access and the SDH view.
 * 
 * @author yateam
 *
 */
@Projection(name = "summary", types = PatientReadiness.class)
public interface PatientReadinessSummary extends IdDescriptionSummary {

}

	
