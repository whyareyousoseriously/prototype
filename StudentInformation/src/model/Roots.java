package model;

public class Roots {
	/*
	 * ��������Ա��
	 * @author cz
	 * 2017-12-26
	 * */
	private String rootID;
	private String password;
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
		return "Roots [rootID=" + rootID + ", password=" + password + "]";
	}
	
}
