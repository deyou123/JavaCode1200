package com.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import sun.java2d.pipe.SpanShapeRenderer.Simple;
/**
 *��Ȩ���У����տƼ����޹�˾
 * @author Administrator
 */
public class RegisterMark {
	public static String getRegister(String s) {
		String s1="",s2="";
		SimpleDateFormat sd=new SimpleDateFormat("yyyy");
		Date d=new Date();
		s+=sd.format(d);
		for(int i=0;i<s.length();i++){
				s1+=(int)s.charAt(i)*(i+1);
		}
		for(int i=0;i<s1.length()/5;i++){
			
			if(i==s1.length()/5-1)
				s2+=s1.substring(i*5,(i+1)*5);
			else 
				s2+=s1.substring(i*5, (i+1)*5)+"-";
		}
		return s2;
	}

	public static String getMAC() {
		String macResult = null;
			String osName = System.getProperty("os.name");
			StringBuffer systemPathBuff = new StringBuffer("");
			if (osName.indexOf("Windows") > -1) {
				// Windows����ϵͳ��cmd.exe�ľ���·��
				systemPathBuff.append("c:\\WINDOWS\\system32\\cmd.exe");
			}
			if (osName.indexOf("NT") > -1) {
				// NT����ϵͳ��cmd.exe�ľ���·��
				systemPathBuff.append("c:\\WINDOWS\\command.com");
			}
			// 
			Process pro = null;
			try {
	
				// Serial Number �ĵõ�
				// ������൱����cmd����ִ�� dir������.���õ�����ִ����Ϻ�������
				pro = Runtime.getRuntime().exec(
						systemPathBuff.toString() + " /c dir");
				InputStream is = pro.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				// ��ȡ��һ��
				String message = br.readLine();
				// Serial Number ����ʱ����
				String serNuResult = null;
				int index = -1;
					// ��ȡ��һ��
					message = br.readLine();
				
				// Mac��ַ�ĵõ�
				// ������൱����cmd����ִ�� ipconfig/all ������.���õ�����ִ����Ϻ�������
				pro = Runtime.getRuntime().exec(
						systemPathBuff.toString() + " /c ipconfig/all");
				is = pro.getInputStream();
				br = new BufferedReader(new InputStreamReader(is));
				// ��ȡ��һ��
				message = br.readLine();
				// Mac��ַ ����ʱ����
				while (message != null) {
					if ((index = message.indexOf("Physical Address")) > 0) {
						macResult = message.substring(index + 36).trim();
						break;
					}
					// ��ȡ��һ��
					message = br.readLine();
				}
	}catch(Exception e){
		e.printStackTrace();
	}
	return macResult;
	}

	public static void main(String[] args) {
		getRegister("wsy");
	}
}


