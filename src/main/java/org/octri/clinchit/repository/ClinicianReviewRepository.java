
package org.octri.clinchit.repository;

import java.util.List;

import org.octri.clinchit.domain.ClinicianReview;
import org.octri.clinchit.domain.projections.ClinicianReviewSummary;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "clinician_review", excerptProjection = ClinicianReviewSummary.class)
public interface ClinicianReviewRepository extends PagingAndSortingRepository<ClinicianReview, Long> {
	
	public List<ClinicianReview> findByPatientId(Long id);

}
