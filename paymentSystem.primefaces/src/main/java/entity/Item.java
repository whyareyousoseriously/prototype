/**
 * 下午4:57:28
 * power
 */
package entity;

import java.util.HashSet;
import java.util.Set;



/**
 * Item实体类
 * 属性:	String id
 * 		String name
 * 		double value
 * 		String statuts
 * 		Set<Root> root
 * 		User	user;
 * @author cz
 * 2018年3月12日下午2:17:24
 */
public class Item {
	private String id;
	private String name;
	private double value;
	private String statuts;
	/*
	 * 多对多关系：管理用户VS支付条目
	 * hibernate
	 * */
	private Set<Root> root = new HashSet<Root>();
	
	/*
	 * 一对多关系：一般用户VS支付条目
	 * hibernate
	 * */
	private User user = new User();

	public Item() {
		super();
	}

	public Item(String id, String name, double value, String statuts, Set<Root> root, User user) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
		this.statuts = statuts;
		this.root = root;
		this.user = user;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
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
		return "Item [id=" + id + ", name=" + name + ", value=" + value + ", statuts=" + statuts + ", root=" + root
				+ ", user=" + user + "]";
	}

	
}
