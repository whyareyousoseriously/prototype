/**
 * 
 */
package controller;

import java.util.HashSet;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import dao.ItemDAO;
import dao.impl.ItemDAOImpl;
import entity.Item;
import entity.Root;
import entity.User;
import utils.CurrentRoot;

/**
 * @author cz
 *
 * 2018年3月9日下午7:30:29
 */
/**
 * 
 * @author cz
 * 2018年3月12日下午2:33:54
 */
@ManagedBean
@SessionScoped
public class ItemBean {
	private String name;
	private Double value;
	private String statuts;
	private Set<Root> root;
	private User user;
	
	public ItemBean() {
		super();
	}
	public ItemBean(String name, Double value, String statuts, Set<Root> root, User user) {
		super();
		this.name = name;
		this.value = value;
		this.statuts = statuts;
		this.root = root;
		this.user = user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public String getStatuts() {
		return statuts;
	}
	public void setStatuts(String statuts) {
		this.statuts = statuts;
	}
	public Set<Root> getRoot() {
		return root;
	}
	public void setRoot(Set<Root> root) {
		this.root = root;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "ItemBean [name=" + name + ", value=" + value + ", statuts=" + statuts + ", root=" + root + ", user="
				+ user + "]";
	}
	
	//支付条目Item的增加
	/**
	 * 单个支付条目的添加
	 * @author cz
	 * @time 2018年3月12日下午3:40:36
	 */
	public String addSingleItem() {
		System.out.println(name);
		System.out.println(statuts);
		Set<Root> tempRoot = new HashSet<Root>();
		tempRoot.add(CurrentRoot.getCurrentRoot());
		this.setRoot(tempRoot);
		//将页面属性封装到Item中
		Item item = new Item();
		item.setName(name);
		item.setValue(value);
		item.setRoot(root);
		item.setUser(null);
		
		//调用ItemDAO
		ItemDAO idao = new ItemDAOImpl();
		String idao_feedback = idao.addItem(item);
		if("add_success".equals(idao_feedback)) {
			System.out.println("添加成功");
			return "r_home";
		}else {
			System.out.println("添加失败");
			return "u_home";
		}
	}
	//支付条目Item的删除
	//支付条目Item的更新
	//支付条目Item的查询
}
