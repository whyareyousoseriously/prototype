/**
 * 下午6:53:46
 * power
 */
package controller.user;

import java.io.File;
import java.io.IOException;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.primefaces.event.FileUploadEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.Const;
import common.ServerResponse;
import pojo.User;
import pojo.UserDetails;
import service.IUserService;
import service.impl.UserServiceImpl;
import utils.DateTimeUtil;
import utils.db.MyHibernateSessionFactory;
import vo.UserVo;

/**
 * UserVoController 用户视图控制类
 * 
 * @author cz 2018年4月26日下午6:53:46
 */
@ManagedBean(name="userVoController")
@SessionScoped
public class UserVoController {
	private UserVo userVo;

	private IUserService iUserService;

	private UserDetails userDetails;
	
	private static final Logger logger = LoggerFactory.getLogger(UserVoController.class);

	public UserVoController() {
		this.userVo = new UserVo();
		this.iUserService = new UserServiceImpl();
		this.userDetails = new UserDetails();
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public UserVo getUserVo() {
		return userVo;
	}

	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}

	/**
	 * 获取用户详细信息
	 * 
	 * @author cz
	 * @time 2018年4月27日上午11:39:42
	 */
	public String listUserVo() {
		// 获得当前session中的当前用户
		User user = (User) this.getCurrentSession().getAttribute(Const.CURRENT_USER);
		// 进行判断
		if (user == null) {
			ServerResponse.createByErrorMessage("用户未登录，无法获取详细信息");
			// 返回登陆界面
			return "/login?faces-redirect=true";
		} else {
			// 调用iUserService,获得详细信息
			userDetails = iUserService.listUserDetailsByUserId(user.getUserId());
			if (userDetails == null) {
				userDetails = new UserDetails();
			}
			userVo = this.Combine(user, userDetails);
		}
		return "/user/u_home?faces-redirect=true";
	}

	public String saveUserDetails() {
		// 获得当前session中的当前用户
		User user = (User) this.getCurrentSession().getAttribute(Const.CURRENT_USER);
		// 进行判断
		if (user == null) {
			ServerResponse.createByErrorMessage("用户未登录，无法完善详细信息");
			// 返回登陆界面
			return "/login?faces-redirect=true";
		} else {
			// 调用iUserService.获取详细信息
			userDetails = iUserService.saveUserDetials(this.change(userVo, user)).getData();
			return "/user/u_home?faces-redirect=true";
		}
	}
	
	public void handlePhotoUpload(FileUploadEvent event) {
		FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
		// 获得当前session中的当前用户
		User user = (User) this.getCurrentSession().getAttribute(Const.CURRENT_USER);
		// 进行判断
		if (user == null) {
			ServerResponse.createByErrorMessage("用户未登录，无法完善详细信息");
			// 返回登陆界面
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(
						FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()+"login.jsf");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			//将文件写入指定路径
			ServerResponse<String> response=this.writePhoto("d:\\paymentSystem\\img\\"+user.getUserId()+"\\", event.getFile().getContents());
			if(response.isSuccess()) {
				//成功写入
				ServerResponse.createBySuccessMessage(response.getData()+"文件写入成功");
				logger.info(response.getData()+"文件写入成功");
			
			}else {
				ServerResponse.createByErrorMessage(response.getData()+"文件写入失败");
				logger.info(response.getData()+"文件写入失败");

			}
			//将文件名封装到userVo中
			userVo.setPhotoName(response.getData());
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			userVo.setPhotoDetails(Hibernate.getLobCreator(session).createBlob(event.getFile().getContents()));
			session.getTransaction().commit();
			
			// 调用iUserService.获取详细信息
			userDetails = iUserService.saveUserDetials(this.change(userVo, user)).getData();
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(
						"addUserDetails.jsf");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
	
		}

		
	}
	
	private ServerResponse<String> writePhoto(String path,byte[] data){
		//若路径不存在则为其创建路径
		File fileDir = new File(path);
		if (!fileDir.exists()) {
			fileDir.setWritable(true);
			fileDir.mkdirs();
		}
		//为其重新随机取名字
		String photoName = this.getRandomImageName();
		File targetFile = new File(path,photoName+".jpeg");
		FileImageOutputStream imageOutput;
        try {
            imageOutput = new FileImageOutputStream(targetFile);
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
            return ServerResponse.createBySuccess(photoName);
        }
        catch(IOException e) {
            throw new FacesException("Error in writing captured image.", e);
        }
	}

	/**
	 * 获得随机名字
	 * @return
	 * @author cz
	 * @time 2018年4月27日下午10:50:35
	 */
	private String getRandomImageName() {
        int i = (int) (Math.random() * 10000000);
         
        return String.valueOf(i);
    }
	
	private UserDetails change(UserVo userVo, User currentUser) {
		// 将userVo中的属于userDetails中的字段给予userDetails
		UserDetails userDetails = new UserDetails();
		userDetails.setUserId(currentUser.getUserId());
		userDetails.setAddress(userVo.getAddress());
		userDetails.setIdNumber(userVo.getIdNumber());
		userDetails.setOccupation(userVo.getOccupation());
		userDetails.setPhone(userVo.getPhone());
		userDetails.setRealName(userVo.getRealName());
		userDetails.setSex(StringUtils.equals("男", userVo.getSex()) ? Const.MAN : Const.FEMALE);
		userDetails.setPhotoDetails(userVo.getPhotoDetails());
		userDetails.setPhotoName(userVo.getPhotoName());
		return userDetails;
	}

	/**
	 * @param user
	 * @param userDetails
	 * @return
	 * @author cz
	 * @time 2018年4月26日下午7:12:39
	 */
	private UserVo Combine(User user, UserDetails userDetails) {
		// 结合user和userDetails成为UserVo
		UserVo userVo = new UserVo();
		userVo.setActive(Const.ACTIVE == user.getActive() ? "已激活" : "未激活");
		userVo.setAddress(userDetails.getAddress());
		userVo.setEmail(user.getEmail());
		userVo.setIdNumber(userDetails.getIdNumber());
		userVo.setLoginTime(DateTimeUtil.dateToStr(user.getLoginTime()));
		userVo.setOccupation(userDetails.getOccupation());
		userVo.setPhone(userDetails.getPhone());
		userVo.setRealName(userDetails.getRealName());
		userVo.setSex(Const.FEMALE == userDetails.getSex() ? "女" : "男");
		userVo.setUsername(user.getUsername());
		//通过图片的名字，以及图片的所有者，定位到图片的位置
		userVo.setPhotoName(userDetails.getPhotoName());
		userVo.setUserId(userDetails.getUserId());
		return userVo;
	}

	private HttpSession getCurrentSession() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext ec = context.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(true);
		return session;
	}

}
