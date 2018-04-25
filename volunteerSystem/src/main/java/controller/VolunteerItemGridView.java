/**
 * 下午1:28:11
 * power
 */
package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpSession;

import org.primefaces.model.DefaultStreamedContent;

import dao.IDonationDao;
import dao.IFileDao;
import dao.IPermitionDao;
import dao.IVolunteerItemDao;

import dao.impl.IDonationDaoImpl;
import dao.impl.IFileDaoImpl;
import dao.impl.IPermitionDaoImpl;
import dao.impl.IVolunteerItemDaoImpl;

import pojo.Donation;
import pojo.FileStorage;
import pojo.Permition;
import pojo.Root;
import pojo.User;
import pojo.VolunteerItem;
import vo.VolunteerItemVo;

/**
 * 所有志愿条目的呈现
 * 
 * @author cz 2018年4月13日下午1:28:11
 */
public class VolunteerItemGridView {
	private List<VolunteerItemVo> volunteerItemsVo;
	private List<VolunteerItemVo> volunteerItemsVo_wait;
	private VolunteerItemVo selectedVolunteerItemVo;
	private String donationDetail;
	private List<VolunteerItemVo> personalVolunteerItemsVo;
	private List<VolunteerItemVo> personalVolunteerItemsVo_wait;
	private FileStorage fileStorage;

