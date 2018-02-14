package com.accenture.dsg.backend.dao;

import java.util.List;

import com.accenture.dsg.backend.model.TreeStructure;

public interface TreeStructureDao{
	
	public List<TreeStructure> getAllLista();
	public TreeStructure getFindById(int id);
	public TreeStructure getTree();
	public void persistTreeStructure(TreeStructure tree);

}
