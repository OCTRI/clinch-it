package org.octri.clinchit.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.octri.clinchit.domain.AbstractEntity;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Abstract class implementing common functionality for domain entity CRUD operations.
 * 
 * @author lawhead
 *
 * @param <T>
 * @param <U>
 */
public abstract class AbstractEntityController<T extends AbstractEntity, U extends PagingAndSortingRepository<T, Long>> {

	// cached values
	private String templateFolder;
	private String baseRoute;

	/**
	 * Class of the domain entity. Needed due to java type erasure.
	 * 
	 * @return
	 */
	protected abstract Class<T> domainClass();

	/**
	 * Repository for the given domain entity. This should be autowired into the extending controller.
	 * 
	 * @return
	 */
	protected abstract U getRepository();

	/**
	 * Adds common view attributes to the Model passed to the view template.
	 * 
	 * @param model
	 */
	protected void addTemplateAttributes(Map<String, Object> model) {
		model.put("entityName", this.entityName());
		model.put("baseRoute", this.getBaseRoute());
	}

	@GetMapping("/")
	public String list(Map<String, Object> model) {
		addTemplateAttributes(model);
		model.put("entity_list", getRepository().findAll());
		return template("list");
	}

	@GetMapping("/{id}")
	public String show(Map<String, Object> model, @PathVariable Long id) {
		addTemplateAttributes(model);
		model.put("entity", getRepository().findById(id).get());
		return template("show");
	}

	@GetMapping("/new")
	public String newEntity(Map<String, Object> model) {
		addTemplateAttributes(model);
		model.put("entity", newEntity());
		return template("form");
	}

	@PostMapping("/new")
	public String create(Map<String, Object> model, @ModelAttribute("entity") T entity,
			RedirectAttributes redirectAttributes) {
		T newEntity = this.getRepository().save(entity);
		redirectAttributes.addFlashAttribute("successMessage", this.entityName() + " successfully created.");
		return showRedirect(newEntity.getId());
	}

	@GetMapping("/edit/{id}")
	public String edit(Map<String, Object> model, @PathVariable Long id) {
		addTemplateAttributes(model);
		model.put("entity", getRepository().findById(id).get());
		return template("form");
	}

	@PostMapping(value = "/edit/{id}")
	public String update(Map<String, Object> model, @PathVariable Long id,
			@ModelAttribute("entity") T entity, RedirectAttributes redirectAttributes) {
		this.getRepository().save(entity);
		redirectAttributes.addFlashAttribute("infoMessage", this.entityName() + " updated.");
		return showRedirect(id);
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		try {
			getRepository().deleteById(id);
		} catch (DataIntegrityViolationException e) {
			String msg = this.entityName() + " is in use and cannot be deleted.";
			redirectAttributes.addFlashAttribute("errorMessage", msg);
			return showRedirect(id);
		}

		String msg = this.entityName() + " with id " + id + " successfully deleted.";
		redirectAttributes.addFlashAttribute("infoMessage", msg);
		return listingRedirect();
	}

	/**
	 * Name displayed to the user in the UI.
	 * 
	 * @return
	 */
	protected String entityName() {
		return this.domainClass().getSimpleName();
	}

	/**
	 * Creates a new instance based on the provided domain entity class.
	 * 
	 * @return
	 */
	protected T newEntity() {
		try {
			return this.domainClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Name of the folder in which the CRUD view templates reside. Can be overridden.
	 * 
	 * @return folder name for the view templates.
	 */
	protected String templateFolder() {
		String className = this.domainClass().getSimpleName();

		String regex = "([a-z])([A-Z]+)";
		String replacement = "$1_$2";
		return className.replaceAll(regex, replacement)
				.toLowerCase();
	}

	/**
	 * Get the template folder. If the value has been previously cached, uses that value, otherwise calculates it.
	 * 
	 * @return
	 */
	private String getTemplateFolder() {
		if (templateFolder == null) {
			// cache the value
			templateFolder = templateFolder();
		}
		return templateFolder;
	}

	/**
	 * Utility function that returns the full path to the given template.
	 * 
	 * @param viewName
	 * @return
	 */
	protected String template(String viewName) {
		return getTemplateFolder() + "/" + viewName;
	}

	/**
	 * Computes the base route. Base routes should be relative to the contextPath. Unless overridden, the value is
	 * acquired from the RequestMapping annotation.
	 * 
	 * @return
	 */
	protected String baseRoute() {
		return this.getClass().getAnnotation(RequestMapping.class).value()[0];
	}

	/**
	 * Used to construct urls in the view.
	 * 
	 * @return The value of the Controller's RequestMapping; used to construct urls in the view.
	 */
	protected String getBaseRoute() {
		if (this.baseRoute == null) {
			baseRoute = baseRoute();
		}
		return baseRoute;
	}

	/**
	 * 
	 * @return The string returned from a controller method that will redirect to the listing page.
	 */
	protected String listingRedirect() {
		return "redirect:" + this.getBaseRoute() + "/";
	}

	/**
	 * 
	 * @return The string returned from a controller method that will redirect to the listing page.
	 */
	protected String showRedirect(Long id) {
		return "redirect:" + this.getBaseRoute() + "/" + id;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
