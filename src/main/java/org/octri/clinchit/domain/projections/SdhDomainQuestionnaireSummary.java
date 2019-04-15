package org.octri.clinchit.domain.projections;

import org.octri.clinchit.domain.SdhDomainQuestionnaire;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

/**
 * Summarize the Questionnaire in a more natural way for REST access and the SDH view.
 * 
 * @author yateam
 *
 */
@Projection(name = "summary", types = SdhDomainQuestionnaire.class)
public interface SdhDomainQuestionnaireSummary {

	Long getId();
	
	@Value("#{target.getSdhDomain().getDescription()}")
	String getSdhDomain();
	
	String getQuestionJson();
	
}

	
