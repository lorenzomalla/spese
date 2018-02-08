package com.accenture.dsg.backend.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.accenture.dsg.backend.model.TreeStructure;

@Repository
public interface TreeCrudRepository extends JpaRepository<TreeStructure, Long>{

	List<TreeStructure> findByParentId(@Param("treeId") Long treeId);
}
