
package org.octri.clinchit.repository;

import org.octri.clinchit.domain.Patient;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(itemResourceRel = "api", path = "patient")
public interface PatientRepository extends PagingAndSortingRepository<Patient, Long> {

}
