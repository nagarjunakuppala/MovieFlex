package info.vdsi.nags.app.model;

public class AjaxResponseBody {

	    String msg;
	    String userName;
	    boolean userExists;
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public boolean isUserExists() {
			return userExists;
		}
		public void setUserExists(boolean userExists) {
			this.userExists = userExists;
		}
		
}
