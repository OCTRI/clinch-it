package org.octri.clinchit.domain.projections;

import org.octri.clinchit.domain.QuestionnaireResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

/**
 * Summarize the Questionnaire Response in a more natural way for REST access and the SDH view.
 * 
 * @author yateam
 *
 */
@Projection(name = "summary", types = QuestionnaireResponse.class)
public interface QuestionnaireResponseSummary {

	Long getId();
	
	String getCreatedAt();
		
	@Value("#{target.getPatient().getId()}")
	Long getPatientId();
	
	@Value("#{target.getSdhDomainQuestionnaire().getSdhDomain().getDescription()}")
	String getDomain();
	
	String getAnswerJson();
	
}

	
