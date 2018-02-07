package com.accenture.dsg.backend.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.accenture.dsg.backend.model.CatTemplate;
@Component
public interface CatTemplateCrudRepository extends CrudRepository<CatTemplate, Long>{

}
