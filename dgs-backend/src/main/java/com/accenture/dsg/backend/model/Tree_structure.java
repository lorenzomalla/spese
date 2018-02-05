package com.accenture.dsg.backend.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.mapping.Collection;



@Entity
public class Tree_structure {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
		private Tree_structure parent_id;
	
	@ManyToOne
		private Cat_tree_structure_types cat_tree_structure_type_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Tree_structure getParent_id() {
		return parent_id;
	}

	public void setParent_id(Tree_structure parent_id) {
		this.parent_id = parent_id;
	}
	
	public Cat_tree_structure_types getCat_tree_structure_type_id() {
		return cat_tree_structure_type_id;
	}

	public void setCat_tree_structure_type_id(Cat_tree_structure_types cat_tree_structure_type_id) {
		this.cat_tree_structure_type_id = cat_tree_structure_type_id;
	}
	
}
