
package org.octri.clinchit.repository;

import org.octri.clinchit.domain.PatientReadiness;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "api/patient_readiness")
public interface PatientReadinessRepository extends PagingAndSortingRepository<PatientReadiness, Long> {

}
