
package org.octri.clinchit.controller;

import org.octri.clinchit.domain.PatientDomainAssessment;
import org.octri.clinchit.repository.PatientDomainAssessmentRepository;
import org.octri.clinchit.repository.PatientRepository;
import org.octri.clinchit.repository.SdhDomainRepository;
import org.octri.clinchit.repository.ClinicianPriorityRepository;
import org.octri.clinchit.repository.PatientReadinessRepository;
import org.octri.clinchit.view.OptionList;
import java.util.Map;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	@Autowired
	private ClinicianPriorityRepository clinicianPriorityRepository;
	@Autowired
	private PatientReadinessRepository patientReadinessRepository;

	@Override
	public String newEntity(Map<String, Object> model) {
		String template = super.newEntity(model);

		// Add options for select.
		model.put("patientOptions", 
			OptionList.fromSearch(patientRepository.findAll(), null));
		model.put("domainOptions", 
			OptionList.fromSearch(domainRepository.findAll(), null));
		model.put("clinicianPriorityOptions", 
			OptionList.fromSearch(clinicianPriorityRepository.findAll(), null));
		model.put("patientReadinessOptions", 
			OptionList.fromSearch(patientReadinessRepository.findAll(), null));
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
		model.put("clinicianPriorityOptions", 
			OptionList.fromSearch(clinicianPriorityRepository.findAll(), entity.getClinicianPriority()));
		model.put("patientReadinessOptions", 
			OptionList.fromSearch(patientReadinessRepository.findAll(), entity.getPatientReadiness()));
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
