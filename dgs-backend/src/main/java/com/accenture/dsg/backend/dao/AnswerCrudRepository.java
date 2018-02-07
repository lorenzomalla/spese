package com.accenture.dsg.backend.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import com.accenture.dsg.backend.model.Answer;

@Component
public interface AnswerCrudRepository extends CrudRepository<Answer, Long>{

	List<Answer> findAll();
	List<Answer> findByTreeId(@Param("treeId") Long treeId);
}
