package service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import entity.UserFtp;
import service.UserService;

/**
 * 服务接口实现类
 * 
 * @author Jestiny
 */
public class UserServiceImpl implements UserService {

	private FTPClient ftp = new FTPClient();

	/*
	 * 得到ftp连接
	 */
	@Override
	public boolean getFtpClient(UserFtp userftp) {
		try {
			int reply;
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			if (userftp.getPort() == 0) {
				ftp.connect(userftp.getUrl());
			} else {
				ftp.connect(userftp.getUrl(), userftp.getPort());
			}
			ftp.login(userftp.getUsername(), userftp.getPassword());// 登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * 得到ftp
	 */
	public FTPClient getFtp() {
		return ftp;
	}

	/*
	 * 文件搜索
	 */
	public List<String> serchFile(FTPClient ftp, String fileName) {
		List<String> result;
		try {
			/* 重点区域 */
			// ftp.changeWorkingDirectory("..");//默认目录不是根目录
			ftp.enterLocalPassiveMode();// 处理linux服务器下无法查找文件的问题
			ftp.setControlEncoding("gbk");// 处理中文乱码问题
			/* 重点区域 */
			FTPFile[] listFiles = ftp.listFiles();
			result = this.BFS(listFiles, ftp, fileName);
			System.out.println();
			System.out.println(result.toString());
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * 广度优先搜索文件
	 */
	private List<String> BFS(FTPFile[] ftpFiles, FTPClient ftp, String filename) {

		int head = 0;
		int rear = 0;
		// 因为事先不知道文件数目，所以不能采用数组队列，这里采用list来代替
		List<String> queue = new ArrayList<String>();
		List<String> result = new ArrayList<String>();
		if (ftpFiles.length < 1)
			return null;
		for (FTPFile ftpFile : ftpFiles) {
			System.out.println(ftpFile.getName());
			// if(ftpFile.getName().equals(filename))
			// result.add(ftpFile.getName());
			if (ftpFile.getName().indexOf(filename) != -1)
				result.add(ftpFile.getName());
			if (ftpFile.isDirectory()) {
				queue.add(ftpFile.getName());
				rear++;// 入队
			}
		}
		while (head != rear) {
			StringBuilder path = new StringBuilder(queue.get(head++));// 出队
			try {

				for (FTPFile ff = getFirstFile(path, ftp); ff != null; ff = getNextFile(path, ff, ftp)) {
					System.out.println(ff.getName());
					// if(ff.getName().equals(filename))
					// result.add(path+"/"+ff.getName());
					if (ff.getName().indexOf(filename) != -1)
						result.add(path + "/" + ff.getName());
					if (ff.isDirectory()) {
						/*
						 * 这一行代码是错误的！！！！！ 这样会直接改变path，应该新new一个String，让它自己来append
						 * 不能改变path的值
						 * queue.add(path.append("/").append(ff.getName()).
						 * toString());
						 */
						StringBuilder nPath = new StringBuilder(path);
						queue.add(nPath.append("/").append(ff.getName()).toString());
						rear++;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

		return result;
	}

	/*
	 * 得到目录f下相对与ff的下一个文件
	 */
	private FTPFile getNextFile(StringBuilder path, FTPFile ff, FTPClient ftp) {
		try {
			String rePath = new String(path.toString().getBytes("GBK"), "iso-8859-1");// 更换编码方式，以便处理中文目录
			FTPFile[] files = ftp.listFiles(rePath);
			if (files != null && files.length > 0) {
				// 先找到ff这个文件
				for (int i = 0; i < files.length; i++) {
					if (ff.getName().equals(files[i].getName()) && i < files.length - 1) {
						return files[i + 1];
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * 得到目录f下的第一个文件
	 */
	private FTPFile getFirstFile(StringBuilder path, FTPClient ftp) {
		try {
			/* 这是重点，更换编码才能处理中文目录 */
			String rePath = new String(path.toString().getBytes("GBK"), "iso-8859-1");// 更换编码方式，以便处理中文目录
			FTPFile[] listFiles = ftp.listFiles(rePath);
			if (listFiles != null && listFiles.length > 0) {
				return listFiles[0];
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	/*
	 * 文件下载
	 */
	public boolean downFile(FTPClient ftp, String filepath) {
		// 首先切割路径得到文件名
		ftp.setControlEncoding("GBK");
		try {

			String[] split = filepath.split("/");
			String filename = split[split.length - 1];
			// 下载文件
			File localFile = new File("D:/" + filename);
			OutputStream out;
			out = new FileOutputStream(localFile);
			String rePath = new String(filepath.getBytes("GBK"), "iso-8859-1");// 更换编码方式，以便下载中文文件
			/*
			 * ftp下处理的文件编码必须是iso类型 ，之前转GBK类型只是为了得到文件名 否则下载下来的文件大小为0
			 */
			boolean flag = ftp.retrieveFile(rePath, out);
			out.close();
			return flag;
		} catch (Exception e1) {
			e1.printStackTrace();
			return false;
		}
	}
}
