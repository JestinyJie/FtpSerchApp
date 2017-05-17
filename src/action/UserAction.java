package action;


import java.io.IOException;
import java.util.List;

import org.apache.catalina.core.ApplicationContext;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import entity.UserFtp;
import service.UserService;
import service.impl.UserServiceImpl;

/**
 * 用户动作类
 */
public class UserAction extends ActionSupport implements ModelDriven<UserFtp> {

	public String filepath;
	private UserFtp userftp=new UserFtp();
	UserService us=new UserServiceImpl();
	/*
	 * 登录action
	 */
	public String login(){
		//判空
		if(userftp.getUrl()==null || userftp.getUrl().trim().equals("") ||
				userftp.getUsername()==null || userftp.getUsername().trim().equals("")||
				userftp.getPassword()==null ){
			this.addActionError("*号为必填项！");
			return INPUT;
		}
		if (false == us.getFtpClient(userftp)){
			this.addActionError("连接失败！");
			return INPUT;
		}
		FTPClient ftp=us.getFtp();
		ActionContext.getContext().getSession().put("ftp", ftp);
		return "loginSuccess";
	}
	/*
	 * 转到搜索页面
	 */
	public String toSerch(){
		return "toSerch";
	}
	/*
	 * 搜索文件
	 */
	public String serch(){
		if(userftp.getFileName()==null || userftp.getFileName().trim().equals("")){
			this.addActionError("文件名不能为空！");
			return "serchFailure";
		}
		FTPClient ftp=(FTPClient) ActionContext.getContext().getSession().get("ftp");
		//得到搜索结果文件名列表
		List<String> list=us.serchFile(ftp,userftp.getFileName());
		if(list!=null && list.size()>0){
			//放入栈顶
			ActionContext.getContext().getValueStack().set("list", list);
			return "serchSuccess";
		}
		return "serchNull";
	}
	/*
	 * 下载
	 */
	public String down(){
		FTPClient ftp=(FTPClient) ActionContext.getContext().getSession().get("ftp");
		if(us.downFile(ftp,filepath)){
			return "downSuccess";
		};
		return "downFailure";
	}
	@Override
	public UserFtp getModel() {
		return userftp;
	}
	public void setFile(String filepath) {
		this.filepath = filepath;
	}
	
}