	public List<VolunteerItemVo> getPersonalVolunteerItemsVo_wait() {
		IVolunteerItemDao iVolunteerItemDao = new IVolunteerItemDaoImpl();
		IFileDao ifileDao = new IFileDaoImpl();
		// 取出seesion中保存的当前user用户
		System.out.println("取出seesion中保存的当前user用户");
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext ec = context.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(true);
		User user = (User) session.getAttribute("CurrentUser");
		System.out.println("session取出user完成");

		List<VolunteerItem> personalVolunteerItems_wait = iVolunteerItemDao.ListVolunteerItem(user.getId(), 0);

		// 遍历personalVolunteerItems_wait,赋值给personalVolunteerItemsVo_wait;
		personalVolunteerItemsVo_wait = new ArrayList<VolunteerItemVo>();
		// 判空
		if (personalVolunteerItems_wait != null) {
			for (VolunteerItem volunteerItem : personalVolunteerItems_wait) {
				VolunteerItemVo volunteerItemVo = new VolunteerItemVo();
				volunteerItemVo.setId(volunteerItem.getId());
				volunteerItemVo.setUserId(volunteerItem.getUserId());
				volunteerItemVo.setItemName(volunteerItem.getItemName());
				volunteerItemVo.setItemDetail(volunteerItem.getItemDetail());
				volunteerItemVo.setStatus(volunteerItem.getStatus());
				volunteerItemVo.setCount(volunteerItem.getCount());
				volunteerItemVo.setCreateTime(volunteerItem.getCreateTime());
				volunteerItemVo.setUpdateTime(volunteerItem.getUpdateTime());
				fileStorage = ifileDao.getFileByUserIdAndItemId(volunteerItem.getUserId(), volunteerItem.getId());
				volunteerItemVo.setFileName(fileStorage.getFileName());
				try {
					volunteerItemVo.setFileDetail(
							new DefaultStreamedContent(fileStorage.getFileDetails().getBinaryStream(), "image/jpeg"));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				personalVolunteerItemsVo_wait.add(volunteerItemVo);

			}
		}
		return personalVolunteerItemsVo_wait;
	}

	public void setPersonalVolunteerItemsVo_wait(List<VolunteerItemVo> personalVolunteerItemsVo_wait) {
		this.personalVolunteerItemsVo_wait = personalVolunteerItemsVo_wait;
	}

	public List<VolunteerItemVo> getPersonalVolunteerItemsVo() {
		IVolunteerItemDao iVolunteerItemDao = new IVolunteerItemDaoImpl();
		IFileDao ifileDao = new IFileDaoImpl();
		// 取出seesion中保存的当前user用户
		System.out.println("取出seesion中保存的当前user用户");
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext ec = context.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(true);
		User user = (User) session.getAttribute("CurrentUser");
		System.out.println("session取出user完成");

		List<VolunteerItem> personalVolunteerItems = iVolunteerItemDao.ListVolunteerItem(user.getId(), 1);

		// 装填personalVolunteerItemsVo

		personalVolunteerItemsVo = new ArrayList<VolunteerItemVo>();
		// 判空
		if (personalVolunteerItems != null) {
			for (VolunteerItem volunteerItem : personalVolunteerItems) {
				VolunteerItemVo volunteerItemVo = new VolunteerItemVo();
				volunteerItemVo.setId(volunteerItem.getId());
				volunteerItemVo.setUserId(volunteerItem.getUserId());
				volunteerItemVo.setItemName(volunteerItem.getItemName());
				volunteerItemVo.setItemDetail(volunteerItem.getItemDetail());
				volunteerItemVo.setStatus(volunteerItem.getStatus());
				volunteerItemVo.setCount(volunteerItem.getCount());
				volunteerItemVo.setCreateTime(volunteerItem.getCreateTime());
				volunteerItemVo.setUpdateTime(volunteerItem.getUpdateTime());
				fileStorage = ifileDao.getFileByUserIdAndItemId(volunteerItem.getUserId(), volunteerItem.getId());
				volunteerItemVo.setFileName(fileStorage.getFileName());
				if (volunteerItemVo != null)
					personalVolunteerItemsVo.add(volunteerItemVo);
			}

		}

		return personalVolunteerItemsVo;
	}

	public void setPersonalVolunteerItemsVo(List<VolunteerItemVo> personalVolunteerItemsVo) {
		this.personalVolunteerItemsVo = personalVolunteerItemsVo;
	}

	public List<VolunteerItemVo> getVolunteerItemsVo_wait() {
		IVolunteerItemDao iVolunteerItemDao = new IVolunteerItemDaoImpl();
		IFileDao ifileDao = new IFileDaoImpl();

		List<VolunteerItem> volunteerItems_wait = iVolunteerItemDao.ListIVolunteerItem(0);

		// 装填volunteerItemsVo_wait

		volunteerItemsVo_wait = new ArrayList<VolunteerItemVo>();
		// 判空
		if (volunteerItems_wait != null) {
			for (VolunteerItem volunteerItem : volunteerItems_wait) {
				VolunteerItemVo volunteerItemVo = new VolunteerItemVo();
				volunteerItemVo.setId(volunteerItem.getId());
				volunteerItemVo.setUserId(volunteerItem.getUserId());
				volunteerItemVo.setItemName(volunteerItem.getItemName());
				volunteerItemVo.setItemDetail(volunteerItem.getItemDetail());
				volunteerItemVo.setStatus(volunteerItem.getStatus());
				volunteerItemVo.setCount(volunteerItem.getCount());
				volunteerItemVo.setCreateTime(volunteerItem.getCreateTime());
				volunteerItemVo.setUpdateTime(volunteerItem.getUpdateTime());
				fileStorage = ifileDao.getFileByUserIdAndItemId(volunteerItem.getUserId(), volunteerItem.getId());
				volunteerItemVo.setFileName(fileStorage.getFileName());
				if (volunteerItemVo != null)
					volunteerItemsVo_wait.add(volunteerItemVo);
			}
		}

		return volunteerItemsVo_wait;
	}

	public void setVolunteerItemsVo_wait(List<VolunteerItemVo> volunteerItemsVo_wait) {
		this.volunteerItemsVo_wait = volunteerItemsVo_wait;
	}

	public List<VolunteerItemVo> getVolunteerItemsVo() {
		IVolunteerItemDao iVolunteerItemDao = new IVolunteerItemDaoImpl();
		IFileDao ifileDao = new IFileDaoImpl();

		List<VolunteerItem> volunteerItems = iVolunteerItemDao.ListIVolunteerItem(1);

		// 装填volunteerItemsVo

		volunteerItemsVo = new ArrayList<VolunteerItemVo>();
		// 判空
		if (volunteerItems != null) {
			for (VolunteerItem volunteerItem : volunteerItems) {
				VolunteerItemVo volunteerItemVo = new VolunteerItemVo();
				volunteerItemVo.setId(volunteerItem.getId());
				volunteerItemVo.setUserId(volunteerItem.getUserId());
				volunteerItemVo.setItemName(volunteerItem.getItemName());
				volunteerItemVo.setItemDetail(volunteerItem.getItemDetail());
				volunteerItemVo.setStatus(volunteerItem.getStatus());
				volunteerItemVo.setCount(volunteerItem.getCount());
				volunteerItemVo.setCreateTime(volunteerItem.getCreateTime());
				volunteerItemVo.setUpdateTime(volunteerItem.getUpdateTime());
				fileStorage = ifileDao.getFileByUserIdAndItemId(volunteerItem.getUserId(), volunteerItem.getId());
				volunteerItemVo.setFileName(fileStorage.getFileName());
				if (volunteerItemVo != null)
					volunteerItemsVo.add(volunteerItemVo);
			}
		}

		return volunteerItemsVo;
	}

	public void setVolunteerItemsVo(List<VolunteerItemVo> volunteerItemsVo) {
		this.volunteerItemsVo = volunteerItemsVo;
	}

	public VolunteerItemVo getSelectedVolunteerItemVo() {
		return selectedVolunteerItemVo;
	}

	public void setSelectedVolunteerItemVo(VolunteerItemVo selectedVolunteerItemVo) {
		this.selectedVolunteerItemVo = selectedVolunteerItemVo;
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
		// 修改VolunteerItemVo中status字段
		selectedVolunteerItemVo.setStatus(1);
		// 更新VolunteerItem表
		IVolunteerItemDao iVolunteerItemDao = new IVolunteerItemDaoImpl();
		// 装填selectedVolunteerItem
		VolunteerItem selectedVolunteerItem = new VolunteerItem();
		selectedVolunteerItem.setId(selectedVolunteerItemVo.getId());
		selectedVolunteerItem.setUserId(selectedVolunteerItemVo.getUserId());
		selectedVolunteerItem.setItemName(selectedVolunteerItemVo.getItemName());
		selectedVolunteerItem.setItemDetail(selectedVolunteerItemVo.getItemDetail());
		selectedVolunteerItem.setStatus(selectedVolunteerItemVo.getStatus());
		selectedVolunteerItem.setCount(selectedVolunteerItemVo.getCount());
		selectedVolunteerItem.setCreateTime(selectedVolunteerItemVo.getCreateTime());
		selectedVolunteerItem.setUpdateTime(selectedVolunteerItemVo.getUpdateTime());

		VolunteerItem volunteerItem_feedback = iVolunteerItemDao.saveOrUpdateVolunteerItem(selectedVolunteerItem);
		if (volunteerItem_feedback != null) {
			FacesMessage message = new FacesMessage(volunteerItem_feedback.getItemName() + "批准成功");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		// TODO 将数据库中所有存储的图片转换形式存储入本机路径下。

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
		// 修改VolunteerItemVo的count字段,使其加1
		selectedVolunteerItemVo.setCount(selectedVolunteerItemVo.getCount() + 1);
		// 装填selectedVolunteerItem
		VolunteerItem selectedVolunteerItem = new VolunteerItem();
		selectedVolunteerItem.setId(selectedVolunteerItemVo.getId());
		selectedVolunteerItem.setUserId(selectedVolunteerItemVo.getUserId());
		selectedVolunteerItem.setItemName(selectedVolunteerItemVo.getItemName());
		selectedVolunteerItem.setItemDetail(selectedVolunteerItemVo.getItemDetail());
		selectedVolunteerItem.setStatus(selectedVolunteerItemVo.getStatus());
		selectedVolunteerItem.setCount(selectedVolunteerItemVo.getCount());
		selectedVolunteerItem.setCreateTime(selectedVolunteerItemVo.getCreateTime());
		selectedVolunteerItem.setUpdateTime(selectedVolunteerItemVo.getUpdateTime());
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
