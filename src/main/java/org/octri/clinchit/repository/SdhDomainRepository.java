
package org.octri.clinchit.repository;

import org.octri.clinchit.domain.SdhDomain;
import org.octri.clinchit.domain.projections.SdhDomainSummary;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "sdh_domain", excerptProjection = SdhDomainSummary.class)
public interface SdhDomainRepository extends PagingAndSortingRepository<SdhDomain, Long> {

}
