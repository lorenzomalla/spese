package com.accenture.dsg.backend.model;

import java.io.Serializable;
import javax.persistence.*;

import com.accenture.dsg.backend.model.Answer;
import com.accenture.dsg.backend.model.CatTreeStructureType;
import com.accenture.dsg.backend.model.Question;

import java.util.List;


/**
 * The persistent class for the tree_structure database table.
 * 
 */
@Entity	
@Table(name="tree_structure")
@NamedQuery(name="TreeStructure.findAll", query="SELECT t FROM TreeStructure t")
public class TreeStructure implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//bi-directional many-to-one association to Answer
	@OneToMany(mappedBy="treeStructure")
	private List<Answer> answers;

	//bi-directional many-to-one association to Question
	@OneToMany(mappedBy="treeStructure")
	private List<Question> questions;

	//bi-directional many-to-one association to CatTreeStructureType
	@ManyToOne
	@JoinColumn(name="cat_tree_structure_type_id")
	private CatTreeStructureType catTreeStructureType;

	//bi-directional many-to-one association to TreeStructure
	@ManyToOne
	@JoinColumn(name="parent_id")
	private TreeStructure treeStructure;

	//bi-directional many-to-one association to TreeStructure
	@OneToMany(mappedBy="treeStructure")
	private List<TreeStructure> treeStructures;

	public TreeStructure() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public CatTreeStructureType getCatTreeStructureType() {
		return this.catTreeStructureType;
	}

	public void setCatTreeStructureType(CatTreeStructureType catTreeStructureType) {
		this.catTreeStructureType = catTreeStructureType;
	}

	public TreeStructure getTreeStructure() {
		return this.treeStructure;
	}

	public void setTreeStructure(TreeStructure treeStructure) {
		this.treeStructure = treeStructure;
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

}