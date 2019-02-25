package org.octri.clinchit.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Home page controller.
 */
@RestController
public class HomeController {

	@GetMapping("/")
	public ModelAndView welcome(Map<String, Object> model) {

		model.put("page_title", "Clinch IT");
		return new ModelAndView("home", model);
	}

}
