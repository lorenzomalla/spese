package com.accenture.dsg.backend.model;

import java.io.Serializable;
import javax.persistence.*;



/**
 * The persistent class for the answers database table.
 * 
 */
@Entity
@Table(name="answers")
@NamedQueries({
	@NamedQuery(name="Answer.findAll", query="SELECT a FROM Answer a"),
	@NamedQuery(name="Answer.findByTreeId", query="SELECT a FROM Answer a WHERE a.treeStructure = :treeId")
})
public class Answer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	private String description;

	private String image;

	private String title;

	//bi-directional many-to-one association to TreeStructure
	@ManyToOne
	@JoinColumn(name="tree_structure_id")
	private TreeStructure treeStructure;

	//bi-directional many-to-one association to Template
	@ManyToOne
	@JoinColumn(name="answer_id")
	private Template template;

	public Answer() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public TreeStructure getTreeStructure() {
		return this.treeStructure;
	}

	public void setTreeStructure(TreeStructure treeStructure) {
		this.treeStructure = treeStructure;
	}

	public Template getTemplate() {
		return this.template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

}