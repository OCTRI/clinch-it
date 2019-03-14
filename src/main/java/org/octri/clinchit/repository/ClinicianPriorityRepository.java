
package org.octri.clinchit.repository;

import org.octri.clinchit.domain.ClinicianPriority;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "clinician_priority")
public interface ClinicianPriorityRepository extends PagingAndSortingRepository<ClinicianPriority, Long> {

}
