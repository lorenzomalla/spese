package com.accenture.dsg.backend.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//bi-directional many-to-one association to CatTemplate
	@ManyToOne
	@JoinColumn(name="cat_template_id")
	@JsonIgnore
	private CatTemplate catTemplate;

	//bi-directional many-to-one association to Answer
	@OneToMany(mappedBy="template", cascade=CascadeType.ALL)
	private List<Answer> answers = new ArrayList<>();
	
	public Template() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@JsonIgnore
	public CatTemplate getCatTemplate() {
		return this.catTemplate;
	}
	@JsonProperty(access=Access.WRITE_ONLY) 
	public void setCatTemplate(CatTemplate catTemplate) {
		this.catTemplate = catTemplate;
	}

	public List<Answer> getAnswers() {
		return this.answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public Answer addAnswer(Answer answer) {
		getAnswers().add(answer);
		answer.setTemplate(this);

		return answer;
	}

	public Answer removeAnswer(Answer answer) {
		getAnswers().remove(answer);
		answer.setTemplate(null);

		return answer;
	}
}