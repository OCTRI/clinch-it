
package org.octri.clinchit.controller;

import org.octri.clinchit.domain.SdhDomainQuestion;
import org.octri.clinchit.repository.SdhDomainQuestionRepository;
import org.octri.clinchit.repository.SdhDomainRepository;
import org.octri.clinchit.view.OptionList;
import java.util.Map;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for {@link SdhDomainQuestion} objects.
 */
@Controller
@RequestMapping("/data/sdh_domain_question")
public class SdhDomainQuestionController extends AbstractEntityController<SdhDomainQuestion, SdhDomainQuestionRepository> {
	
	@Autowired
	private SdhDomainQuestionRepository repository;
	@Autowired
	private SdhDomainRepository sdhDomainRepository;

	@Override
	public String newEntity(Map<String, Object> model) {
		String template = super.newEntity(model);

		// Add options for select.
		model.put("sdhDomainOptions", 
			OptionList.fromSearch(sdhDomainRepository.findAll(), null));
		return template;
	}

	@Override
	public String edit(Map<String, Object> model, @PathVariable Long id) {
		String template = super.edit(model, id);

		SdhDomainQuestion entity = (SdhDomainQuestion) model.get("entity");

		// Add options for select.
		model.put("sdhDomainOptions", 
			OptionList.fromSearch(sdhDomainRepository.findAll(), entity.getSdhDomain()));
		return template;
	}

	@Override
	protected Class<SdhDomainQuestion> domainClass() {
		return SdhDomainQuestion.class;
	}
	
	@Override
	protected SdhDomainQuestionRepository getRepository() {
		return this.repository;
	}
}
