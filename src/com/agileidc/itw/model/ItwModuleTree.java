package com.agileidc.itw.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ITW_MODULE_TREE")
public class ItwModuleTree implements Serializable {

	private static final long serialVersionUID = -723583058556873479L;

	@Id
	
	@Column(name = "ID")
	private String id;

	@Column(name = "NODE_NAME")
	private String nodeName;

	
	@Column(name = "MODULEID")
	private Integer moduleId;

	@Column(name = "LOCKED_STATUS")
	private String lockedStatus;

	@Column(name = "LOCKED_BY")
	private String lockedBy;

	@Column(name = "NODE_TYPE")
	private String nodeType;

	@Column(name = "PARENTID")
	private String parentId;
	
	@Column(name = "ACTUALROOT")
	private String actualRoot;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public String getLockedStatus() {
		return lockedStatus;
	}

	public void setLockedStatus(String lockedStatus) {
		this.lockedStatus = lockedStatus;
	}

	public String getLockedBy() {
		return lockedBy;
	}

	public void setLockedBy(String lockedBy) {
		this.lockedBy = lockedBy;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	public String getActualRoot() {
		return actualRoot;
	}

	public void setActualRoot(String actualRoot) {
		this.actualRoot = actualRoot;
	}
}
