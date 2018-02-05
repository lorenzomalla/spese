package com.accenture.dsg.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Templates {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)

	private int id;
	
	@ManyToOne
		private CatTemplates cat_template_id;

	@ManyToOne
		private Answers answer_id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CatTemplates getCatTemplateId() {
		return cat_template_id;
	}

	public void setCatTemplateId(CatTemplates cat_template_id) {
		this.cat_template_id = cat_template_id;
	}
	
	public Answers getAnswerId() {
		return answer_id;
	}

	public void setAnswerId(Answers answer_id) {
		this.answer_id = answer_id;
	}
}
