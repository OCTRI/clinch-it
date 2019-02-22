
package org.octri.clinchit.repository;

import org.octri.clinchit.domain.PatientDomainAssessment;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "api/patient_domain_assessment")
public interface PatientDomainAssessmentRepository extends PagingAndSortingRepository<PatientDomainAssessment, Long> {

}
