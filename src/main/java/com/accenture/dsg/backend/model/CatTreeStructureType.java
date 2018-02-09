package com.accenture.dsg.backend.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String description;

	//bi-directional many-to-one association to TreeStructure
	@OneToMany(mappedBy="treeStructure", cascade=CascadeType.ALL)
	private List<TreeStructure> treeStructures = new ArrayList<>();
	
	public CatTreeStructureType() {
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
	
	public List<TreeStructure> getTreeStructure() {
		return this.treeStructures;
	}

	public void setTreeStructure(List<TreeStructure> treeStructures) {
		this.treeStructures = treeStructures;
	}

	public TreeStructure addTreeStructure(TreeStructure treeStructure) {
		getTreeStructure().add(treeStructure);
		treeStructure.setCatStructureTypeId(this);

		return treeStructure;
	}

	public TreeStructure removeTreeStructure(TreeStructure treeStructure) {
		getTreeStructure().remove(treeStructure);
		treeStructure.setCatStructureTypeId(null);

		return treeStructure;
	}

}