package info.vdsi.nags.app.beans;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class UserLoginData {
	@NotEmpty @NotBlank
	private String userName;
	private String password;
	private String admininfo;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAdmininfo() {
		return admininfo;
	}
	public void setAdmininfo(String admininfo) {
		this.admininfo = admininfo;
	}
	
}
