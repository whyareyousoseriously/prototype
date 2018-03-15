/**
 * 
 */
package controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import dao.ItemDAO;
import dao.RootDAO;
import dao.UserDAO;
import dao.impl.ItemDAOImpl;
import dao.impl.RootDAOImpl;
import dao.impl.UserDAOImpl;
import entity.Item;
import entity.Root;
import entity.User;
import utils.CurrentRoot;
import utils.MailUtil;

/**
 * @author cz
 *
 * 2018年3月9日下午8:48:26
 */
/**
 * 
 * @author cz
 * 2018年3月12日下午3:43:23
 */
@ManagedBean
@SessionScoped
public class RootBean {
	private String id;
	private String username;
	private String password;
	
	private String email;
	private String type;
	private String active;
	private String mailCode;
	private String certificationState;
	
	
	
	//RootBean来操作Item
	private Item singleItem = new Item();
	
	//自己的items
	private Set<Item> ownItems = new HashSet<Item>();
	
	public Set<Item> getOwnItems() {
		/*
		 * RootImplDAO
		 * 
		 * */
		RootDAO rdao = new RootDAOImpl();
		System.out.println(CurrentRoot.getCurrentRoot());
		Root rdao_feedback = rdao.getOwnRoot("40288a876219902001621990b70c0000");
		
		ownItems = rdao_feedback.getItem();
		return ownItems;
	}

	public void setOwnItems(Set<Item> ownItems) {
		this.ownItems = ownItems;
	}

	public Item getSingleItem() {
		return singleItem;
	}

	public void setSingleItem(Item singleItem) {
		this.singleItem = singleItem;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getMailCode() {
		return mailCode;
	}
	public void setMailCode(String mailCode) {
		this.mailCode = mailCode;
	}
	public String getCertificationState() {
		return certificationState;
	}
	public void setCertificationState(String certificationState) {
		this.certificationState = certificationState;
	}
	/**
	 * 
	 * @author cz
	 * @time 2018年3月10日下午2:28:34
	 */
	public void showActive() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		context.addMessage(null, new FacesMessage("登陆中","账号状态："+this.active));
		if(active.equals("未激活"))
			context.addMessage(null, new FacesMessage("提示","您的账号状态未激活，请尽快激活，以免影响您的使用"));
	}
	/**
	 * 
	 * @author cz
	 * @time 2018年3月10日下午2:28:40
	 */
	public void showNotice() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("注册成功","激活邮件已发送到您的注册邮箱"+this.email+"请尽快登陆激活"));
	}
	
	@Override
	public String toString() {
		return "RootBean [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", type=" + type + ", active=" + active + ", mailCode=" + mailCode + ", certificationState="
				+ certificationState + "]";
	}
	/**
	 * @return
	 * @author cz
	 * @time 2018年3月10日下午2:39:32
	 */
	public String doLogin() {
		System.out.println(username);
		System.out.println(password);
		
		//将页面上的属性封装到root中
		Root root = new Root();
		root.setUsername(username);
		root.setPassword(password);
		
		//WCF Interface oriented programming
		RootDAO rdao = new RootDAOImpl();
		
		//接受逻辑返回值
		Root root_feedback = rdao.rootLogin(root);
		
		if(root_feedback!=null) {
			System.out.println(root_feedback.toString()+"login success!");
			this.setId(root_feedback.getId());
			this.setUsername(root_feedback.getUsername());
			this.setPassword(root_feedback.getPassword());
			this.setActive(root_feedback.getActive());
			this.setCertificationState(root_feedback.getCertificationState());
			this.setEmail(root_feedback.getEmail());
			this.setMailCode(root_feedback.getMailCode());
			this.setType(root_feedback.getType());
			this.showActive();
			
			//记录当前登录者的信息
			System.out.println("开始保存当前用户");
			CurrentRoot current = new CurrentRoot(root_feedback);
			System.out.println(CurrentRoot.getCurrentRoot());
			System.out.println("保存成功");
			
			return "r_home?faceRedirect=true";
			
		}else{
			System.out.println(root_feedback.toString()+"login faliure!");
			return "/WEB-INF/rootPage/r_login-error?facesRedirect=true";
		}
	}
	
	public String doRegister() {
		System.out.println(this.toString());
		
		//将页面属性封装入User对象
		Root root = new Root();
		root.setUsername(this.getUsername());
		root.setPassword(this.getPassword());
		root.setEmail(this.getEmail());
		root.setType(this.getType());
		root.setActive("未激活");; //置激活状态为未激活。
		root.setCertificationState("未认证");//置实名认证为未认证
		root.setMailCode(MailUtil.getUUID());
		this.showNotice();//将提示信息加入FacesMessage;
		//调用业务层处理数据
		RootDAO rdao = new RootDAOImpl();
		if(rdao.rootRegister(root))
			return "/WEB-INF/rootPage/r_registration_success?facesRedirect=true";
		else
			return "/WEB-INF/rootPage/r_registration_error?facesRedirect=true";
	}
	
	public void addSingleItem(ActionEvent e) {
		System.out.println("开始写入一条Item");
		System.out.println(CurrentRoot.getCurrentRoot());
		System.out.println(this.singleItem.toString());
		Set<Root> tempRoot = new HashSet<Root>();
		tempRoot.add(CurrentRoot.getCurrentRoot());
		singleItem.setRoot(tempRoot);
		singleItem.setUser(null);
		System.out.println(this.singleItem.toString());
		ItemDAO idao = new ItemDAOImpl();
		String idao_feedback = idao.addItem(singleItem);
		if("add_success".equals(idao_feedback))
			System.out.println("添加成功");
		else {
			System.out.println("添加失败");
		}
	}
}
