
package org.octri.clinchit.repository;

import org.octri.clinchit.domain.SdhDomain;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "api/sdh_domain")
public interface SdhDomainRepository extends PagingAndSortingRepository<SdhDomain, Long> {

}
