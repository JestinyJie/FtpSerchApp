package service;

import java.util.List;

import org.apache.commons.net.ftp.FTPClient;

import entity.UserFtp;

/**
 * 服务接口类
 * @author Jestiny
 */
public interface UserService {

	boolean getFtpClient(UserFtp userftp);
	FTPClient getFtp();
	List<String> serchFile(FTPClient ftp, String fileName);
	boolean downFile(FTPClient ftp, String filepath);

}
