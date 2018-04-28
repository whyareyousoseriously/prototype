/**
 * 下午4:08:55
 * power
 */
package pojo;

import java.sql.Blob;
import java.util.Date;

/**
 * 
 * @author cz 2018年4月25日下午4:08:55
 */
public class UserDetails {
	
	private String userId;// 用户id;
	private String realName;// 真实姓名;
	private String idNumber;// 身份证号
	private String photoName; // 个人头像名字
	private Blob photoDetails; // 个人头像内容
	private Integer sex;// 性别；
	private String occupation;// 职业
	private String phone;// 手机号
	private String address;// 地址
	private Date createTime;// 创建时间
	private Date updateTime;// 最近一次更新时间
	
	
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	

}
