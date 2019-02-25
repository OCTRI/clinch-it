package org.octri.clinchit.domain;

import javax.persistence.Entity;

import org.octri.clinchit.view.Labelled;

/**
 * The priority as assessed by the clinician
 * 
 * <code>
 * High | Medium | Low
 * </code>
 * 
 * @author yateam
 */
@Entity
public class ClinicianPriority extends AbstractEntity implements Labelled {
	
	private static final long serialVersionUID = 5067049354005764170L;

	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getLabel() {
		return description;
	}

}
