
package org.octri.clinchit.repository;

import java.util.List;

import org.octri.clinchit.domain.SdhDomainQuestionnaire;
import org.octri.clinchit.domain.projections.SdhDomainQuestionnaireSummary;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "sdh_domain_questionnaire", excerptProjection = SdhDomainQuestionnaireSummary.class)
public interface SdhDomainQuestionnaireRepository extends PagingAndSortingRepository<SdhDomainQuestionnaire, Long> {

	public List<SdhDomainQuestionnaire> findBySdhDomainDescription(String description);

}
