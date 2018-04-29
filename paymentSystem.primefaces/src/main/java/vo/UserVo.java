/**
 * 下午6:45:40
 * power
 */
package vo;

import java.sql.Blob;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * 用户的view,供前端显示
 * @author cz
 * 2018年4月26日下午6:45:40
 */
@ManagedBean
@SessionScoped
public class UserVo {
	private String userId;	//用户id,视图中此字段存在,用于定位图片
	private String username; //用户名
	//private String password; //用户密码,视图中密码不用存在
	private String email; //邮箱账号
	private String active; //邮箱激活状态 ,在视图中active采用String字段方便理解和显示
	//private String mailCode; //邮箱激活码，视图中邮箱激活码也不用存在
	private String photoName;//头像名称
	private Blob photoDetails;//头像细节
	private String realName;//真实姓名;
	private String idNumber;//身份证号
	private String sex;//性别；同激活状态一样，sex字段也采用String字段，方便理解和显示
	private String occupation;//职业
	private String phone;//手机号
	private String address;//地址
	
	private String loginTime; //登陆时间

	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public Blob getPhotoDetails() {
		return photoDetails;
	}

	public void setPhotoDetails(Blob photoDetails) {
		this.photoDetails = photoDetails;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	
	
	

}
