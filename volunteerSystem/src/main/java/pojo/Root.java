/**
 * 下午5:19:25
 * power
 */
package pojo;

/**
 * 
 * @author cz
 * 2018年4月12日下午5:19:25
 */
public class Root {

	private String id;//管理员id
	private String rootname;//管理员名字
	private String password;//管理员密码
	public Root() {
		super();
	}
	public Root(String id, String rootName, String password) {
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
	
}
