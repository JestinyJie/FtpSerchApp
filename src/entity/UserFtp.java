package entity;
/*
 * 用户的ftp实体类
 */
public class UserFtp {

	private String url;//域名
	private int port;//端口
	private String username;//用户名
	private String password;//密码
	private String fileName;//搜索文件名
	
	public UserFtp() {
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
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
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "UserFtp [url=" + url + ", port=" + port + ", username=" + username + ", password=" + password
				+ ", fileName=" + fileName + "]";
	}
	
}
