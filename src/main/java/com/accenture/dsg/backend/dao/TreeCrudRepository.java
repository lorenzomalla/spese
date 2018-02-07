package com.accenture.dsg.backend.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import com.accenture.dsg.backend.model.TreeStructure;

@Component
public interface TreeCrudRepository extends CrudRepository<TreeStructure, Long>{

	List<TreeStructure> findAll();
	List<TreeStructure> findByParentId(@Param("treeId") Long treeId);
}
