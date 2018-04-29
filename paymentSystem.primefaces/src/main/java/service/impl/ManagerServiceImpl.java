/**
 * 下午2:53:59
 * power
 */
package service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.Const;
import common.ServerResponse;
import common.TokenCache;
import dao.IManagerDao;
import dao.impl.ManagerDaoImpl;
import pojo.Manager;
import pojo.ManagerDetails;

import service.IManagerService;
import utils.MD5Util;
import utils.MailUtil;

/**
 * 
 * @author cz 2018年4月28日下午2:53:59
 */
public class ManagerServiceImpl implements IManagerService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	// 调用iManagerDao
	IManagerDao iManagerDao = new ManagerDaoImpl();

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.IManagerService#login(java.lang.String, java.lang.String)
	 * 
	 * @author cz
	 * 
	 * @time 2018年4月28日下午2:55:44
	 */
	@Override
	public ServerResponse<Manager> login(String managerName, String managerPassword) {
		// 调用Dao层的方法查询数据库当前用户是否存在
		Manager result = iManagerDao.checkManagerName(managerName);
		if (result == null) {
			return ServerResponse.createByErrorMessage("用户名不存在");
		}
		// 用户存在，则将密码MD5加密，并调用Dao层方法进行查询登陆
		String md5Password = MD5Util.MD5EncodeUtf8(managerPassword);
		Manager manager = iManagerDao.selectLogin(managerName, md5Password);
		if (manager == null) {
			return ServerResponse.createByErrorMessage("密码错误");
		}
		// 登陆成功，修改最后一次登陆时间,并更新数据库
		manager.setLoginTime(new Date());
		iManagerDao.saveOrUpdate(manager);
		// 将用户的密码移除，返回
		manager.setManagerPassword(org.apache.commons.lang3.StringUtils.EMPTY);
		return ServerResponse.createBySuccess("登录成功", manager);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.IManagerService#register(pojo.Manager)
	 * 
	 * @author cz
	 * 
	 * @time 2018年4月28日下午2:55:44
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public ServerResponse<String> register(Manager manager) {
		ServerResponse validResponse = this.checkExist(manager.getManagerName(), Const.MANAGERNAME);
		if (validResponse.isSuccess()) {
			return ServerResponse.createByErrorMessage("用户名已存在，无法注册");
		}
		validResponse = this.checkExist(manager.getEmail(), Const.EMAIL);
		if (validResponse.isSuccess()) {
			return ServerResponse.createByErrorMessage("邮箱已存在，无法注册");
		}

		// MD5加密
		manager.setManagerPassword(MD5Util.MD5EncodeUtf8(manager.getManagerPassword()));
		manager.setActive(0);// 置激活状态为未激活。
		manager.setMailCode(MailUtil.getUUID());// 设置邮件激活码
		// 发送激活邮件
		MailUtil.sendMail(manager.getEmail(), manager.getMailCode());
		ServerResponse.createBySuccessMessage("注册成功,激活邮件已经发送到注册邮箱" + manager.getEmail() + "请尽快激活登陆");
		Date currentTime = new Date();// 取当前时间
		manager.setCreateTime(currentTime); // 设置首次注册时间
		manager.setUpdateTime(currentTime); // 设置更新时间

		int resultCount = iManagerDao.saveOrUpdate(manager);
		if (resultCount == 0) {
			return ServerResponse.createByErrorMessage("注册失败");
		}
		return ServerResponse.createBySuccessMessage(manager.getManagerName() + "注册成功");
	}

	/**
	 * @param managerName
	 * @param managername2
	 * @return
	 * @author cz
	 * @time 2018年4月28日下午3:06:03
	 */
	@SuppressWarnings("rawtypes")
	private ServerResponse checkExist(String str, String type) {
		// 判断type是否为空
		if (StringUtils.isNotBlank(type)) {
			// 开始校验
			if (Const.MANAGERNAME.equals(type)) {
				// 校验用户名
				Manager result = iManagerDao.checkManagerName(str);
				if (result != null) {
					return ServerResponse.createBySuccess(result);
				}
				return ServerResponse.createByError();
			}
			if (Const.EMAIL.equals(type)) {
				// 校验email
				Manager result = iManagerDao.checkEmail(str);
				if (result != null) {
					return ServerResponse.createBySuccess(result);
				}
				return ServerResponse.createByError();
			}
			if(Const.ACCOUNTID.equals(type)) {
				//校验支付账户
				ManagerDetails managerDetails = iManagerDao.checkAccountId(str);
				if(managerDetails!=null) {
					return ServerResponse.createBySuccess(managerDetails);
				}
				return ServerResponse.createByError();
			}
			return ServerResponse.createByErrorMessage(type + "验证类型不在此列");

		} else {
			return ServerResponse.createByErrorMessage("参数错误");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.IManagerService#sendCheckCodeEmail(java.lang.String,
	 * java.lang.String)
	 * 
	 * @author cz
	 * 
	 * @time 2018年4月28日下午2:55:44
	 */
	@Override
	public ServerResponse<String> sendCheckCodeEmail(String managerName, String email) {
		Manager result = iManagerDao.checkManagerNameAndEmail(managerName, email);
		if (result != null) {
			// 说明email是此用户的
			// 给邮箱验证码
			String checkCode = UUID.randomUUID().toString();
			// 发送邮件
			if (!MailUtil.sendCheckCode(email, checkCode)) {
				return ServerResponse.createByErrorMessage("验证码邮件发送失败");
			}
			// 将邮箱验证码放在在本地cache中并设置有效期
			TokenCache.setKey(TokenCache.TOKEN_PREFIX + managerName, checkCode);
			return ServerResponse.createBySuccessMessage("验证码邮件发送成功");
		}
		return ServerResponse.createByErrorMessage("发送验证码失败");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.IManagerService#forgetResetPassword(java.lang.String,
	 * java.lang.String, java.lang.String)
	 * 
	 * @author cz
	 * 
	 * @time 2018年4月28日下午2:55:44
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ServerResponse<String> forgetResetPassword(String managerName, String managerPasswordNew,
			String restPasswordCheckCode) {
		if (StringUtils.isBlank(restPasswordCheckCode)) {
			return ServerResponse.createByErrorMessage("参数错误，邮箱验证码不能为空");
		}
		// 校验managerName
		ServerResponse<Manager> validResponser = this.checkExist(managerName, Const.MANAGERNAME);
		if (!validResponser.isSuccess()) {
			// 用户不存在
			return ServerResponse.createByErrorMessage("用户不存在");
		}
		// 从cache中获取token
		String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX + managerName);
		// 校验token
		if (StringUtils.isBlank(token)) {
			return ServerResponse.createByErrorMessage("邮箱验证码无效，或者已过期");
		}
		if (StringUtils.equals(token, restPasswordCheckCode)) {
			// 比对成功，开始修改密码
			String md5Password = MD5Util.MD5EncodeUtf8(managerPasswordNew);
			Manager manager = validResponser.getData();
			manager.setManagerPassword(md5Password);
			int resultCount = iManagerDao.saveOrUpdate(manager);
			if (resultCount > 0) {
				return ServerResponse.createBySuccessMessage("修改密码成功");
			}
		} else {
			return ServerResponse.createByErrorMessage("邮箱验证码错误,请重新获取修改密码的邮箱验证码");
		}
		return ServerResponse.createByErrorMessage("密码修改失败");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.IManagerService#resetPasswordDirect(java.lang.String,
	 * java.lang.String)
	 * 
	 * @author cz
	 * 
	 * @time 2018年4月28日下午2:55:44
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ServerResponse<String> resetPasswordDirect(String managerName, String managerPasswordNew) {
		// 校验managerName
		ServerResponse<Manager> validResponser = this.checkExist(managerName, Const.MANAGERNAME);
		if (!validResponser.isSuccess()) {
			// 用户不存在
			return ServerResponse.createByErrorMessage("用户不存在");
		}
		String md5Password = MD5Util.MD5EncodeUtf8(managerPasswordNew);
		Manager manager = validResponser.getData();
		manager.setManagerPassword(md5Password);
		int resultCount = iManagerDao.saveOrUpdate(manager);
		if (resultCount > 0) {
			return ServerResponse.createBySuccessMessage("修改密码成功");
		}
		return ServerResponse.createByErrorMessage("密码修改失败");
	}

	/* (non-Javadoc)
	 * @see service.IManagerService#listManagerDetailsByManagerId(java.lang.String)
	 * @author cz
	 * @time 2018年4月28日下午5:58:39
	 */
	@Override
	public ServerResponse<List<ManagerDetails>> listManagerDetailsByManagerId(String managerId) {
		
		List<ManagerDetails> managerDetailsList = iManagerDao.listManagerDetailsByManagerId(managerId);
		if(managerDetailsList.isEmpty()) {
			//获取详细失败没有存储详细信息
			return ServerResponse.createByErrorMessage("获取支付账户信息失败没有存储支付账户");
		}else {
			return ServerResponse.createBySuccess("获取支付账户信息成功", managerDetailsList);
		}
	}

	/* (non-Javadoc)
	 * @see service.IManagerService#saveManagerDetials(pojo.ManagerDetails)
	 * @author cz
	 * @time 2018年4月28日下午6:53:11
	 */
	@Override
	public ServerResponse<ManagerDetails> saveManagerDetials(ManagerDetails managerDetails) {
		//校验支付账户，查看是否在数据库中存在
		ManagerDetails checkExist = iManagerDao.checkExistByAccountIdAndManagerId(managerDetails.getAccountId(), managerDetails.getManagerId());
		ManagerDetails managerDetails_feedback;
		if(checkExist!=null) {
			//该支付账户和从属管理者，数据库中已存在
			//执行更新操作
			logger.info("支付账户的更新");
			managerDetails.setUpdateTime(new Date());
			managerDetails_feedback = iManagerDao.saveOrUpdateManagerDetails(managerDetails);
		}else {
			//该支付账户和丛书管理者，数据库中不存在
			//执行创建操作
			logger.info("支付账户的创建");
			managerDetails.setCreateTime(new Date());
			managerDetails.setUpdateTime(new Date());
			managerDetails_feedback = iManagerDao.saveOrUpdateManagerDetails(managerDetails);
		}
		if(managerDetails_feedback==null) {
			//保存失败
			return ServerResponse.createByErrorMessage("支付账户信息保存或更新失败");
		}else {
			return ServerResponse.createBySuccess("支付账户信息保存或更新成功", managerDetails_feedback);
		}
	}

	/* (non-Javadoc)
	 * @see service.IManagerService#listManagerDetailsByManagerIdAndActive(java.lang.String, int)
	 * @author cz
	 * @time 2018年4月29日上午11:56:49
	 */
	@Override
	public ServerResponse<List<ManagerDetails>> listManagerDetailsByManagerIdAndActive(String managerId, int code) {
		List<ManagerDetails> managerDetailsList = iManagerDao.listManagerDetailsByManagerIdAndActive(managerId,code);
		if(managerDetailsList.isEmpty()) {
			//获取信息失败
			return ServerResponse.createByErrorMessage("获取支付账户信息失败没有存储支付账户或没有已经激活的账户");
		}else {
			return ServerResponse.createBySuccess("获取支付账户信息成功", managerDetailsList);
		}
	}

	/* (non-Javadoc)
	 * @see service.IManagerService#listManagerDetailsByAccountId(java.lang.String)
	 * @author cz
	 * @time 2018年4月29日下午1:32:45
	 */
	@Override
	public ServerResponse<ManagerDetails> selectManagerDetailsByAccountId(String accountId) {
		ManagerDetails managerDetailsList = iManagerDao.selectManagerDetailsByAccountId(accountId);
		if(managerDetailsList==null) {
			//获取信息失败
			//这个只是查找账户是不是存在，用在给itemView中的itemVo赋值,集合遍历，所以不用重复提示错误信息
			return ServerResponse.createByError();
		}else {
			return ServerResponse.createBySuccess(managerDetailsList);
		}
	}

}
