package model;

public class Roots {
	/*
	 * 超级管理员类
	 * @author cz
	 * 2017-12-26
	 * */
	private int uid;
	private String rootID;
	private String password;
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getRootID() {
		return rootID;
	}
	public void setRootID(String rootID) {
		this.rootID = rootID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Roots [uid=" + uid + ", rootID=" + rootID + ", password=" + password + "]";
	}
	
}
