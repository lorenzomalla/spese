package com.accenture.dsg.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accenture.dsg.backend.model.TreeStructure;

@Repository
public interface TreeStructureRepository extends JpaRepository<TreeStructure, Long> {

}