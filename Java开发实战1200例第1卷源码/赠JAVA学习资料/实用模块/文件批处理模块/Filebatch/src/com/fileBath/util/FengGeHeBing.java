package com.fileBath.util;
import java.io.*;
import javax.swing.*;
/**
 *��Ȩ���У����տƼ����޹�˾
 * @author Administrator
 */
public class FengGeHeBing {
	JFrame jframe = new JFrame();

	//�����ļ��ָ��
public void fenGe(File commFile, File untieFile, int filesize) {
		FileInputStream fis = null;
		int size=1024*1024;
		try {
			if (!untieFile.isDirectory()) {
				untieFile.mkdirs();
			}
			size = size * filesize;
			int length = (int) commFile.length();
			int num = length / size;
			int yu = length % size;
			String newfengeFile = commFile.getAbsolutePath();
			int fileNew = newfengeFile.lastIndexOf(".");
			String strNew = newfengeFile.substring(fileNew, newfengeFile
					.length());
			fis = new FileInputStream(commFile);
			File[] fl = new File[num + 1];
			int begin = 0;
			for (int i = 0; i < num; i++) {
				fl[i] = new File(untieFile.getAbsolutePath() + "\\" + (i + 1)
						+ strNew + ".tem");
				if (!fl[i].isFile()) {
					fl[i].createNewFile();
				}
				FileOutputStream fos = new FileOutputStream(fl[i]);
				byte[] bl = new byte[size];
				fis.read(bl);
				fos.write(bl);
				begin = begin + size * 1024 * 1024;
				System.out.println("BEGIN " + begin);
				fos.close();
			}
			if (yu != 0) {
				fl[num] = new File(untieFile.getAbsolutePath() + "\\"
						+ (num + 1) + strNew + ".tem");
				if (!fl[num].isFile()) {
					fl[num].createNewFile();
				}
				FileOutputStream fyu = new FileOutputStream(fl[num]);
				byte[] byt = new byte[yu];
				fis.read(byt);

				fyu.write(byt);
				fyu.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
}	
	//�ļ��ϲ�����
/**
 * @param file:Ҫ���зָ���ļ��������
 * @param cunDir���ϲ����ļ��ı���·��
 * @param hz���ϲ����ļ��ĸ�ʽ
 */
public void heBing(File[] file, File cunDir, String hz) {
	try {
		File heBingFile = new File(cunDir.getAbsoluteFile() + "\\UNTIE"
				+ hz);
		if (!heBingFile.isFile()) {
			heBingFile.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(heBingFile);
		for (int i = 0; i < file.length; i++) {
			FileInputStream fis = new FileInputStream(file[i]);
			int len = (int) file[i].length();
			byte[] bRead = new byte[len];
			fis.read(bRead);
			fos.write(bRead);
			fis.close();
		}
		fos.close();
		JOptionPane.showMessageDialog(jframe, "�ļ��ϲ��ɹ�", "��Ϣ�Ի���",
				JOptionPane.WARNING_MESSAGE);
	} catch (Exception e) {
		e.printStackTrace();
		JOptionPane.showMessageDialog(jframe, "��ѡ����ļ����Ƿָ��ļ�����ѡ��ָ��ļ�",
				"��Ϣ�Ի���", JOptionPane.WARNING_MESSAGE);
	}
}
}
