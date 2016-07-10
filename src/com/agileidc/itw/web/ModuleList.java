package com.agileidc.itw.web;

import java.util.List;

import com.agileidc.itw.model.ItwModuleTree;

public class ModuleList {

	Integer id;
	String moduleName;
	List<ItwModuleTree> itwModuleTreeList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public List<ItwModuleTree> getItwModuleTreeList() {
		return itwModuleTreeList;
	}

	public void setItwModuleTreeList(List<ItwModuleTree> itwModuleTreeList) {
		this.itwModuleTreeList = itwModuleTreeList;
	}

}