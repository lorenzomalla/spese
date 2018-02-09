package com.accenture.dsg.backend.model;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.*;

import java.util.ArrayList;
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
	private int id;

	private String templatename;
	
	@Column(columnDefinition="LONGTEXT")
	private String markup;

	//bi-directional many-to-one association to Template
	@OneToMany(mappedBy="catTemplate")
	private List<Template> templates = new ArrayList<>();

	public CatTemplate() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTemplateName() {
		return this.templatename;
	}

	public void setTemplateName(String templatename) {
		this.templatename = templatename;
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

	public Template addTemplate(Template template) {
		getTemplates().add(template);
		template.setCatTemplate(this);

		return template;
	}

	public Template removeTemplate(Template template) {
		getTemplates().remove(template);
		template.setCatTemplate(null);

		return template;
	}

}