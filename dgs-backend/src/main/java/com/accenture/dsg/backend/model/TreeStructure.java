package com.accenture.dsg.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class TreeStructure {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
		private TreeStructure parent_id;
	
	@ManyToOne
		private CatTreeStructureTypes cat_tree_structure_type_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TreeStructure getParentId() {
		return parent_id;
	}

	public void setParentId(TreeStructure parent_id) {
		this.parent_id = parent_id;
	}
	
	public CatTreeStructureTypes getCatTreeStructureTypeId() {
		return cat_tree_structure_type_id;
	}

	public void setCatTreeStructureTypeId(CatTreeStructureTypes cat_tree_structure_type_id) {
		this.cat_tree_structure_type_id = cat_tree_structure_type_id;
	}
	
}
