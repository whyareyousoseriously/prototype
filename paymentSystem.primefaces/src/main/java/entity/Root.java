/**
 * 
 */
package entity;

/**
 * @author cz
 *
 * 2018年3月9日下午8:56:04
 */
public class Root {
	private int uid;
	private String username;
	private String password;
	private String email;
	private String type;
	private String active;
	private String mailCode;
	private String certificationState;
	public Root() {
		super();
	}
	public Root(int uid, String username, String password, String email, String type, String active, String mailCode,
			String certificationState) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.email = email;
		this.type = type;
		this.active = active;
		this.mailCode = mailCode;
		this.certificationState = certificationState;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
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
	@Override
	public String toString() {
		return "Root [uid=" + uid + ", username=" + username + ", password=" + password + ", email=" + email + ", type="
				+ type + ", active=" + active + ", mailCode=" + mailCode + ", certificationState=" + certificationState
				+ "]";
	}
	
}
