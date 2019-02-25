
package org.octri.clinchit.controller;

import org.octri.clinchit.domain.PatientReadiness;
import org.octri.clinchit.repository.PatientReadinessRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for {@link PatientReadiness} objects.
 */
@Controller
@RequestMapping("/data/patient_readiness")
public class PatientReadinessController extends AbstractEntityController<PatientReadiness, PatientReadinessRepository> {
	
	@Autowired
	private PatientReadinessRepository repository;
	

	@Override
	protected Class<PatientReadiness> domainClass() {
		return PatientReadiness.class;
	}
	
	@Override
	protected PatientReadinessRepository getRepository() {
		return this.repository;
	}
}
