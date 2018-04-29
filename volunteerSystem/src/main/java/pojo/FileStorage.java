/**
 * 上午9:52:57
 * power
 */
package pojo;

import java.sql.Blob;
import java.util.Date;

/**
 * 
 * @author cz 2018年4月23日上午9:52:57
 */
public class FileStorage {

	private String id; // 文件唯一ID
	private String userId; // 文件所属user用户id

	private String volunteerItemId; //用户所属的ItemId;
	private String fileName; // 文件名称
	private Blob fileDetails; // 文件存储的内容

	private Date createTime; // 文件创建时间

	private Date updateTime; // 文件更新时间
	
	

	
	public FileStorage() {
		super();
	}

	public FileStorage(String id, String userId, String volunteerItemId, String fileName, Blob fileDetails,
			Date createTime, Date updateTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.volunteerItemId = volunteerItemId;
		this.fileName = fileName;
		this.fileDetails = fileDetails;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public String getVolunteerItemId() {
		return volunteerItemId;
	}

	public void setVolunteerItemId(String volunteerItemId) {
		this.volunteerItemId = volunteerItemId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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
