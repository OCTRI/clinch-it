
package org.octri.clinchit.repository;

import java.util.List;

import org.octri.clinchit.domain.QuestionnaireResponse;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "questionnaire_response")
public interface QuestionnaireResponseRepository extends PagingAndSortingRepository<QuestionnaireResponse, Long> {

	public List<QuestionnaireResponse> findAllByPatientId(Long patientId);
}
