package com.accenture.dsg.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Answers {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private int id;
	
	private String page_title;
	
	private String page_subtitle;
	
	@ManyToOne
		private Tree_structure tree_structure_id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPage_title() {
		return page_title;
	}

	public void setPage_title(String page_title) {
		this.page_title = page_title;
	}
	
	public String getPage_subtitle() {
		return page_subtitle;
	}

	public void setPage_subtitle(String page_subtitle) {
		this.page_subtitle = page_subtitle;
	}
	
	public Tree_structure getTree_structure_id() {
		return tree_structure_id;
	}

	public void setTree_structure_id(Tree_structure tree_structure_id) {
		this.tree_structure_id = tree_structure_id;
	}
}
