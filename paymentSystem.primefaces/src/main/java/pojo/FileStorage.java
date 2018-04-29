/**
 * 下午4:43:03
 * power
 */
package pojo;

import java.sql.Blob;
import java.util.Date;

/**
 * 
 * @author cz
 * 2018年4月25日下午4:43:03
 */
public class FileStorage {
	private String fileId;//文件Id;
	private String managerId;//管理者Id;
	private Blob fileDetails;//文件内容;
	private Date createTime;//创建时间
	private Date updateTime;//更新时间
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public Blob getFileDetails() {
		return fileDetails;
	}
	public void setFileDetails(Blob fileDetails) {
		this.fileDetails = fileDetails;
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
