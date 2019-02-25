package org.octri.clinchit.domain;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.octri.clinchit.view.Labelled;

/**
 * The Social Determinants of Health Domain
 * 
 * <code>
 * Food Security | Transportation | Housing Instability | Utilities | Interpersonal Violence | Education | Financial Strain
 * </code>
 * 
 * @author yateam
 *
 */
@Entity
public class SdhDomain extends AbstractEntity implements Labelled {

	private static final long serialVersionUID = -8712168032144623237L;

	@NotNull
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
