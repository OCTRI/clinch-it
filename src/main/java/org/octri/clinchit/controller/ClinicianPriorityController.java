
package org.octri.clinchit.controller;

import org.octri.clinchit.domain.ClinicianPriority;
import org.octri.clinchit.repository.ClinicianPriorityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for {@link ClinicianPriority} objects.
 */
@Controller
@RequestMapping("/data/clinician_priority")
public class ClinicianPriorityController extends AbstractEntityController<ClinicianPriority, ClinicianPriorityRepository> {
	
	@Autowired
	private ClinicianPriorityRepository repository;
	

	@Override
	protected Class<ClinicianPriority> domainClass() {
		return ClinicianPriority.class;
	}
	
	@Override
	protected ClinicianPriorityRepository getRepository() {
		return this.repository;
	}
}
