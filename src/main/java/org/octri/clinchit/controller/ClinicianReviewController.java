
package org.octri.clinchit.controller;

import org.octri.clinchit.domain.ClinicianReview;
import org.octri.clinchit.repository.ClinicianReviewRepository;
import org.octri.clinchit.repository.SdhDomainRepository;
import org.octri.clinchit.repository.PatientReadinessRepository;
import org.octri.clinchit.repository.ClinicianPriorityRepository;
import org.octri.clinchit.repository.PatientRepository;
import org.octri.clinchit.view.OptionList;
import java.util.Map;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for {@link ClinicianReview} objects.
 */
@Controller
@RequestMapping("/data/clinician_review")
public class ClinicianReviewController extends AbstractEntityController<ClinicianReview, ClinicianReviewRepository> {

	@Autowired
	private ClinicianReviewRepository repository;
	@Autowired
	private SdhDomainRepository sdhDomainRepository;
	@Autowired
	private PatientReadinessRepository patientReadinessRepository;
	@Autowired
	private ClinicianPriorityRepository clinicianPriorityRepository;
	@Autowired
	private PatientRepository patientRepository;

	@Override
	public String newEntity(Map<String, Object> model) {
		String template = super.newEntity(model);

		// Add options for select.
		model.put("patientOptions",
				OptionList.fromSearch(patientRepository.findAll(), null));
		model.put("domainOptions",
				OptionList.fromSearch(sdhDomainRepository.findAll(), null));
		model.put("clinicianPriorityOptions",
				OptionList.fromSearch(clinicianPriorityRepository.findAll(), null));
		model.put("patientReadinessOptions",
				OptionList.fromSearch(patientReadinessRepository.findAll(), null));
		return template;
	}

	@Override
	public String edit(Map<String, Object> model, @PathVariable Long id) {
		String template = super.edit(model, id);

		ClinicianReview entity = (ClinicianReview) model.get("entity");

		// Add options for select.
		model.put("patientOptions",
				OptionList.fromSearch(patientRepository.findAll(), entity.getPatient()));
		model.put("domainOptions",
				OptionList.fromSearch(sdhDomainRepository.findAll(), entity.getDomain()));
		model.put("clinicianPriorityOptions",
				OptionList.fromSearch(clinicianPriorityRepository.findAll(), entity.getClinicianPriority()));
		model.put("patientReadinessOptions",
				OptionList.fromSearch(patientReadinessRepository.findAll(), entity.getPatientReadiness()));
		return template;
	}

	@Override
	protected Class<ClinicianReview> domainClass() {
		return ClinicianReview.class;
	}

	@Override
	protected ClinicianReviewRepository getRepository() {
		return this.repository;
	}
}
