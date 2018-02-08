package com.accenture.dsg.backend.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


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
	private long id;

	//bi-directional many-to-one association to TemplateAttribute
	@OneToMany(mappedBy="template")
	private List<TemplateAttribute> templateAttributes;

	//bi-directional many-to-one association to CatTemplate
	@ManyToOne
	@JoinColumn(name="cat_template_id")
	private CatTemplate catTemplate;

	//bi-directional many-to-one association to Answer
	@OneToMany(mappedBy="template")
	private List<Answer> answers;

	public Template() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<TemplateAttribute> getTemplateAttributes() {
		return this.templateAttributes;
	}

	public void setTemplateAttributes(List<TemplateAttribute> templateAttributes) {
		this.templateAttributes = templateAttributes;
	}

	public TemplateAttribute addTemplateAttribute(TemplateAttribute templateAttribute) {
		getTemplateAttributes().add(templateAttribute);
		templateAttribute.setTemplate(this);

		return templateAttribute;
	}

	public TemplateAttribute removeTemplateAttribute(TemplateAttribute templateAttribute) {
		getTemplateAttributes().remove(templateAttribute);
		templateAttribute.setTemplate(null);

		return templateAttribute;
	}

	public CatTemplate getCatTemplate() {
		return this.catTemplate;
	}

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