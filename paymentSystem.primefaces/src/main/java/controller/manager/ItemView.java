/**
 * 下午10:45:39
 * power
 */
package controller.manager;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.google.common.collect.Lists;

import common.Const;
import common.ServerResponse;
import pojo.Item;
import pojo.Manager;
import pojo.ManagerDetails;
import service.IItemService;
import service.IManagerService;
import service.impl.ItemServiceImpl;
import service.impl.ManagerServiceImpl;
import utils.DateTimeUtil;
import utils.VoUtil;
import vo.ItemVo;
import vo.ManagerDetailsVo;

/**
 * 
 * @author cz 2018年4月28日下午10:45:39
 */
@ManagedBean(name = "itemView")
@SessionScoped
public class ItemView {
	private ItemVo itemVo;
	private List<ItemVo> itemVoList;// 支付条目的简单概述
	private List<ManagerDetailsVo> managerDetailsVoList; // 发布上线时，需要的
	private IItemService iItemService;
	private IManagerService iManagerService;

	public ItemView() {
		this.itemVo = new ItemVo();
		this.iItemService = new ItemServiceImpl();
		this.iManagerService = new ManagerServiceImpl();
		this.itemVoList = Lists.newArrayList();
		this.managerDetailsVoList = Lists.newArrayList();
	}

	public List<ItemVo> getItemVoList() {
		return itemVoList;
	}

	public void setItemVoList(List<ItemVo> itemVoList) {
		this.itemVoList = itemVoList;
	}

	public ItemVo getItemVo() {
		return itemVo;
	}

	public void setItemVo(ItemVo itemVo) {
		this.itemVo = itemVo;
	}

	public List<ManagerDetailsVo> getManagerDetailsVoList() {
		return managerDetailsVoList;
	}

	public void setManagerDetailsVoList(List<ManagerDetailsVo> managerDetailsVoList) {
		this.managerDetailsVoList = managerDetailsVoList;
	}

