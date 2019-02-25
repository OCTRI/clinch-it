
package org.octri.clinchit.controller;

import org.octri.clinchit.domain.SdhDomain;
import org.octri.clinchit.repository.SdhDomainRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for {@link SdhDomain} objects.
 */
@Controller
@RequestMapping("/data/sdh_domain")
public class SdhDomainController extends AbstractEntityController<SdhDomain, SdhDomainRepository> {
	
	@Autowired
	private SdhDomainRepository repository;
	

	@Override
	protected Class<SdhDomain> domainClass() {
		return SdhDomain.class;
	}
	
	@Override
	protected SdhDomainRepository getRepository() {
		return this.repository;
	}
}
