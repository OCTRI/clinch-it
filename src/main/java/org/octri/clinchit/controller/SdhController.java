package org.octri.clinchit.controller;

import java.util.Map;

import org.octri.clinchit.domain.Patient;
import org.octri.clinchit.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Home page controller.
 */
@RestController
public class SdhController {
	
	@Autowired
	PatientRepository patientRepository;
	
	/**
	 * Show the Vue mockup
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("patient/{id}/sdh")
	public ModelAndView sdh(Map<String, Object> model, @PathVariable Long id) {

		Patient patient = patientRepository.findById(id).get();
		model.put("patient", patient);
		model.put("pageScripts", new String[] {"sdh"});
		return new ModelAndView("sdh", model);
	}

	/**
	 * Show the static mockup
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("patient/{id}/sdh-mockup")
	public ModelAndView sdhMockup(Map<String, Object> model, @PathVariable Long id) {

		Patient patient = patientRepository.findById(id).get();
		model.put("patient", patient);
		return new ModelAndView("sdh-mockup", model);
	}

}
