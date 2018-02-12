package com.accenture.dsg.backend.utils;

import java.util.Set;

import com.accenture.dsg.backend.model.TreeStructure;

public class ResponseObjectWrapper {
	
	public ResponseObjectWrapper(){};
	
	private class CatTreeStructureType{
		private int id;
		private String description;
	}
	
	private class CatTemplateWrapper{
		private int id;
		private String templatename;
		private String markup;
	}
	
	private class TemplateWrapper{
		private int id;
		private CatTemplateWrapper catTemplate;
	}
	
	private class AnswerWrapper{
		private int id;
		private String description;
		private String image;
		private String title;
		private TemplateWrapper template;
	}
	
	private class QuestionWrapper{
		private int id;
		private String pageSubtitle;
		private String pageTitle;
	}
	
	private int id;
	private CatTreeStructureType catStructureTypeId;
	private Set<AnswerWrapper> answers;
	private Set<QuestionWrapper> questions;
	private Set<ResponseObjectWrapper> treeStructures;
	
	
	public Set<ResponseObjectWrapper> getTreeStructures() {
		return this.treeStructures;
	}

	public void setTreeStructures(Set<ResponseObjectWrapper> treeStructures) {
		this.treeStructures = treeStructures;
	}
}
