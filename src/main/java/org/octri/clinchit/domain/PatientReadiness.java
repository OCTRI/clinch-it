package org.octri.clinchit.domain;

import javax.persistence.Entity;

import org.octri.clinchit.view.Labelled;

/**
 * The readiness as assessed by the clinician
 * 
 * <code>
 * Not Ready | Motivated | Ready
 * </code>
 * 
 * @author yateam
 */
@Entity
public class PatientReadiness extends AbstractEntity implements Labelled {
	
	private static final long serialVersionUID = 7337717060613958491L;

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
