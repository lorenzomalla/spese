package com.accenture.dsg.backend.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import com.accenture.dsg.backend.model.Question;

@Component
public interface QuestionCrudRepository extends CrudRepository<Question, Long>{

	List<Question> findAll();
	List<Question> findByTreeId(@Param("treeId") Long treeId);
}
