package com.accenture.dsg.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Answers {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private int id;
	
	private String page_title;
	
	private String page_subtitle;
	
	//@ManyToOne
	//@JoinColumn(name="id")
		private TreeStructure tree_structure_id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPageTitle() {
		return page_title;
	}

	public void setPageTitle(String page_title) {
		this.page_title = page_title;
	}
	
	public String getPageSubtitle() {
		return page_subtitle;
	}

	public void setPageSubtitle(String page_subtitle) {
		this.page_subtitle = page_subtitle;
	}
	
	public TreeStructure getTreeStructureId() {
		return tree_structure_id;
	}

	public void setTreeStructureId(TreeStructure tree_structure_id) {
		this.tree_structure_id = tree_structure_id;
	}
}
