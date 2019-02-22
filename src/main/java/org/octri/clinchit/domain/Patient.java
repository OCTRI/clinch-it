package org.octri.clinchit.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.octri.clinchit.view.Labelled;

@Entity
public class Patient extends AbstractEntity implements Labelled {

	private static final long serialVersionUID = -3429231186560230651L;

	private String name;
	
	@Temporal(value = TemporalType.DATE)
	private Date dateOfBirth;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public String getLabel() {
		return name;
	}
	
	

}
