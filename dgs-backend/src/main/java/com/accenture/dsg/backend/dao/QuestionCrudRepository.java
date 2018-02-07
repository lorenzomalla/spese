package com.accenture.dsg.backend.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.accenture.dsg.backend.model.Question;

@Component
public interface QuestionCrudRepository extends CrudRepository<Question, Long>{

}
