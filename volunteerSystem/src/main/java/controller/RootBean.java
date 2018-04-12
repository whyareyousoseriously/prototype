/**
 * 下午5:32:01
 * power
 */
package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import dao.IRootDao;
import dao.impl.IRootDaoImpl;
import pojo.Root;

/**
 * 管理用户bean类
 * @author cz 2018年4月12日下午5:32:01
 */
@ManagedBean
@RequestScoped
public class RootBean {
	private String id;// 管理员id
	private String rootname;// 管理员名字
	private String password;// 管理员密码

	public RootBean() {
		super();
	}

	public RootBean(String id, String rootName, String password) {
		super();
		this.id = id;
		this.rootname = rootName;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRootname() {
		return rootname;
	}

	public void setRootname(String rootName) {
		this.rootname = rootName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String doLogin() {
		// 正常发布，取消这两行打印，或者采用日志进行记录
		System.out.println("请求登陆的root用户账户:" + rootname);
		System.out.println("请求登陆的root用户密码:" + password);
		// 将页面属性封装到root中
		Root root = new Root();
		root.setRootname(rootname);
		root.setPassword(password);
		// 调用dao.impl
		IRootDao idao = new IRootDaoImpl();
		Root root_feedback = idao.login(root);
		if (root_feedback != null) {
			System.out.println(root.toString() + "login success!");
			this.setId(root_feedback.getId());
			this.setRootname(root_feedback.getRootname());
			this.setPassword(root_feedback.getPassword());

			// 将root_feedback保持在session中
			System.out.println("向session中写入d当前的root");
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext ec = context.getExternalContext();
			HttpSession session = (HttpSession) ec.getSession(true);
			session.setAttribute("CurrentRoot", root_feedback);
			System.out.println("写入session完成");

			return "r_home?faceRedirect=true";

		} else {
			System.out.println(root.toString() + "login faliure!");
			return "/WEB-INF/rootPage/r_login_error?facesRedirect=true";
		}
	}

	public String doRegister() {
		System.out.println("请求注册的root用户账户:" + rootname);
		System.out.println("请求注册的root用户密码:" + password);
		// 将页面属性封装到root中
		Root root = new Root();
		root.setRootname(rootname);
		root.setPassword(password);
		// 调用dao.impl
		IRootDao idao = new IRootDaoImpl();
		Root root_feedback = idao.register(root);
		if(root_feedback!=null) {
			System.out.println(root.toString()+"注册成功");
			return "/WEB-INF/rootPage/r_registration_success?facesRedirect=true";
		}else {
			System.out.println(root.toString()+"注册失败");
			return "/WEB-INF/rootPage/r_registration_error?facesRedirect=true";
		}
	}
}
