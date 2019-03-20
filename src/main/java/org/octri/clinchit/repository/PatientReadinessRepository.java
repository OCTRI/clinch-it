
package org.octri.clinchit.repository;

import org.octri.clinchit.domain.PatientReadiness;
import org.octri.clinchit.domain.projections.PatientReadinessSummary;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "patient_readiness", excerptProjection = PatientReadinessSummary.class)
public interface PatientReadinessRepository extends PagingAndSortingRepository<PatientReadiness, Long> {

}
