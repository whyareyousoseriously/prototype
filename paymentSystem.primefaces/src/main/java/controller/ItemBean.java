/**
 * 
 */
package controller;

import entity.User;

/**
 * @author cz
 *
 * 2018年3月9日下午7:30:29
 */
public class ItemBean {
	private int uid;
	private String payment_name;
	private String payment_statuts;
	private User user;
	public ItemBean() {
		super();
	}
	public ItemBean(int uid, String payment_name, String payment_statuts, User user) {
		super();
		this.uid = uid;
		this.payment_name = payment_name;
		this.payment_statuts = payment_statuts;
		this.user = user;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getPayment_name() {
		return payment_name;
	}
	public void setPayment_name(String payment_name) {
		this.payment_name = payment_name;
	}
	public String getPayment_statuts() {
		return payment_statuts;
	}
	public void setPayment_statuts(String payment_statuts) {
		this.payment_statuts = payment_statuts;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "ItemBean [uid=" + uid + ", payment_name=" + payment_name + ", payment_statuts=" + payment_statuts
				+ ", user=" + user + "]";
	}
	public void showItem() {
		/*
		 * 展示所有缴费项目
		 * */
		
	}
}
