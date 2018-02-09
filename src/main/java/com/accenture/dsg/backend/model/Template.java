package com.accenture.dsg.backend.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the templates database table.
 * 
 */
@Entity
@Table(name="templates")
@NamedQuery(name="Template.findAll", query="SELECT t FROM Template t")
public class Template implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	//bi-directional many-to-one association to CatTemplate
	@ManyToOne
	@JoinColumn(name="cat_template_id")
	private CatTemplate catTemplate;

	public Template() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CatTemplate getCatTemplate() {
		return this.catTemplate;
	}

	public void setCatTemplate(CatTemplate catTemplate) {
		this.catTemplate = catTemplate;
	}

}