package com.agileidc.itw.bean;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.agileidc.itw.web.IdList;

public class ItwUserBean implements Comparable<ItwUserBean> {

	private Integer id;

	private String firstname;

	private String lastname;
	private Integer genderid;
	private String gender;

	private Blob icon;

	private String emailid;

	private Integer primaryroleid;
	private String primaryroleiddisplay;

	private Integer typeid;
	private String typeiddisplay;

	private Integer companyid;
	private String companyiddisplay;

	private String password;
	private String confirmpassword;

	private String userid;
	private Integer iconid;
	private Integer langid;
	private Integer enabled;
	
	private Integer failedcount;
	
	private String authority;
	private Integer onetimepass;
	
	

	public Integer getOnetimepass() {
		return onetimepass;
	}

	public void setOnetimepass(Integer onetimepass) {
		this.onetimepass = onetimepass;
	}

	public Integer getFailedcount() {
		return failedcount;
	}

	public void setFailedcount(Integer failedcount) {
		this.failedcount = failedcount;
	}

	public Integer getLangid() {
		return langid;
	}

	public void setLangid(Integer langid) {
		this.langid = langid;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	Map<String, String> itwTypeidListofValues = new LinkedHashMap<String, String>();
	Map<String, String> itwPrimaryroleidListofValues = new LinkedHashMap<String, String>();
	List<IdList> itwPrimaryroleidList = new ArrayList<IdList>();
	List<IdList> itwTypeidList = new ArrayList<IdList>();
	List<IdList> itwCompanyidList = new ArrayList<IdList>();
	List<IdList> genderList = new ArrayList<IdList>();
	Map<String, String> itwCompanyidListofValues = new LinkedHashMap<String, String>();

	public int compareTo(ItwUserBean o) {
		return Comparators.ID.compare(this, o);
	}

	public static class Comparators {

		public static Comparator<ItwUserBean> ID = new Comparator<ItwUserBean>() {

			public int compare(ItwUserBean o1, ItwUserBean o2) {
				return o2.id.compareTo(o1.id);
			}
		};
	}

	public Integer getGenderid() {
		return genderid;
	}

	public void setGenderid(Integer genderid) {
		this.genderid = genderid;
	}

	public Integer getIconid() {
		return iconid;
	}

	public void setIconid(Integer iconid) {
		this.iconid = iconid;
	}

	public List<IdList> getItwPrimaryroleidList() {

		return itwPrimaryroleidList;
	}

	public void setItwPrimaryroleidList(List<IdList> itwPrimaryroleidList) {
		this.itwPrimaryroleidList = itwPrimaryroleidList;
	}

	public List<IdList> getGenderList() {

		return genderList;
	}

	public void setGenderList(List<IdList> genderList) {
		this.genderList = genderList;
	}

	public List<IdList> getItwTypeidList() {

		return itwTypeidList;
	}

	public void setItwTypeidList(List<IdList> itwTypeidList) {
		this.itwTypeidList = itwTypeidList;
	}

	public List<IdList> getItwCompanyidList() {

		return itwCompanyidList;
	}

	public void setItwCompanyidList(List<IdList> itwCompanyidList) {
		this.itwCompanyidList = itwCompanyidList;
	}

	public Map<String, String> getItwTypeidListofValues() {
		return itwTypeidListofValues;
	}

	public void setItwCompanyidListofValues(
			Map<String, String> itwCompanyidListofValues) {
		this.itwCompanyidListofValues = itwCompanyidListofValues;
	}

	public Map<String, String> getItwCompanyidListofValues() {
		return itwCompanyidListofValues;
	}

	public void setItwTypeidListofValues(
			Map<String, String> itwTypeidListofValues) {
		this.itwTypeidListofValues = itwTypeidListofValues;
	}

	public Map<String, String> getItwPrimaryroleidListofValues() {
		return itwPrimaryroleidListofValues;
	}

	public void setItwPrimaryroleidListofValues(
			Map<String, String> itwPrimaryroleidListofValues) {
		this.itwPrimaryroleidListofValues = itwPrimaryroleidListofValues;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getCompanyiddisplay() {
		return this.companyiddisplay;
	}

	public void setCompanyiddisplay(String companyiddisplay) {
		this.companyiddisplay = companyiddisplay;
	}

	public String getTypeiddisplay() {
		return this.typeiddisplay;
	}

	public void setTypeiddisplay(String typeiddisplay) {
		this.typeiddisplay = typeiddisplay;
	}

	public String getPrimaryroleiddisplay() {
		return this.primaryroleiddisplay;
	}

	public void setPrimaryroleiddisplay(String primaryroleiddisplay) {
		this.primaryroleiddisplay = primaryroleiddisplay;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Blob getIcon() {
		return icon;
	}

	public void setIcon(Blob icon) {
		this.icon = icon;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public Integer getPrimaryroleid() {
		return primaryroleid;
	}

	public void setPrimaryroleid(Integer primaryroleid) {
		this.primaryroleid = primaryroleid;
	}

	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public Integer getCompanyid() {
		return companyid;
	}

	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
