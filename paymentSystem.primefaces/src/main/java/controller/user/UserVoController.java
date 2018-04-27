/**
 * 下午6:53:46
 * power
 */
package controller.user;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import common.Const;
import common.ServerResponse;
import pojo.User;
import pojo.UserDetails;
import service.IUserService;
import service.impl.UserServiceImpl;
import vo.UserVo;

/**
 * UserVoController
 * 用户视图控制类
 * @author cz
 * 2018年4月26日下午6:53:46
 */
public class UserVoController {
	private UserVo userVo;
	
	private IUserService iUserService = new UserServiceImpl();

	/**
	 * 获得用户的详细信息
	 * @return
	 * @author cz
	 * @time 2018年4月26日下午6:55:21
	 */
	public UserVo getUserVo() {
		//获得当前session中的当前用户
		User user = (User) this.getCurrentSession().getAttribute(Const.CURRENT_USER);
		//进行判断
		if(user==null) {
			ServerResponse.createByErrorMessage("用户未登录，无法获取详细信息");
		}else {
			//调用iUserService,获得详细信息
			UserDetails userDetails = iUserService.listUserDetailsByUserId(user.getUserId());
			if(userDetails==null) {
				userDetails = new UserDetails();
			}
			userVo=this.Combine(user,userDetails);
		}
		return userVo;
	}

	/**
	 * @param user
	 * @param userDetails
	 * @return
	 * @author cz
	 * @time 2018年4月26日下午7:12:39
	 */
	private UserVo Combine(User user, UserDetails userDetails) {
		//结合user和userDetails成为UserVo
		UserVo userVo = new UserVo();
		userVo.setActive(Const.ACTIVE==user.getActive()?"已激活":"未激活");
		userVo.setAddress(userDetails.getAddress());
		userVo.setEmail(user.getEmail());
		userVo.setIdNumber(userDetails.getIdNumber());
		userVo.setLoginTime(user.getLoginTime());
		userVo.setOccupation(userDetails.getOccupation());
		userVo.setPhone(userDetails.getPhone());
		userVo.setRealName(userDetails.getRealName());
		userVo.setSex(Const.FEMALE==userDetails.getSex()?"女":"男");
		userVo.setUsername(user.getUsername());
		return userVo;
	}

	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}
	
	private HttpSession getCurrentSession() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext ec = context.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(true);
		return session;
	}
	
}
