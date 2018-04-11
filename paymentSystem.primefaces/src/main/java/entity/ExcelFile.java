/**
 * 下午4:32:44
 * power
 */
package entity;


import java.sql.Blob;
import java.util.Date;

/**
 * 用于Excel文件的上传和下载
 * @author cz
 * 2018年4月8日下午4:32:44
 */
public class ExcelFile {
	private String id; //文件唯一ID
	private Root root = new Root(); //文件所属root用户

	private String fileName; //文件名称
	private Blob fileDetails; //文件存储的内容
	
	private Date createTime; //文件创建时间
	
	private Date updateTime; //文件更新时间
	

	

	public ExcelFile() {
		super();
	}

	public ExcelFile(String id, Root root, String fileName, Blob fileDetails, Date createTime, Date updateTime) {
		super();
		this.id = id;
		this.root = root;
		this.fileName = fileName;
		this.fileDetails = fileDetails;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Root getRoot() {
		return root;
	}

	public void setRoot(Root root) {
		this.root = root;
	}

	public Blob getFileDetails() {
		return fileDetails;
	}

	public void setFileDetails(Blob bs) {
		this.fileDetails = bs;
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
