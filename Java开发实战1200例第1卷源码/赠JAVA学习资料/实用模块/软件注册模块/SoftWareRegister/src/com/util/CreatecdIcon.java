package com.util;

import java.net.URL;

import javax.swing.ImageIcon;
import com.wsy.mainFrame;
/**
 *��Ȩ���У����տƼ����޹�˾
 * @author Administrator
 */
public class CreatecdIcon {
	public static ImageIcon add(String ImageName){
		URL IconUrl = mainFrame.class.getResource("/"+ImageName);
		ImageIcon icon=new ImageIcon(IconUrl);
		return icon;
	}
	public static void main(String args[]){
		URL IconUrl = mainFrame.class.getResource("/");
		//System.out.println(IconUrl);
	}
}