	private HttpSession getCurrentSession() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext ec = context.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(true);
		return session;
	}

	public void saveOneItem() {
		// 获得当前session中的当前用户
		Manager manager = (Manager) this.getCurrentSession().getAttribute(Const.CURRENT_MANAGER);
		// 进行判断
		if (manager == null) {
			ServerResponse.createByErrorMessage("用户未登录，无法添加支付条目");
			// 返回登陆界面
			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()
								+ "login.jsf");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// 封装Item
			Item item = new Item();
			item.setItemName(this.itemVo.getItemName());
			item.setManagerId(manager.getManagerId());
			item.setPrice(new BigDecimal(itemVo.getPrice()));
			item.setActive(Const.ItemStatus.UNACTIVE.getCode());// 设置为未发布状态
			item.setAccountId(null);
			item.setCreateTime(new Date());
			item.setUpdateTime(new Date());
			// 保存item
			ServerResponse<Item> response = iItemService.saveItem(item);
			if (response.isSuccess()) {
				// 保存成功,返回

			} else {
				// 保存失败,跳转到保存页面

			}
			this.listItemVo();
		}
	}

	public void updateOneItem() {
		// 获得当前session中的当前用户
		Manager manager = (Manager) this.getCurrentSession().getAttribute(Const.CURRENT_MANAGER);
		// 进行判断
		if (manager == null) {
			ServerResponse.createByErrorMessage("用户未登录，无法更新支付条目");
			// 返回登陆界面
			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()
								+ "login.jsf");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// 封装Item
			Item item = new Item();
			item.setItemId(this.itemVo.getItemId());
			item.setItemName(this.itemVo.getItemName());
			item.setManagerId(manager.getManagerId());
			item.setPrice(new BigDecimal(itemVo.getPrice()));
			item.setCreateTime(DateTimeUtil.strToDate(this.itemVo.getCreateTime()));
			item.setUpdateTime(DateTimeUtil.strToDate(this.itemVo.getUpdateTime()));
			// 设置支付账号,设置激活状态
			item.setAccountId(this.itemVo.getAccountId());
			item.setActive(VoUtil.readItemStatus(this.itemVo.getActive()));

			// 更新item
			ServerResponse<Item> response = iItemService.updateItem(item);
			if (response.isSuccess()) {
				// 保存成功,返回

			} else {
				// 保存失败,跳转到保存页面

			}
			this.listItemVo();
		}
	}

	public void deleteOneItem() {
		// 获得当前session中的当前用户
		Manager manager = (Manager) this.getCurrentSession().getAttribute(Const.CURRENT_MANAGER);
		// 进行判断
		if (manager == null) {
			ServerResponse.createByErrorMessage("用户未登录，无法删除支付条目");
			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()
								+ "login.jsf");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// 封装Item
			Item item = new Item();
			item.setItemId(this.itemVo.getItemId());
			/*
			 * item.setItemName(this.itemVo.getItemName());
			 * item.setManagerId(manager.getManagerId()); item.setPrice(new
			 * BigDecimal(itemVo.getPrice()));
			 * item.setActive(VoUtil.readItemActive(itemVo.getActive()));
			 * item.setCreateTime(DateTimeUtil.strToDate(this.itemVo.getCreateTime()));
			 * item.setUpdateTime(DateTimeUtil.strToDate(this.itemVo.getUpdateTime()));
			 */
			// 保存item
			ServerResponse<Item> response = iItemService.deleteItemByItemId(item);
			if (response.isSuccess()) {
				// 保存成功,返回

			} else {
				// 保存失败,跳转到保存页面

			}
			this.listItemVo();
		}
	}

	public void onLineOneItem() {
		// 获得当前session中的当前用户
		Manager manager = (Manager) this.getCurrentSession().getAttribute(Const.CURRENT_MANAGER);
		// 进行判断
		if (manager == null) {
			ServerResponse.createByErrorMessage("用户未登录，无法删除支付条目");
			// 返回登陆界面
			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()
								+ "login.jsf");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			this.listManagerDetailsVo();
			// 封装item
			// 封装Item
			Item item = new Item();
			item.setItemId(this.itemVo.getItemId());
			item.setItemName(this.itemVo.getItemName());
			item.setManagerId(manager.getManagerId());
			item.setPrice(new BigDecimal(itemVo.getPrice()));
			item.setCreateTime(DateTimeUtil.strToDate(this.itemVo.getCreateTime()));
			item.setUpdateTime(DateTimeUtil.strToDate(this.itemVo.getUpdateTime()));
			// 设置支付账号,设置激活状态
			item.setAccountId(this.itemVo.getAccountId());
			item.setActive(Const.ItemStatus.ACTIVE.getCode());
			// 上线item
			iItemService.onLineItem(item);
		}
		this.listItemVo();
	}

	public void outLineOneItem() {
		// 获得当前session中的当前用户
		Manager manager = (Manager) this.getCurrentSession().getAttribute(Const.CURRENT_MANAGER);
		// 进行判断
		if (manager == null) {
			ServerResponse.createByErrorMessage("用户未登录，无法删除支付条目");
			// 返回登陆界面
			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()
								+ "login.jsf");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			this.listManagerDetailsVo();
			// 封装item
			// 封装Item
			Item item = new Item();
			item.setItemId(this.itemVo.getItemId());
			item.setItemName(this.itemVo.getItemName());
			item.setManagerId(manager.getManagerId());
			item.setPrice(new BigDecimal(itemVo.getPrice()));
			item.setCreateTime(DateTimeUtil.strToDate(this.itemVo.getCreateTime()));
			item.setUpdateTime(DateTimeUtil.strToDate(this.itemVo.getUpdateTime()));
			// 设置支付账号,设置激活状态
			item.setAccountId(null);
			item.setActive(Const.ItemStatus.UNACTIVE.getCode());
			// 上线item
			iItemService.outLineItem(item);
		}
		this.listItemVo();
	}

	public void listManagerDetailsVo() {
		// 获得当前session中的当前用户
		Manager manager = (Manager) this.getCurrentSession().getAttribute(Const.CURRENT_MANAGER);
		// 进行判断
		if (manager == null) {
			ServerResponse.createByErrorMessage("用户未登录，无法删除支付条目");
			// 返回登陆界面
			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()
								+ "login.jsf");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// 给managerDetailsVoList装填合适的值，已激活的账号信息
			// 根据managerId和active获得满足条件的集合
			// 调用iManagerService,获得详细信息，主要是支付账户
			ServerResponse<List<ManagerDetails>> response = iManagerService.listManagerDetailsByManagerIdAndActive(
					manager.getManagerId(), Const.AccountStatus.ACTIVE.getCode());
			// 给managerDetailsVoList装填值
			// 装填前先清空原来的
			managerDetailsVoList = Lists.newArrayList();

			List<ManagerDetails> managerDetailsList = response.getData();
			for (ManagerDetails managerDetails : managerDetailsList) {
				ManagerDetailsVo managerDetailsVo = new ManagerDetailsVo();
				managerDetailsVo.setAccountId(managerDetails.getAccountId());
				managerDetailsVo.setAccountType(VoUtil.readAccountType(managerDetails.getAccountType()));
				managerDetailsVo.setActive(VoUtil.readAccountStatus(managerDetails.getActive()));
				managerDetailsVo.setCreateTime(managerDetails.getCreateTime());
				managerDetailsVo.setManagerId(managerDetails.getManagerId());
				managerDetailsVo.setUpdateTime(managerDetails.getUpdateTime());
				managerDetailsVoList.add(managerDetailsVo);
			}
		}
	}

	public void listItemVo() {
		// 获得当前session中的当前用户
		Manager manager = (Manager) this.getCurrentSession().getAttribute(Const.CURRENT_MANAGER);
		// 进行判断
		if (manager == null) {
			ServerResponse.createByErrorMessage("用户未登录，无法获取详细信息");
			// 返回登陆界面
			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()
								+ "login.jsf");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// 调用iItemService获取信息
			ServerResponse<List<Item>> response = iItemService.listItemByManagerId(manager.getManagerId());
			if (response.isSuccess()) {
				// 获取成功
				// 装填itemVoList
				// 先清空原来的
				this.itemVoList = Lists.newArrayList();
				List<Item> itemList = response.getData();
				for (Item item : itemList) {
					ItemVo itemVoTemp = new ItemVo();
					itemVoTemp.setItemId(item.getItemId());
					itemVoTemp.setItemName(item.getItemName());
					itemVoTemp.setManagerId(item.getManagerId());
					itemVoTemp.setPrice(item.getPrice().toString());
					itemVoTemp.setActive(VoUtil.writeItemStatus(item.getActive()));
					itemVoTemp.setCreateTime(DateTimeUtil.dateToStr(item.getCreateTime()));
					itemVoTemp.setUpdateTime(DateTimeUtil.dateToStr(item.getUpdateTime()));
					// 设置到账账户信息
					itemVoTemp.setAccountId(item.getAccountId());
					ManagerDetails managerDetails = iManagerService.selectManagerDetailsByAccountId(item.getAccountId())
							.getData();
					if (managerDetails == null) {
						// 判空，防止出错
						itemVoTemp.setAccountType(null);
					} else {
						itemVoTemp.setAccountType(VoUtil.readAccountType(managerDetails.getAccountType()));
					}

					this.itemVoList.add(itemVoTemp);
				}
			} else {
				// 获取失败
				this.itemVoList = Lists.newArrayList();
			}

		}
	}

}
