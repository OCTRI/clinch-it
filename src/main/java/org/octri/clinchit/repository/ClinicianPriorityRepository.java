
package org.octri.clinchit.repository;

import org.octri.clinchit.domain.ClinicianPriority;
import org.octri.clinchit.domain.projections.ClinicianPrioritySummary;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "clinician_priority", excerptProjection = ClinicianPrioritySummary.class)
public interface ClinicianPriorityRepository extends PagingAndSortingRepository<ClinicianPriority, Long> {

}
