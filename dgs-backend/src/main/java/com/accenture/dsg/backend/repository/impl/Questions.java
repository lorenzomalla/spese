package com.accenture.dsg.backend.repository.impl;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Questions {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private int id;
	
	private String title;
	
	private String description;

	private String image;
	
	@ManyToOne
	@JoinColumn(name="id")
		private TreeStructure tree_structure_id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public TreeStructure getTreeStructureId() {
		return tree_structure_id;
	}

	public void setTreeStructureId(TreeStructure tree_structure_id) {
		this.tree_structure_id = tree_structure_id;
	}
}
