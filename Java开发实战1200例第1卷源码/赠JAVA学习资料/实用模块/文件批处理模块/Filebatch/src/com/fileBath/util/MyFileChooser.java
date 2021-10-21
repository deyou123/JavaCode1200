package com.fileBath.util;

import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.util.*;
/**
 *版权所有：明日科技有限公司
 * @author Administrator
 */
public class MyFileChooser {
	JPanel jContentPane = new JPanel();

	JFileChooser jfc = null;

	String name = "";

	String result = "";

	String files = "";

	private List listFile;

	// 为对话框添加指定的过滤器(保存文件对话框)
	public JFileChooser getSeveFileChooser() {
		jfc = new JFileChooser() {
			public String paramString() {
				return "drhdrhdrh";
			}
		};
		jfc.addChoosableFileFilter(new FileFilter() {
			public boolean accept(File f) {
				return f.getName().toLowerCase().endsWith(".zip")
						|| f.isDirectory();
			}

			public String getDescription() {
				return "zip(*.zip)";
			}
		});
		int i = jfc.showSaveDialog(jContentPane);
		if (i == JFileChooser.APPROVE_OPTION) {
			name = jfc.getCurrentDirectory() + "\\"
					+ jfc.getSelectedFile().getName();

			javax.swing.filechooser.FileFilter filefilter = jfc.getFileFilter();
			String path = filefilter.getDescription();

			if (path.equals("zip(*.zip)")) {
				result = ".bmp";
			} 
			getPath();
		}
		return jfc;
	}

	public String getPath() {
		return name + result;
	}

	// 为对话框添加指定的过滤器(打开文件对话框)
	public JFileChooser getOpenFileChooser() {
		jfc = new JFileChooser() {
			public String paramString() {
				return "drhdrhdrh";
			}
		};
		jfc.addChoosableFileFilter(new FileFilter() {
			public boolean accept(File f) {
				return f.getName().toLowerCase().endsWith(".zip")||
				f.getName().toLowerCase().endsWith(".zip")
						|| f.isDirectory();
			}
			public String getDescription() {
				return "zip(*)";
			}
		});
		int i = jfc.showOpenDialog(jContentPane); // 打开保存文件对话框
		if (i == JFileChooser.APPROVE_OPTION) {
			name = jfc.getSelectedFile().getPath();
			files = jfc.getSelectedFile().getName();
			System.out.println("file1 " + files);
			dealFile();
			fileName();
		}
		return jfc;
	}
	// 获取保存图片路径方法
	public String dealFile() {
		if (name != null) {
			return name;

		} else {
			return null;
		}
	}
	public String fileName() {
		if (files != null) {
			return files;
		} else {
			return null;
		}
	}
	// 只获取磁盘文件夹方法
	public JFileChooser getFolder() {
		try {
			jfc = new JFileChooser();
			jfc.setFileSelectionMode(jfc.DIRECTORIES_ONLY); // 指示只显示目录
			int n = jfc.showOpenDialog(jContentPane);
			if (n == jfc.APPROVE_OPTION) // 单击”确定“后的返回值
			{
				String folderPath = new String();
				folderPath = jfc.getSelectedFile().getPath();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return jfc;
	}
	public String getFolderPath() {
		try {	
				return jfc.getSelectedFile().getPath();
			
		} catch (Exception e) {
			System.out.println("没有选择文件");
		}
		return null;
	}
}