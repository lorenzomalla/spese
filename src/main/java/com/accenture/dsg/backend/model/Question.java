package com.accenture.dsg.backend.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


/**
 * The persistent class for the questions database table.
 * 
 */
@Entity
@Table(name="questions")
@NamedQuery(name="Question.findAll", query="SELECT q FROM Question q")
public class Question implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="page_subtitle")
	private String pageSubtitle;

	@Column(name="page_title")
	private String pageTitle;

	//bi-directional many-to-one association to TreeStructure
	@ManyToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="tree_structure_id")
	@JsonIgnore
	private TreeStructure treeStructure;

	public Question() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPageSubtitle() {
		return this.pageSubtitle;
	}

	public void setPageSubtitle(String pageSubtitle) {
		this.pageSubtitle = pageSubtitle;
	}

	public String getPageTitle() {
		return this.pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
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