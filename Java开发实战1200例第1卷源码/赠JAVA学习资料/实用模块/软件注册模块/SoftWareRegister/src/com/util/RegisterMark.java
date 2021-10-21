package com.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import sun.java2d.pipe.SpanShapeRenderer.Simple;
/**
 *版权所有：明日科技有限公司
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
				// Windows操作系统的cmd.exe的绝对路径
				systemPathBuff.append("c:\\WINDOWS\\system32\\cmd.exe");
			}
			if (osName.indexOf("NT") > -1) {
				// NT操作系统的cmd.exe的绝对路径
				systemPathBuff.append("c:\\WINDOWS\\command.com");
			}
			// 
			Process pro = null;
			try {
	
				// Serial Number 的得到
				// 此语句相当于在cmd下面执行 dir的命令.并得到命令执行完毕后的输出流
				pro = Runtime.getRuntime().exec(
						systemPathBuff.toString() + " /c dir");
				InputStream is = pro.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				// 读取第一行
				String message = br.readLine();
				// Serial Number 的零时变量
				String serNuResult = null;
				int index = -1;
					// 读取下一行
					message = br.readLine();
				
				// Mac地址的得到
				// 此语句相当于在cmd下面执行 ipconfig/all 的命令.并得到命令执行完毕后的输出流
				pro = Runtime.getRuntime().exec(
						systemPathBuff.toString() + " /c ipconfig/all");
				is = pro.getInputStream();
				br = new BufferedReader(new InputStreamReader(is));
				// 读取第一行
				message = br.readLine();
				// Mac地址 的零时变量
				while (message != null) {
					if ((index = message.indexOf("Physical Address")) > 0) {
						macResult = message.substring(index + 36).trim();
						break;
					}
					// 读取下一行
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


