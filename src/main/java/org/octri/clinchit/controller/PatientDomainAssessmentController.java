
package org.octri.clinchit.controller;

import java.util.Map;

import org.octri.clinchit.domain.PatientDomainAssessment;
import org.octri.clinchit.repository.PatientDomainAssessmentRepository;
import org.octri.clinchit.repository.PatientRepository;
import org.octri.clinchit.repository.SdhDomainRepository;
import org.octri.clinchit.view.OptionList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for {@link PatientDomainAssessment} objects.
 */
@Controller
@RequestMapping("/data/patient_domain_assessment")
public class PatientDomainAssessmentController extends AbstractEntityController<PatientDomainAssessment, PatientDomainAssessmentRepository> {
	
	@Autowired
	private PatientDomainAssessmentRepository repository;
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private SdhDomainRepository domainRepository;

	@Override
	public String newEntity(Map<String, Object> model) {
		String template = super.newEntity(model);

		// Add options for select.
		model.put("patientOptions", 
			OptionList.fromSearch(patientRepository.findAll(), null));
		model.put("domainOptions", 
			OptionList.fromSearch(domainRepository.findAll(), null));
		return template;
	}

	@Override
	public String edit(Map<String, Object> model, @PathVariable Long id) {
		String template = super.edit(model, id);

		PatientDomainAssessment entity = (PatientDomainAssessment) model.get("entity");

		// Add options for select.
		model.put("patientOptions", 
			OptionList.fromSearch(patientRepository.findAll(), entity.getPatient()));
		model.put("domainOptions", 
			OptionList.fromSearch(domainRepository.findAll(), entity.getDomain()));
		return template;
	}

	@Override
	protected Class<PatientDomainAssessment> domainClass() {
		return PatientDomainAssessment.class;
	}
	
	@Override
	protected PatientDomainAssessmentRepository getRepository() {
		return this.repository;
	}
}
