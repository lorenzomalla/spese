package com.accenture.dsg.backend.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cat_templates database table.
 * 
 */
@Entity
@Table(name="cat_templates")
@NamedQuery(name="CatTemplate.findAll", query="SELECT c FROM CatTemplate c")
public class CatTemplate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	private String markup;

	//bi-directional many-to-one association to Template
	@OneToMany(mappedBy="catTemplate")
	private List<Template> templates;

	public CatTemplate() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMarkup() {
		return this.markup;
	}

	public void setMarkup(String markup) {
		this.markup = markup;
	}

	public List<Template> getTemplates() {
		return this.templates;
	}

	public void setTemplates(List<Template> templates) {
		this.templates = templates;
	}

}