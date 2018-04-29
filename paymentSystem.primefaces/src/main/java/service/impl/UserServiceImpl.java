/**
 * 下午5:44:58
 * power
 */
package service.impl;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.Const;
import common.ServerResponse;
import common.TokenCache;
import dao.IUserDao;
import dao.impl.UserDaoImpl;
import pojo.User;
import pojo.UserDetails;
import service.IUserService;
import utils.MD5Util;
import utils.MailUtil;

/**
 * 
 * @author cz 2018年4月25日下午5:44:58
 */
public class UserServiceImpl implements IUserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	// 调用iUserDao方法
	IUserDao iUserDao = new UserDaoImpl();

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.IUserService#login(java.lang.String, java.lang.String)
	 * 
	 * @author cz
	 * 
	 * @time 2018年4月25日下午7:35:34
	 */
	public ServerResponse<User> login(String username, String password) {
		// 调用Dao层的方法查询数据库当前用户是否存在
		User result = iUserDao.checkUsername(username);
		if (result == null) {
			return ServerResponse.createByErrorMessage("用户名不存在");
		}
		// 用户存在，则将密码MD5加密，并调用Dao层方法进行查询登陆
		String md5Password = MD5Util.MD5EncodeUtf8(password);
		User user = iUserDao.selectLogin(username, md5Password);
		if (user == null) {
			return ServerResponse.createByErrorMessage("密码错误");
		}
		// 登陆成功，修改最后一次登陆时间,并更新数据库
		user.setLoginTime(new Date());
		iUserDao.saveOrUpdate(user);
		// 将用户的密码移除，返回
		user.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
		return ServerResponse.createBySuccess("登录成功", user);
	}

	@SuppressWarnings({ "rawtypes" })
	public ServerResponse<String> register(User user) {
		ServerResponse validResponse = this.checkExist(user.getUsername(), Const.USERNAME);
		if (validResponse.isSuccess()) {
			return ServerResponse.createByErrorMessage("用户名已存在，无法注册");
		}
		validResponse = this.checkExist(user.getEmail(), Const.EMAIL);
		if (validResponse.isSuccess()) {
			return ServerResponse.createByErrorMessage("邮箱已存在，无法注册");
		}

		// MD5加密
		user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
		user.setActive(0);// 置激活状态为未激活。
		user.setMailCode(MailUtil.getUUID());// 设置邮件激活码
		// 发送激活邮件
		MailUtil.sendMail(user.getEmail(), user.getMailCode());
		ServerResponse.createBySuccessMessage("注册成功,激活邮件已经发送到注册邮箱" + user.getEmail() + "请尽快激活登陆");
		Date currentTime = new Date();// 取当前时间
		user.setCreateTime(currentTime); // 设置首次注册时间
		user.setUpdateTime(currentTime); // 设置更新时间

		int resultCount = iUserDao.saveOrUpdate(user);
		if (resultCount == 0) {
			return ServerResponse.createByErrorMessage("注册失败");
		}
		return ServerResponse.createBySuccessMessage(user.getUsername() + "注册成功");

	}

	public ServerResponse<String> active(String mailCode) {
		// 查询数据库中是否有该激活码的用户
		User user = iUserDao.findByMailCode(mailCode);
		if (user != null) {
			// 数据库有该激活用户，开始进行激活操作
			logger.info("开始激活操作");
			user.setActive(Const.ACTIVE);
			user.setMailCode(null);// 清除mailCode;
			user.setUpdateTime(new Date());// 更新更新时间
			iUserDao.saveOrUpdate(user);// 写入数据库
			return ServerResponse.createBySuccess("激活成功");
		} else {
			logger.info("未找到待激活用户，激活失败");
			return ServerResponse.createByErrorMessage("激活失败");
		}
	}

	public ServerResponse<User> checkExist(String str, String type) {

		// 判断type是否为空
		if (StringUtils.isNotBlank(type)) {
			// 开始校验
			if (Const.USERNAME.equals(type)) {
				// 校验用户名
				User result = iUserDao.checkUsername(str);
				if (result != null) {
					return ServerResponse.createBySuccess(result);
				}
				return ServerResponse.createByError();
			}
			if (Const.EMAIL.equals(type)) {
				// 校验email
				User result = iUserDao.checkEmail(str);
				if (result != null) {
					return ServerResponse.createBySuccess(result);
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
	 * @see service.IUserService#forgetRestPassword(java.lang.String,
	 * java.lang.String, java.lang.String)
	 * 
	 * @author cz
	 * 
	 * @time 2018年4月26日下午12:27:49
	 */
	@Override
	public ServerResponse<String> forgetResetPassword(String username, String passwordNew,
			String restPasswordCheckCode) {
		if (StringUtils.isBlank(restPasswordCheckCode)) {
			return ServerResponse.createByErrorMessage("参数错误，邮箱验证码不能为空");
		}
		// 校验username
		ServerResponse<User> validResponser = this.checkExist(username, Const.USERNAME);
		if (!validResponser.isSuccess()) {
			// 用户不存在
			return ServerResponse.createByErrorMessage("用户不存在");
		}
		// 从cache中获取token
		String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX + username);
		// 校验token
		if (StringUtils.isBlank(token)) {
			return ServerResponse.createByErrorMessage("邮箱验证码无效，或者已过期");
		}
		if (StringUtils.equals(token, restPasswordCheckCode)) {
			// 比对成功，开始修改密码
			String md5Password = MD5Util.MD5EncodeUtf8(passwordNew);
			User user = validResponser.getData();
			user.setPassword(md5Password);
			user.setUpdateTime(new Date());//设置更新时间
			int resultCount = iUserDao.saveOrUpdate(user);
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
	 * @see service.IUserService#sendEmail(java.lang.String, java.lang.String)
	 * 
	 * @author cz
	 * 
	 * @time 2018年4月26日下午4:52:56
	 */
	@Override
	public ServerResponse<String> sendCheckCodeEmail(String username, String email) {
		User result = iUserDao.checkUsernameAndEmail(username, email);
		if (result != null) {
			// 说明email是此用户的
			// 给邮箱验证码
			String checkCode = UUID.randomUUID().toString();
			// 发送邮件
			if (!MailUtil.sendCheckCode(email, checkCode)) {
				return ServerResponse.createByErrorMessage("验证码邮件发送失败");
			}
			// 将邮箱验证码放在在本地cache中并设置有效期
			TokenCache.setKey(TokenCache.TOKEN_PREFIX + username, checkCode);
			return ServerResponse.createBySuccessMessage("验证码邮件发送成功");
		}
		return ServerResponse.createByErrorMessage("发送验证码失败");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.IUserService#listUserDetailsByUserId(java.lang.String)
	 * 
	 * @author cz
	 * 
	 * @time 2018年4月26日下午7:04:45
	 */
	@Override
	public UserDetails listUserDetailsByUserId(String userId) {
		UserDetails userDetails = iUserDao.listUserDetailsByUserId(userId);
		if (userDetails == null) {
			// 获取详细失败没有存储详细信息
			ServerResponse.createByErrorMessage("获取详细信息失败没有存储详细信息");
			return userDetails;
		} else {
			ServerResponse.createBySuccessMessage("获取详细信息成功");
			return userDetails;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.IUserService#saveUserDetials(pojo.UserDetails)
	 * 
	 * @author cz
	 * 
	 * @time 2018年4月27日下午5:10:30
	 */
	@Override
	public ServerResponse<UserDetails> saveUserDetials(UserDetails userDetails) {
		//校验userId,查看表UserDetails中是否有这一个数据,
		UserDetails checkExist = iUserDao.listUserDetailsByUserId(userDetails.getUserId());
		UserDetails userDetails_feedback;
		if(checkExist==null) {
			//该数据不存在，所以创建
			userDetails.setCreateTime(new Date());
			userDetails_feedback = iUserDao.saveOrUpdate(userDetails);
		}else {
			//该数据已存在，所以更新
			userDetails.setUpdateTime(new Date());
			userDetails_feedback = iUserDao.saveOrUpdate(userDetails);
		}
		if (userDetails_feedback == null) {
			return ServerResponse.createByErrorMessage("用户详细信息更新失败");
		} else {
			return ServerResponse.createBySuccess("用户详细信息更新成功", userDetails_feedback);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.IUserService#restPasswordDirect(java.lang.String,
	 * java.lang.String)
	 * 
	 * @author cz
	 * 
	 * @time 2018年4月28日下午1:50:47
	 */
	@Override
	public ServerResponse<String> restPasswordDirect(String username, String passwordNew) {
		// 校验username
		ServerResponse<User> validResponser = this.checkExist(username, Const.USERNAME);
		if (!validResponser.isSuccess()) {
			// 用户不存在
			return ServerResponse.createByErrorMessage("用户不存在");
		}
		String md5Password = MD5Util.MD5EncodeUtf8(passwordNew);
		User user = validResponser.getData();
		user.setPassword(md5Password);
		user.setUpdateTime(new Date());//设置更新时间
		int resultCount = iUserDao.saveOrUpdate(user);
		if (resultCount > 0) {
			return ServerResponse.createBySuccessMessage("修改密码成功");
		}
		return ServerResponse.createByErrorMessage("密码修改失败");
	}
}
