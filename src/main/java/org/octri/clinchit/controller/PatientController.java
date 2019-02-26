
package org.octri.clinchit.controller;

import org.octri.clinchit.domain.Patient;
import org.octri.clinchit.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for {@link Patient} objects.
 */
@Controller
@RequestMapping("/patient")
public class PatientController extends AbstractEntityController<Patient, PatientRepository> {
	
	@Autowired
	private PatientRepository repository;
	

	@Override
	protected Class<Patient> domainClass() {
		return Patient.class;
	}
	
	@Override
	protected PatientRepository getRepository() {
		return this.repository;
	}
}
