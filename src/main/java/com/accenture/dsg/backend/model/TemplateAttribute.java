package com.accenture.dsg.backend.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the template_attributes database table.
 * 
 */
@Entity
@Table(name="template_attributes")
@NamedQuery(name="TemplateAttribute.findAll", query="SELECT t FROM TemplateAttribute t")
public class TemplateAttribute implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	private String key;

	private String value;

	//bi-directional many-to-one association to Template
	@ManyToOne
	private Template template;

	public TemplateAttribute() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Template getTemplate() {
		return this.template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

}