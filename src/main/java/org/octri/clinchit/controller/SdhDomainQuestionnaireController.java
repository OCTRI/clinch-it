
package org.octri.clinchit.controller;

import org.octri.clinchit.domain.SdhDomainQuestionnaire;
import org.octri.clinchit.repository.SdhDomainQuestionnaireRepository;
import org.octri.clinchit.repository.SdhDomainRepository;
import org.octri.clinchit.view.OptionList;
import java.util.Map;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for {@link SdhDomainQuestionnaire} objects.
 */
@Controller
@RequestMapping("/data/sdh_domain_questionnaire")
public class SdhDomainQuestionnaireController extends AbstractEntityController<SdhDomainQuestionnaire, SdhDomainQuestionnaireRepository> {
	
	@Autowired
	private SdhDomainQuestionnaireRepository repository;
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

		SdhDomainQuestionnaire entity = (SdhDomainQuestionnaire) model.get("entity");

		// Add options for select.
		model.put("sdhDomainOptions", 
			OptionList.fromSearch(sdhDomainRepository.findAll(), entity.getSdhDomain()));
		return template;
	}

	@Override
	protected Class<SdhDomainQuestionnaire> domainClass() {
		return SdhDomainQuestionnaire.class;
	}
	
	@Override
	protected SdhDomainQuestionnaireRepository getRepository() {
		return this.repository;
	}
}
