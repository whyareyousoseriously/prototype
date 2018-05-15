/**
 * 下午1:39:51
 * power
 */
package controller.manager;

import java.io.IOException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

import common.Const;
import common.ResponseCode;
import common.ServerResponse;

import pojo.Manager;
import pojo.UserOrder;
import service.IOrderService;
import service.impl.OrderServiceImpl;
import utils.VoUtil;
import vo.UserOrderVo;

/**
 * 
 * @author cz
 * 2018年5月15日下午1:39:51
 */
@ManagedBean(name="managerOrderView")
@SessionScoped
public class ManagerOrderView {
	private static final Logger logger = LoggerFactory.getLogger(ManagerOrderView.class);
	private IOrderService iOrderService;
	private String status;//0-已取消-10-未付款，20-已付款，40-已发货，50-交易成功，60-交易关闭
	private List<UserOrderVo> userOrderVoList;
	private UserOrderVo userOrderVo;
	public ManagerOrderView() {
		iOrderService = new OrderServiceImpl();
	}
	
	public UserOrderVo getUserOrderVo() {
		return userOrderVo;
	}

	public void setUserOrderVo(UserOrderVo userOrderVo) {
		this.userOrderVo = userOrderVo;
	}

	public String getStatus() {
		return status;
	}

	public List<UserOrderVo> getUserOrderVoList() {
		return userOrderVoList;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setUserOrderVoList(List<UserOrderVo> userOrderVoList) {
		this.userOrderVoList = userOrderVoList;
	}

	private HttpSession getCurrentSession() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext ec = context.getExternalContext();
		HttpSession httpSession = (HttpSession) ec.getSession(true);
		return httpSession;
	}
	public void listUserOrderVoByStatus() {
		//权限判断
		Manager manager = (Manager)this.getCurrentSession().getAttribute(Const.CURRENT_MANAGER);
		if(manager==null) {
			//填充错误信息
			ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
			//跳转登录界面
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()+"login.jsf");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		}
		//通过权限判断
		//调用Service层方法
		ServerResponse<List<UserOrder>> response = iOrderService.listUserOrderByStatus(VoUtil.readOrderStatus(status));
		if(response.isSuccess()) {
			//查询成功
			//开始转化前台Vo
			this.userOrderVoList = Lists.newArrayList();
			logger.info("获取" + status + "订单信息成功");
			ServerResponse.createBySuccessMessage("获取" + status + "订单信息成功");
			List<UserOrder> userOrderList = response.getData();
			for (UserOrder userOrder : userOrderList) {
				this.userOrderVoList.add(VoUtil.UserOrderToVo(userOrder));
			}
		}else {
			logger.error("获取" + status + "订单信息失败");
			ServerResponse.createByErrorMessage("获取" + status + "订单信息失败");
			this.userOrderVoList = Lists.newArrayList();
		}
	}
}
