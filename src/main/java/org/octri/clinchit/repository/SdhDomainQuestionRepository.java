
package org.octri.clinchit.repository;

import org.octri.clinchit.domain.SdhDomainQuestion;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "api/sdh_domain_question")
public interface SdhDomainQuestionRepository extends PagingAndSortingRepository<SdhDomainQuestion, Long> {

}
