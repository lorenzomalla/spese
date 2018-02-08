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

	public List<Answer> getAnswers() {
		return this.answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
}