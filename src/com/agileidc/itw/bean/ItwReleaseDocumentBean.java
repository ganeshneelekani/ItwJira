package com.agileidc.itw.bean;

import java.sql.Clob;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ITW_RELEASE_INFO")
public class ItwReleaseDocumentBean {
	private String title;
	private String sourceCodeVersion;
	private String envoirnmentEffected;
	private String buisnessImpact;
	private String objectImpacted;
	private String revisedBy;
	private String createdBy;
	
	//Ganesh Using
	private Integer releaseid;
	private Integer id;
	private String releaseDate;
	private String releaseName;
	private Integer langId;
	private String introduction;
	private String scope;
	private String systemRequirements;
	private String operatingsystemssupported;
	private String prerequisites;
	private String knownIssues;
	private String assumptionDependencies;
	private String defects; 
	private String specialInstructions;
	private String releaseProcessInsrtuctions;

	
	public Integer getLangId() {
		return langId;
	}

	public void setLangId(Integer langId) {
		this.langId = langId;
	}

	public String getReleaseName() {
		return releaseName;
	}

	public void setReleaseName(String releaseName) {
		this.releaseName = releaseName;
	}

	public String getSourceCodeVersion() {
		return sourceCodeVersion;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setSourceCodeVersion(String sourceCodeVersion) {
		this.sourceCodeVersion = sourceCodeVersion;
	}

	public String getEnvoirnmentEffected() {
		return envoirnmentEffected;
	}

	public void setEnvoirnmentEffected(String envoirnmentEffected) {
		this.envoirnmentEffected = envoirnmentEffected;
	}

	

	

	public String getBuisnessImpact() {
		return buisnessImpact;
	}

	public void setBuisnessImpact(String buisnessImpact) {
		this.buisnessImpact = buisnessImpact;
	}

	public String getObjectImpacted() {
		return objectImpacted;
	}

	public void setObjectImpacted(String objectImpacted) {
		this.objectImpacted = objectImpacted;
	}

	public String getRevisedBy() {
		return revisedBy;
	}

	public void setRevisedBy(String revisedBy) {
		this.revisedBy = revisedBy;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getReleaseid() {
		return releaseid;
	}

	public void setReleaseid(Integer releaseid) {
		this.releaseid = releaseid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getSystemRequirements() {
		return systemRequirements;
	}

	public void setSystemRequirements(String systemRequirements) {
		this.systemRequirements = systemRequirements;
	}

	public String getOperatingsystemssupported() {
		return operatingsystemssupported;
	}

	public void setOperatingsystemssupported(String operatingsystemssupported) {
		this.operatingsystemssupported = operatingsystemssupported;
	}

	public String getPrerequisites() {
		return prerequisites;
	}

	public void setPrerequisites(String prerequisites) {
		this.prerequisites = prerequisites;
	}

	public String getKnownIssues() {
		return knownIssues;
	}

	public void setKnownIssues(String knownIssues) {
		this.knownIssues = knownIssues;
	}

	public String getAssumptionDependencies() {
		return assumptionDependencies;
	}

	public void setAssumptionDependencies(String assumptionDependencies) {
		this.assumptionDependencies = assumptionDependencies;
	}

	public String getDefects() {
		return defects;
	}

	public void setDefects(String defects) {
		this.defects = defects;
	}

	public String getSpecialInstructions() {
		return specialInstructions;
	}

	public void setSpecialInstructions(String specialInstructions) {
		this.specialInstructions = specialInstructions;
	}

	public String getReleaseProcessInsrtuctions() {
		return releaseProcessInsrtuctions;
	}

	public void setReleaseProcessInsrtuctions(String releaseProcessInsrtuctions) {
		this.releaseProcessInsrtuctions = releaseProcessInsrtuctions;
	}

	
	
	
}
