package com.accenture.dsg.backend.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the tree_structure database table.
 * 
 */
@Entity
@Table(name="tree_structure")
@NamedQueries({
@NamedQuery(name="TreeStructure.findAll", query="SELECT t FROM TreeStructure t"),
})
public class TreeStructure implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@ManyToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="cat_structure_type_id")
	@JsonIgnore
	private CatTreeStructureType catStructureTypeId;

	//bi-directional many-to-one association to Answer
	@OneToMany(mappedBy="treeStructure", cascade=CascadeType.ALL)
	private List<Answer> answers = new ArrayList<>();

	//bi-directional many-to-one association to Question
	@OneToMany(mappedBy="treeStructure", cascade=CascadeType.ALL)
	private List<Question> questions = new ArrayList<>();

	
	
	//bi-directional many-to-one association to TreeStructure
	@ManyToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="parent_id")
	@JsonIgnore
	private TreeStructure treeStructure;
	

	//bi-directional many-to-one association to TreeStructure
	@OneToMany(mappedBy="treeStructure", cascade=CascadeType.ALL)
	private List<TreeStructure> treeStructures = new ArrayList<>();

	public TreeStructure() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@JsonIgnore
	public CatTreeStructureType getCatStructureTypeId() {
		return this.catStructureTypeId;
	}
	@JsonProperty(access=Access.WRITE_ONLY) 
	public void setCatStructureTypeId(CatTreeStructureType catStructureTypeId) {
		this.catStructureTypeId = catStructureTypeId;
	}
	
	public List<Answer> getAnswers() {
		return this.answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public Answer addAnswer(Answer answer) {
		getAnswers().add(answer);
		answer.setTreeStructure(this);

		return answer;
	}

	public Answer removeAnswer(Answer answer) {
		getAnswers().remove(answer);
		answer.setTreeStructure(null);

		return answer;
	}
	public List<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Question addQuestion(Question question) {
		getQuestions().add(question);
		question.setTreeStructure(this);

		return question;
	}

	public Question removeQuestion(Question question) {
		getQuestions().remove(question);
		question.setTreeStructure(null);

		return question;
	}

	public List<TreeStructure> getTreeStructures() {
		return this.treeStructures;
	}

	public void setTreeStructures(List<TreeStructure> treeStructures) {
		this.treeStructures = treeStructures;
	}

	public TreeStructure addTreeStructure(TreeStructure treeStructure) {
		getTreeStructures().add(treeStructure);
		treeStructure.setTreeStructure(this);

		return treeStructure;
	}

	public TreeStructure removeTreeStructure(TreeStructure treeStructure) {
		getTreeStructures().remove(treeStructure);
		treeStructure.setTreeStructure(null);

		return treeStructure;
	}
	
	@JsonIgnore
	public TreeStructure getTreeStructure() {
		return this.treeStructure;
	}
	
	@JsonProperty(access=Access.WRITE_ONLY) 
	public void setTreeStructure(TreeStructure treeStructure) {
		this.treeStructure = treeStructure;
	}

}