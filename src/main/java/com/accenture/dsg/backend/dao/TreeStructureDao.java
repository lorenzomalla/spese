package com.accenture.dsg.backend.dao;

import java.util.List;

import com.accenture.dsg.backend.model.TreeStructure;
import com.accenture.dsg.backend.utils.ResponseObjectWrapper;

public interface TreeStructureDao{
	
	public List<TreeStructure> getAllLista();
	public ResponseObjectWrapper getFindById(int id);
	public TreeStructure getTree();
	public void persistTreeStructure(TreeStructure tree);

}
