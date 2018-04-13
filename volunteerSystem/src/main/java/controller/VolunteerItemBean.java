/**
 * 下午2:08:22
 * power
 */
package controller;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


import dao.IVolunteerItemDao;

import dao.impl.IVolunteerItemDaoImpl;

import pojo.User;
import pojo.VolunteerItem;

/**
 * 
 * @author cz 2018年4月13日下午2:08:22
 */
public class VolunteerItemBean {
	// private String id;//志愿条目id
	// private String userId;//发起者id
	private String itemName;// 志愿条目的名字
	private String itemDetail;// 志愿条目的细节
	// private int count;//志愿条目的被帮助次数
	// private int status;//志愿条目的状态0-未批准,1-批准,2-下架
	// private Date createTime;//创建时间
	// private Date updateTime;//更新时间

	public VolunteerItemBean() {
		super();
	}

	public VolunteerItemBean(String itemName, String itemDetail) {
		super();
		this.itemName = itemName;
		this.itemDetail = itemDetail;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDetail() {
		return itemDetail;
	}

	public void setItemDetail(String itemDetail) {
		this.itemDetail = itemDetail;
	}

	/**
	 * 前台调用的发起一条志愿的方法
	 * 
	 * @return
	 * @author cz
	 * @time 2018年4月13日下午2:11:03
	 */
	public void addVolunteerItem() {
		VolunteerItem volunteerItem = new VolunteerItem();
		volunteerItem.setItemName(itemName);
		volunteerItem.setItemDetail(itemDetail);
		volunteerItem.setCreateTime(new Date());
		volunteerItem.setStatus(0);
		volunteerItem.setUpdateTime(new Date());
		volunteerItem.setCount(0);

		// 取出当前session中保存的当前用户
		System.out.println("读取当前session中当前的user");
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext ec = context.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(true);
		User user = (User) session.getAttribute("CurrentUser");
		System.out.println("读取session完成");
		
		volunteerItem.setUserId(user.getId());
		
		//写入VolunteerItem表
		IVolunteerItemDao ivolunteerItemDao = new IVolunteerItemDaoImpl();
		VolunteerItem volunteerItem_feedback = ivolunteerItemDao.saveOrUpdateVolunteerItem(volunteerItem);
		if(volunteerItem_feedback!=null) {
			FacesMessage message = new FacesMessage(volunteerItem_feedback.getItemName()+ "成功申请，等待管理员批复");
            FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
}
