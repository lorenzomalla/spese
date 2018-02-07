package com.accenture.dsg.backend.model;

import java.io.Serializable;
import javax.persistence.*;


import java.util.List;


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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	private String description;

	//bi-directional many-to-one association to TreeStructure
	@OneToMany(mappedBy="catTreeStructureType")
	private List<TreeStructure> treeStructures;

	public CatTreeStructureType() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<TreeStructure> getTreeStructures() {
		return this.treeStructures;
	}

	public void setTreeStructures(List<TreeStructure> treeStructures) {
		this.treeStructures = treeStructures;
	}

	public TreeStructure addTreeStructure(TreeStructure treeStructure) {
		getTreeStructures().add(treeStructure);
		treeStructure.setCatTreeStructureType(this);

		return treeStructure;
	}

	public TreeStructure removeTreeStructure(TreeStructure treeStructure) {
		getTreeStructures().remove(treeStructure);
		treeStructure.setCatTreeStructureType(null);

		return treeStructure;
	}

}