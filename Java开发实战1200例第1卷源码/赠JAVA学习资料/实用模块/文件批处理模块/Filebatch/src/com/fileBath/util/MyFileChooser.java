package com.fileBath.util;

import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.util.*;
/**
 *��Ȩ���У����տƼ����޹�˾
 * @author Administrator
 */
public class MyFileChooser {
	JPanel jContentPane = new JPanel();

	JFileChooser jfc = null;

	String name = "";

	String result = "";

	String files = "";

	private List listFile;

	// Ϊ�Ի������ָ���Ĺ�����(�����ļ��Ի���)
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

	// Ϊ�Ի������ָ���Ĺ�����(���ļ��Ի���)
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
		int i = jfc.showOpenDialog(jContentPane); // �򿪱����ļ��Ի���
		if (i == JFileChooser.APPROVE_OPTION) {
			name = jfc.getSelectedFile().getPath();
			files = jfc.getSelectedFile().getName();
			System.out.println("file1 " + files);
			dealFile();
			fileName();
		}
		return jfc;
	}
	// ��ȡ����ͼƬ·������
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
	// ֻ��ȡ�����ļ��з���
	public JFileChooser getFolder() {
		try {
			jfc = new JFileChooser();
			jfc.setFileSelectionMode(jfc.DIRECTORIES_ONLY); // ָʾֻ��ʾĿ¼
			int n = jfc.showOpenDialog(jContentPane);
			if (n == jfc.APPROVE_OPTION) // ������ȷ������ķ���ֵ
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
			System.out.println("û��ѡ���ļ�");
		}
		return null;
	}
}