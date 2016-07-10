package com.agileidc.itw.bean;

import java.io.Serializable;
import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.agileidc.itw.bean.ItwLangTypesBean.Comparators;

@Entity
@Table(name="ITW_LangTypes")
public class ItwLangTypesBean implements Serializable{

	private static final long serialVersionUID = -723583058586873479L;
	
	private Integer id;
	
	private String langDesc;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLangDesc() {
		return langDesc;
	}

	public void setLangDesc(String langDesc) {
		this.langDesc = langDesc;
	}

	
	public int compareTo(ItwLangTypesBean o) {
		return Comparators.ID.compare(this, o);
	}
	public static class Comparators {

		public static Comparator<ItwLangTypesBean> ID = new Comparator<ItwLangTypesBean>() {

			public int compare(ItwLangTypesBean o1, ItwLangTypesBean o2) {
				return o2.id.compareTo(o1.id);
			}
		};
	}

}
