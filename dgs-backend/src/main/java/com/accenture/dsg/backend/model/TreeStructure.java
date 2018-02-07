package com.accenture.dsg.backend.model;

import java.io.Serializable;
import javax.persistence.*;

import com.accenture.dsg.backend.model.Answer;
import com.accenture.dsg.backend.model.CatTreeStructureType;
import com.accenture.dsg.backend.model.Question;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the tree_structure database table.
 * 
 */
@Entity	
@Table(name="tree_structure")
@NamedQueries({
	@NamedQuery(name="TreeStructure.findAll", query="SELECT t FROM TreeStructure t"),
	@NamedQuery(name="TreeStructure.findByParentId", query="SELECT t FROM TreeStructure t WHERE t.treeStructure = :treeStructure")
})
public class TreeStructure implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

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
	private Set<TreeStructure> treeStructures = new HashSet<TreeStructure>();;
	
	public TreeStructure() {
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
		return (List<TreeStructure>) this.treeStructures;
	}

	public void setTreeStructures(List<TreeStructure> treeStructures) {
		this.treeStructures = (Set<TreeStructure>) treeStructures;
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