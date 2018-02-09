package com.accenture.dsg.backend.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cat_tree_structure_types database table.
 * 
 */
@Entity
@Table(name="cat_tree_structure_types")
@NamedQuery(name="CatTreeStructureType.findAll", query="SELECT c FROM CatTreeStructureType c")
public class CatTreeStructureType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String description;

	public CatTreeStructureType() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}