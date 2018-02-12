package com.accenture.dsg.backend.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


/**
 * The persistent class for the answers database table.
 * 
 */
@Entity
@Table(name="answers")
@NamedQuery(name="Answer.findAll", query="SELECT a FROM Answer a")
public class Answer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String description;

	private String image;

	private String title;
	
//	//bi-directional many-to-one association to Template
//	@ManyToOne
//	@JoinColumn(name="template_id")
//	@JsonIgnore
//	private Template template;

	//bi-directional many-to-one association to TreeStructure
	@ManyToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="tree_structure_id")
	@JsonIgnore
	private TreeStructure treeStructure;

	public Answer() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
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
	@JsonIgnore
	public TreeStructure getTreeStructure(){  
        return this.treeStructure;  
    }  
	@JsonProperty(access=Access.READ_WRITE) 
    public void setTreeStructure(TreeStructure treeStructure){  
        this.treeStructure = treeStructure;  
    } 
//	@JsonIgnore
//	public Template getTemplate(){  
//        return this.template;  
//    }  
//	@JsonProperty(access=Access.READ_WRITE) 
//    public void setTemplate(Template template){  
//        this.template = template;  
//    } 

}