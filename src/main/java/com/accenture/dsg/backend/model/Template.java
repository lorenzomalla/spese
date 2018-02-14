package com.accenture.dsg.backend.model;

import java.io.Serializable;

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

	private String templatename;
	
	@Column(columnDefinition="TEXT")
	private String markup;

	//bi-directional many-to-one association to Answer
	@ManyToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="tree_structure_id")
	@JsonIgnore
	private TreeStructure treeStructure;
	
	public Template() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getTemplateName() {
		return this.templatename;
	}

	public void setTemplateName(String templatename) {
		this.templatename = templatename;
	}
	
	public String getMarkup() {
		return this.markup;
	}

	public void setMarkup(String markup) {
		this.markup = markup;
	}

	@JsonIgnore
	public TreeStructure getTreeStructure(){  
        return this.treeStructure;  
    }  
	@JsonProperty(access=Access.READ_WRITE) 
    public void setTreeStructure(TreeStructure treeStructure){  
        this.treeStructure = treeStructure;  
    } 
}