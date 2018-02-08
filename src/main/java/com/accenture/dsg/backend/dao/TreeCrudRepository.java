package com.accenture.dsg.backend.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.accenture.dsg.backend.model.TreeStructure;

@Repository
public interface TreeCrudRepository extends JpaRepository<TreeStructure, Long>{

	List<TreeStructure> findByParentId(@Param("treeId") Long treeId);

	Optional<TreeStructure> findById(long id);

	void deleteById(long id);
}
