package com.accenture.dsg.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accenture.dsg.backend.model.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

}