package test;

import java.io.IOException;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;

import entity.UserFtp;
import service.UserService;
import service.impl.UserServiceImpl;

public class Test {

	@org.junit.Test
	public void test() {
		UserService us = new UserServiceImpl();
		UserFtp userftp = new UserFtp();
		userftp.setUrl("127.0.0.1");
		userftp.setUsername("ftp1");
		userftp.setPassword("123");
		userftp.setPort(0);
		String fileName = "中";
		us.getFtpClient(userftp);
		FTPClient ftp = us.getFtp();
		ftp.setControlEncoding("gbk");
		ftp.enterLocalPassiveMode();
		List<String> serchFile = us.serchFile(ftp, fileName);
		for (String downfile : serchFile) {
			System.out.println(downfile);
		}
		// us.downFile(ftp, "中文测试/中文1.txt");
	}
	@org.junit.Test
	public void test1(){
		StringBuilder path=new StringBuilder("123");
		StringBuilder npath=new StringBuilder(path);
		npath.append("456");
		System.out.println(npath.toString());
	}
}
