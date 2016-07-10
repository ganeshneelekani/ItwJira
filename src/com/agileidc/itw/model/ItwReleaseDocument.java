package com.agileidc.itw.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.agileidc.itw.bean.ItwTaskBugBean;
@Entity
@Table(name="ITW_RELEASE_INFO")
public class ItwReleaseDocument {

	private static final long serialVersionUID = -723583058589973622L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "SOURCECODEVERSION")
	private String sourceCodeVersion;

	@Column(name = "ENVOIRNMENTEFFECTED")
	private String envoirnmentEffected;

	@Column(name = "BUISNESSIMPACT ")
	private String buisnessImpact;

	@Column(name = "OBJECTIMPACTED")
	private String objectImpacted;
	
	@Column(name = "TITLE ")
	private String title;

	@Column(name = "REVISEDBY")
	private String revisedBy;

	@Column(name = "CREATEDBY")
	private String createdBy;

	@Column(name = "RELEASEID")
	private Integer releaseid;

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Column(name = "RELEASEDATE")
	private Date releaseDate;

	@Column(name = "LANGID")
	private Integer langId;
	
	@Column(name = "RELEASENAME")
	private String releaseName;
	
	@Column(name = "INTRODUCTION")
	private String introduction;
	
	@Column(name = "SCOPE")
	private String scope;
	
	@Column(name = "SYSTEMREQUIREMENTS")
	private String systemRequirements;
	
	@Column(name = "OPERATINGSYSTEMSSUPPORTED")
	private String operatingsystemssupported;
	
	@Column(name = "PREREQUISITES")
	private String prerequisites;
	
	@Column(name = "KNOWNISSUES")
	private String knownIssues;
	
	@Column(name = "ASSUMPTIONDEPENDENCIES")
	private String assumptionDependencies;
	
	@Column(name = "DEFECTS")
	private String defects; 
	
	@Column(name = "SPECIALINSTRUCTIONS")
	private String specialInstructions;
	
	@Column(name = "RELEASEPROCESSINSRTUCTIONS")
	private String releaseProcessInsrtuctions;
	

	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReleaseName() {
		return releaseName;
	}

	public void setReleaseName(String releaseName) {
		this.releaseName = releaseName;
	}

	public Integer getLangId() {
		return langId;
	}

	public void setLangId(Integer langId) {
		this.langId = langId;
	}

	public String getSourceCodeVersion() {
		return sourceCodeVersion;
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

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
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
