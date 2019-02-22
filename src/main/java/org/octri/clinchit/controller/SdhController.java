package org.octri.clinchit.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Home page controller.
 */
@RestController
public class SdhController {

	@GetMapping("patient/{id}/sdh")
	public ModelAndView readiness(Map<String, Object> model, @PathVariable Long id) {

		return new ModelAndView("sdh", model);
	}

}
