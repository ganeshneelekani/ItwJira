package com.agileidc.itw.bean;

import java.util.Comparator;

public class ItwTweetBean implements Comparable<ItwTweetBean> {

	private Integer id;
	private Integer userid;

	private String username;
	private String filepath1;

	private String tweetmsg;
	private String daysagodisplay;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFilepath1() {
		return filepath1;
	}

	public void setFilepath1(String filepath1) {
		this.filepath1 = filepath1;
	}
	public String getTweetmsg() {
		return tweetmsg;
	}

	public void setTweetmsg(String tweetmsg) {
		this.tweetmsg = tweetmsg;
	}

	public String getDaysagodisplay() {
		return daysagodisplay;
	}

	public void setDaysagodisplay(String daysagodisplay) {
		this.daysagodisplay = daysagodisplay;
	}

	public int compareTo(ItwTweetBean o) {
		return Comparators.ID.compare(this, o);
	}

	public static class Comparators {

		public static Comparator<ItwTweetBean> ID = new Comparator<ItwTweetBean>() {

			public int compare(ItwTweetBean o1, ItwTweetBean o2) {
				return o2.id.compareTo(o1.id);
			}
		};
	}
}
