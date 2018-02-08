package com.accenture.dsg.backend.model;

import java.io.Serializable;
import javax.persistence.*;



/**
 * The persistent class for the tree_structure database table.
 * 
 */
@Entity	
@Table(name="tree_structure")
@NamedQueries({
	@NamedQuery(name="TreeStructure.findAll", query="SELECT t FROM TreeStructure t"),
	@NamedQuery(name="TreeStructure.findByParentId", query="SELECT t, a, q FROM TreeStructure t JOIN t.parent_id p JOIN t.answers a JOIN  t.questions q WHERE p.id = :treeId")
})
public class TreeStructure implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	//bi-directional many-to-one association to TreeStructure
	@ManyToOne
	@JoinColumn(name="parent_id")
	private TreeStructure parent_id;
	
	public TreeStructure() {
		super();
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public TreeStructure getParentId() {
		return this.parent_id;
	}

	public void setParentId(TreeStructure parent_id) {
		this.parent_id = parent_id;
	}

}