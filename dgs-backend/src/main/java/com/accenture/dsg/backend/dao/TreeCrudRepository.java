package com.accenture.dsg.backend.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.accenture.dsg.backend.model.TreeStructure;
@Component
public interface TreeCrudRepository extends CrudRepository<TreeStructure, Long>{

}
