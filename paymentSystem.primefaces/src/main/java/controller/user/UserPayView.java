/**
 * 下午4:44:38
 * power
 */
package controller.user;

import java.io.IOException;
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


import pojo.PayInfo;
import pojo.User;
import service.IItemService;

import service.IPayInfoService;
import service.impl.ItemServiceImpl;

import service.impl.PayInfoServiceImpl;
import utils.DateTimeUtil;
import utils.VoUtil;
import vo.ItemVo;
import vo.PayInfoVo;

/**
 * 用户支付
 * 
 * @author cz 2018年4月29日下午4:44:38
 */
@ManagedBean(name="userPayView")
@SessionScoped
public class UserPayView {

	private ItemVo itemVo;

	private List<ItemVo> itemVoList;// 支付条目的简单概述

	private List<PayInfoVo> payInfoVoList;// 支付信息清单
	
	private PayInfoVo payInfoVo;

	private IPayInfoService iPayInfoService;

	private IItemService iItemService;

	

	public UserPayView() {
		
		this.iItemService = new ItemServiceImpl();
		this.itemVo = new ItemVo();
		this.payInfoVo = new PayInfoVo();
		this.itemVoList = Lists.newArrayList();
		this.payInfoVoList = Lists.newArrayList();
		this.iPayInfoService = new PayInfoServiceImpl();
	}
	
	

	public PayInfoVo getPayInfoVo() {
		return payInfoVo;
	}



	public void setPayInfoVo(PayInfoVo payInfoVo) {
		this.payInfoVo = payInfoVo;
	}



	public List<PayInfoVo> getPayInfoVoList() {
		return payInfoVoList;
	}

	public void setPayInfoVoList(List<PayInfoVo> payInfoVoList) {
		this.payInfoVoList = payInfoVoList;
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

	private HttpSession getCurrentSession() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext ec = context.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(true);
		return session;
	}

	public void listItemVo() {
		// 获得当前session中的当前用户
		User user = (User) this.getCurrentSession().getAttribute(Const.CURRENT_USER);
		// 进行判断
		if (user == null) {
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
			ServerResponse<List<Item>> response = iItemService.listItemByStatus(Const.ItemStatus.ACTIVE.getCode());
			//获取payInfo表中用户已经支付的条目的集合
			ServerResponse<List<PayInfo>> response2 = iPayInfoService.listPayInfoByUserIdAndStatus(user.getUserId(),Const.PayStatus.PAID.getCode());
			if (response.isSuccess()) {
				if(response2.isSuccess()) {
					//将用户还未支付的条目加入集合
					//先清空原来的
					this.itemVoList =Lists.newArrayList();
					List<Item> itemList = response.getData();
					List<PayInfo> payInfoList = response2.getData();
					//将payInfoList中的itemId放入一个暂时集合中
					List<String> itemIdList = Lists.newArrayList();
					for(PayInfo payInfo:payInfoList) {
						itemIdList.add(payInfo.getItemId());
					}
					for(Item item :itemList) {
						//判断该条目在不在payInfoList
						if(!itemIdList.contains(item.getItemId())) {
							this.itemVoList.add(VoUtil.ItemToItemVo(item));
						}
					}
				}else {
					// 用户一个条目也没有支付
					// 装填itemVoList
					// 先清空原来的
					this.itemVoList = Lists.newArrayList();
					List<Item> itemList = response.getData();
					for (Item item : itemList) {
						//将条目加入集合
						this.itemVoList.add(VoUtil.ItemToItemVo(item));
					}
				}
				
			} else {
				// 获取失败
				this.itemVoList = Lists.newArrayList();
			}

		}
	}

	public void listPayInfoVo() {
		// 获得当前session中的当前用户
		User user = (User) this.getCurrentSession().getAttribute(Const.CURRENT_USER);
		// 进行判断
		if (user == null) {
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
		}else {
			//调用iPayInfoService获取满足条件的信息
			ServerResponse<List<PayInfo>> response = iPayInfoService.listPayInfoByUserId(user.getUserId());
			if(response.isSuccess()) {
				//获取满足条件的信息成功
				//开始装填
				//装填前，先清空
				this.payInfoVoList =Lists.newArrayList();
				List<PayInfo> payInfoList = response.getData();
				for(PayInfo payInfo:payInfoList) {
					PayInfoVo payInfoVo = new PayInfoVo();
					payInfoVo.setCreateTime(DateTimeUtil.dateToStr(payInfo.getCreateTime()));
					payInfoVo.setItemId(payInfo.getItemId());
					//通过itemId，找到item的详细信息
					payInfoVo.setOwnItemVo(VoUtil.ItemToItemVo(iItemService.selectItemByItemId(payInfo.getItemId()).getData()));
					payInfoVo.setPayInfoDetailsId(payInfo.getPayInfoDetailsId());
					payInfoVo.setPayTime(DateTimeUtil.dateToStr(payInfo.getPayTime()));
					payInfoVo.setQrCode(payInfo.getQrCode());
					payInfoVo.setStatus(VoUtil.writePayStatus(payInfo.getStatus()));
					payInfoVo.setUpdateTime(DateTimeUtil.dateToStr(payInfo.getUpdateTime()));
					payInfoVo.setUserId(payInfo.getUserId());
					payInfoVoList.add(payInfoVo);
				}
			}else {
				//获取失败
				this.payInfoVoList = Lists.newArrayList();
			}
		}
	}

	public void pay() {
		// 获得当前session中的当前用户
		User user = (User) this.getCurrentSession().getAttribute(Const.CURRENT_USER);
		// 进行判断
		if (user == null) {
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
			// 创建一个payInfo,并保存
			PayInfo payInfo = new PayInfo();
			payInfo.setItemId(this.itemVo.getItemId());
			payInfo.setCreateTime(new Date());
			payInfo.setPayInfoDetailsId(null);
			payInfo.setPayTime(new Date());
			//暂时存默认的url
			payInfo.setQrCode("QrCode url");
			payInfo.setStatus(Const.PayStatus.PAID.getCode());
			payInfo.setUpdateTime(new Date());
			payInfo.setUserId(user.getUserId());

			ServerResponse<PayInfo> response = iPayInfoService.saveOnePayInfo(payInfo);
			if (response.isSuccess()) {
				ServerResponse.createBySuccessMessage("支付成功");
			} else {
				ServerResponse.createByErrorMessage("支付失败");
			}
			this.listPayInfoVo();
		}
		

	}
}