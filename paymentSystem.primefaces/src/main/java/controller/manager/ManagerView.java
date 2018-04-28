/**
 * 下午3:39:41
 * power
 */
package controller.manager;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

import common.Const;
import common.ServerResponse;
import pojo.Manager;
import pojo.ManagerDetails;

import service.IManagerService;
import service.impl.ManagerServiceImpl;
import vo.ManagerDetailsVo;
import vo.ManagerVo;

/**
 * 
 * @author cz 2018年4月28日下午3:39:41
 */
@ManagedBean(name = "managerView")
@SessionScoped
public class ManagerView {
	private ManagerVo managerVo;
	private List<ManagerDetailsVo> managerDetailsVoList;
	private ManagerDetailsVo managerDetailsVo;

	private IManagerService iManagerService = new ManagerServiceImpl();

	public ManagerView() {
		this.managerVo = new ManagerVo();
		this.managerDetailsVoList = Lists.newArrayList();
		this.managerDetailsVo = new ManagerDetailsVo();
	}

	public ManagerDetailsVo getManagerDetailsVo() {
		return managerDetailsVo;
	}

	public void setManagerDetailsVo(ManagerDetailsVo managerDetailsVo) {
		this.managerDetailsVo = managerDetailsVo;
	}

	public ManagerVo getManagerVo() {
		return managerVo;
	}

	public void setManagerVo(ManagerVo managerVo) {
		this.managerVo = managerVo;
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

	/**
	 * 给前端显示提供支持 暂时只识别微信，支付宝 0代表微信，1代表支付宝，非0非1，默认为支付宝 识别账户类型
	 * 
	 * @param accountType
	 * @return
	 * @author cz
	 * @time 2018年4月28日下午6:13:33
	 */
	private String readAccoutType(Integer accountType) {

		if (Const.PayPlatformEnum.ALIPAY.getCode() == accountType) {
			return Const.PayPlatformEnum.ALIPAY.getValue();
		}
		if (Const.PayPlatformEnum.WECHAT.getCode() == accountType) {
			return Const.PayPlatformEnum.WECHAT.getValue();
		}
		return Const.PayPlatformEnum.ALIPAY.getValue();
	}

	/**
	 * 给后端保存提供支持
	 * 
	 * @param accountType
	 * @return
	 * @author cz
	 * @time 2018年4月28日下午6:45:54
	 */
	private Integer writeAccountType(String accountType) {
		if (StringUtils.equals(accountType, Const.PayPlatformEnum.ALIPAY.getValue())) {
			// 支付宝
			return Const.PayPlatformEnum.ALIPAY.getCode();
		}
		if (StringUtils.equals(accountType, Const.PayPlatformEnum.WECHAT.getValue())) {
			// 微信
			return Const.PayPlatformEnum.WECHAT.getCode();
		}
		return Const.PayPlatformEnum.ALIPAY.getCode();
	}

	/**
	 * 给前端显示提供支持 0-未激活，1-激活
	 * 
	 * @param active
	 * @return
	 * @author cz
	 * @time 2018年4月28日下午6:22:57
	 */
	private String readActive(Integer active) {
		if (Const.ActiveStatus.ACTIVE.getCode() == active) {
			return Const.ActiveStatus.ACTIVE.getValue();
		} else {
			return Const.ActiveStatus.UNACTIVE.getValue();
		}
	}

	/**
	 * 给后端保存提供支持
	 * 
	 * @param active
	 * @return
	 * @author cz
	 * @time 2018年4月28日下午6:49:43
	 */
	private Integer writeActiveStatuts(String active) {
		if (StringUtils.equals(Const.ActiveStatus.ACTIVE.getValue(), active)) {
			return Const.ActiveStatus.ACTIVE.getCode();
		} else {
			return Const.ActiveStatus.UNACTIVE.getCode();
		}
	}

	public String listManagerView() {
		// 获得当前session中的当前用户
		Manager manager = (Manager) this.getCurrentSession().getAttribute(Const.CURRENT_MANAGER);
		// 进行判断
		if (manager == null) {
			ServerResponse.createByErrorMessage("用户未登录，无法获取详细信息");
			// 返回登陆界面
			return "/login?faces-redirect=true";
		} else {
			// 装填基本信息
			// 给managerVo装填值
			managerVo.setActive(this.readActive(manager.getActive()));
			managerVo.setCreateTime(manager.getCreateTime());
			managerVo.setEmail(manager.getEmail());
			managerVo.setLoginTime(manager.getLoginTime());
			managerVo.setManagerId(manager.getManagerId());
			managerVo.setManagerName(manager.getManagerName());
			managerVo.setUpdateTime(manager.getUpdateTime());

			// 调用iManagerService,获得详细信息，主要是支付账户
			ServerResponse<List<ManagerDetails>> response = iManagerService
					.listManagerDetailsByManagerId(manager.getManagerId());
			if (!response.isSuccess()) {
				//获取详细信息失败
				managerDetailsVoList = Lists.newArrayList();
			} else {
				//获取详细成功
				// 给managerDetailsVoList装填值
				//装填前先清空原来的
				managerDetailsVoList=Lists.newArrayList();
				List<ManagerDetails> managerDetailsList = response.getData();
				for (ManagerDetails managerDetails : managerDetailsList) {
					ManagerDetailsVo managerDetailsVo = new ManagerDetailsVo();
					managerDetailsVo.setAccountId(managerDetails.getAccountId());
					managerDetailsVo.setAccountType(this.readAccoutType(managerDetails.getAccountType()));
					managerDetailsVo.setActive(this.readActive(managerDetails.getActive()));
					managerDetailsVo.setCreateTime(managerDetails.getCreateTime());
					managerDetailsVo.setManagerId(managerDetails.getManagerId());
					managerDetailsVo.setUpdateTime(managerDetails.getUpdateTime());
					managerDetailsVoList.add(managerDetailsVo);
				}
			}

			return "/manager/r_home?faces-redirect=true";
		}

	}

	public String saveManagerDetails() {
		// 获得当前session中的当前用户
		Manager manager = (Manager) this.getCurrentSession().getAttribute(Const.CURRENT_MANAGER);
		// 进行判断
		if (manager == null) {
			ServerResponse.createByErrorMessage("用户未登录，无法完善详细信息");
			// 返回登陆界面
			return "/login?faces-redirect=true";
		} else {
			// 调用iUserService.获取详细信息
			ManagerDetails managerDetails = new ManagerDetails();
			// 给managerDetails装值
			managerDetails.setAccountId(this.managerDetailsVo.getAccountId());
			managerDetails.setAccountType(this.writeAccountType(this.managerDetailsVo.getAccountType()));
			managerDetails.setActive(this.writeActiveStatuts(this.managerDetailsVo.getActive()));
			managerDetails.setManagerId(manager.getManagerId());
			ServerResponse<ManagerDetails> response = iManagerService.saveManagerDetials(managerDetails);
			if (response.isSuccess()) {
				return "/manager/r_home?faces-redirect=true";
			} else {
				// 保存失败,跳转到保存页面
				return "/manager/addManagerDetails?faces-redirect=true";
			}
		}
	}

}
