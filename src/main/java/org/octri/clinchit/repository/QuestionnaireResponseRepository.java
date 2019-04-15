
package org.octri.clinchit.repository;

import java.util.List;

import org.octri.clinchit.domain.QuestionnaireResponse;
import org.octri.clinchit.domain.projections.QuestionnaireResponseSummary;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "questionnaire_response", excerptProjection = QuestionnaireResponseSummary.class)
public interface QuestionnaireResponseRepository extends PagingAndSortingRepository<QuestionnaireResponse, Long> {

	public List<QuestionnaireResponse> findByPatientId(Long id);
}
