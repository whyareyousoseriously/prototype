/**
 * 下午1:28:11
 * power
 */
package controller;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpSession;

import dao.IDonationDao;
import dao.IPermitionDao;
import dao.IVolunteerItemDao;
import dao.impl.IDonationDaoImpl;
import dao.impl.IPermitionDaoImpl;
import dao.impl.IVolunteerItemDaoImpl;
import pojo.Donation;
import pojo.Permition;
import pojo.Root;
import pojo.User;
import pojo.VolunteerItem;

/**
 * 所有志愿条目的呈现
 * 
 * @author cz 2018年4月13日下午1:28:11
 */
public class VolunteerItemGridView {
	private List<VolunteerItem> volunteerItems;
	private List<VolunteerItem> volunteerItems_wait;
	private VolunteerItem selectedVolunteerItem;
	private String donationDetail;
	private List<VolunteerItem> personalVolunteerItems;
	private List<VolunteerItem> personalVolunteerItems_wait;
	

	public List<VolunteerItem> getPersonalVolunteerItems_wait() {
		IVolunteerItemDao iVolunteerItemDao = new IVolunteerItemDaoImpl();
		// 取出seesion中保存的当前user用户
		System.out.println("取出seesion中保存的当前user用户");
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext ec = context.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(true);
		User user = (User) session.getAttribute("CurrentUser");
		System.out.println("session取出user完成");
		personalVolunteerItems_wait = iVolunteerItemDao.ListVolunteerItem(user.getId(), 0);
		return personalVolunteerItems_wait;
	}

	public void setPersonalVolunteerItems_wait(List<VolunteerItem> personalVolunteerItems_wait) {
		this.personalVolunteerItems_wait = personalVolunteerItems_wait;
	}

	public List<VolunteerItem> getPersonalVolunteerItems() {
		IVolunteerItemDao iVolunteerItemDao = new IVolunteerItemDaoImpl();
		// 取出seesion中保存的当前user用户
		System.out.println("取出seesion中保存的当前user用户");
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext ec = context.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(true);
		User user = (User) session.getAttribute("CurrentUser");
		System.out.println("session取出user完成");
		personalVolunteerItems = iVolunteerItemDao.ListVolunteerItem(user.getId(), 1);
		return personalVolunteerItems;
	}

	public void setPersonalVolunteerItems(List<VolunteerItem> personalVolunteerItems) {
		this.personalVolunteerItems = personalVolunteerItems;
	}

	public List<VolunteerItem> getVolunteerItems_wait() {
		IVolunteerItemDao iVolunteerItemDao = new IVolunteerItemDaoImpl();
		volunteerItems_wait = iVolunteerItemDao.ListIVolunteerItem(0);
		return volunteerItems_wait;
	}

	public void setVolunteerItems_wait(List<VolunteerItem> volunteerItems_wait) {
		this.volunteerItems_wait = volunteerItems_wait;
	}

	public List<VolunteerItem> getVolunteerItems() {
		IVolunteerItemDao iVolunteerItemDao = new IVolunteerItemDaoImpl();
		volunteerItems = iVolunteerItemDao.ListIVolunteerItem(1);
		return volunteerItems;
	}

	public void setVolunteerItems(List<VolunteerItem> volunteerItems) {
		this.volunteerItems = volunteerItems;
	}

	public VolunteerItem getSelectedVolunteerItem() {
		return selectedVolunteerItem;
	}

	public void setSelectedVolunteerItem(VolunteerItem selectedVolunteerItem) {
		this.selectedVolunteerItem = selectedVolunteerItem;
	}

	public String getDonationDetail() {
		return donationDetail;
	}

	public void setDonationDetail(String donationDetail) {
		this.donationDetail = donationDetail;
	}

	/**
	 * 这个是root用户所拥有的操作
	 * 
	 * @author cz
	 * @time 2018年4月13日下午5:40:52
	 */
	public void doPermition() {
		// 取出seesion中保存的当前root用户
		System.out.println("取出seesion中保存的当前root用户");
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext ec = context.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(true);
		Root root = (Root) session.getAttribute("CurrentRoot");
		System.out.println("session取出root完成");
		// 批准求助申请
		// 修改VolunteerItem表中status字段
		selectedVolunteerItem.setStatus(1);
		// 更新VolunteerItem表
		IVolunteerItemDao iVolunteerItemDao = new IVolunteerItemDaoImpl();
		VolunteerItem volunteerItem_feedback = iVolunteerItemDao.saveOrUpdateVolunteerItem(selectedVolunteerItem);
		if (volunteerItem_feedback != null) {
			FacesMessage message = new FacesMessage(volunteerItem_feedback.getItemName() + "批准成功");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		// 同时保存Permition表
		Permition permition = new Permition();
		permition.setItemId(selectedVolunteerItem.getId());
		permition.setRootId(root.getId());
		permition.setCreateTime(new Date());
		permition.setUpdateTime(new Date());
		IPermitionDao iPermitionDao = new IPermitionDaoImpl();
		Permition permition_feedback = iPermitionDao.saveOrUpdatePermition(permition);
		if (permition_feedback != null) {
			FacesMessage message = new FacesMessage(volunteerItem_feedback.getItemName() + "添加批准表成功");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

	}

	/**
	 * 这个是一般用户拥有的操作
	 * 
	 * @author cz
	 * @time 2018年4月13日下午5:41:18
	 */
	public void doDonation() {
		// 取出seesion中保存的当前user用户
		System.out.println("取出seesion中保存的当前user用户");
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext ec = context.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(true);
		User user = (User) session.getAttribute("CurrentUser");
		System.out.println("session取出user完成");
		// 修改VolunteerItem表中的count字段,使其加1
		selectedVolunteerItem.setCount(selectedVolunteerItem.getCount() + 1);
		// 更新VolunteerItem表
		IVolunteerItemDao iVolunteerItemDao = new IVolunteerItemDaoImpl();
		VolunteerItem volunteerItem_feedback = iVolunteerItemDao.saveOrUpdateVolunteerItem(selectedVolunteerItem);
		if (volunteerItem_feedback != null) {
			FacesMessage message = new FacesMessage(volunteerItem_feedback.getItemName() + "捐赠成功");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

		// 同时保存相关信息保存在Donation表
		Donation donation = new Donation();
		donation.setItemId(selectedVolunteerItem.getId());
		donation.setUserId(user.getId());
		donation.setCount(selectedVolunteerItem.getCount());
		donation.setDetail(donationDetail);
		donation.setCreateTime(new Date());
		donation.setUpdateTime(new Date());
		IDonationDao iDonationDao = new IDonationDaoImpl();
		Donation donation_feedback = iDonationDao.saveOrUpdateDonation(donation);
		if (donation_feedback != null) {
			FacesMessage message = new FacesMessage("捐献信息添加捐献表成功");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

	}

}
