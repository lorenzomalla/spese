package com.accenture.dsg.backend.utils;

import java.util.Set;

public class ResponseObjectWrapper {
	
	public class CatTreeStructureType{
		int id;
		String description;
		Set<ResponseObjectWrapper> treeStructure;
	}
	
	public class CatTemplateWrapper{
		int id;
		String templatename;
		String markup;
		Set<TemplateWrapper> templates;
	}
	
	public class TemplateWrapper{
		int id;
		CatTemplateWrapper catTemplate;
		Set<AnswerWrapper> answers;
	}
	
	public class AnswerWrapper{
		int id;
		String description;
		String image;
		String title;
		TemplateWrapper template;
		ResponseObjectWrapper treeStructure;
	}
	
	public class QuestionWrapper{
		int id;
		String pageSubtitle;
		String pageTitle;
		ResponseObjectWrapper treeStructure;
	}
	
	public int id;
	public CatTreeStructureType catStructureTypeId;
	public Set<AnswerWrapper> answers;
	public Set<QuestionWrapper> questions;
	public ResponseObjectWrapper treeStructure;
	public Set<ResponseObjectWrapper> treeStructures;
	
	
	public Set<ResponseObjectWrapper> getTreeStructures() {
		return this.treeStructures;
	}

	public void setTreeStructures(Set<ResponseObjectWrapper> treeStructures) {
		this.treeStructures = treeStructures;
	}
}