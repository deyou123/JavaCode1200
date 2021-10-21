package com.encrypt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.security.interfaces.RSAPrivateKey;
import java.util.Properties;
import java.util.Set;
/**
 *版权所有：明日科技有限公司
 * @author Administrator
 */
public class decrypt {

	/**
	 * @param args
	 */
	public String Execdecrypt() {
		// TODO 自动生成构造函数存根
		String s="";
		try{
			//读取密文
			//BufferedReader in=new BufferedReader(new InputStreamReader(new FileInputStream("RSAmi.dat")));
			//String ctext=in.readLine();
			Properties pro = new Properties();
			try {
//				将信息包存在a.ini文件中
				pro.store(new FileOutputStream("a.ini",true),null);
//			     可以从a.ini中通过Properties.get方法读取配置信息
				pro.load(new FileInputStream("a.ini"));
			} catch (FileNotFoundException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
			String ctext=String.valueOf(String.valueOf(pro.get("register")));
			System.out.println(ctext);
			BigInteger c=new BigInteger(ctext);
			//获取私钥
			FileInputStream f=new FileInputStream("RSAPrikey.dat");
			ObjectInputStream b=new ObjectInputStream(f);
			RSAPrivateKey prk=(RSAPrivateKey)b.readObject();
			//得到公钥的两个参数
			BigInteger d=prk.getPrivateExponent();
			BigInteger n=prk.getModulus();
			//System.out.println("解密的私钥的指数为："+d);
			//System.out.println("解密私钥的模为："+n);
			//解密处理
			BigInteger m=c.modPow(d, n);
			byte [] mt=m.toByteArray();
			//System.out.println("解密后的明文为：");
			for(int i=0;i<mt.length;i++){
				//System.out.print((char)mt[i]);
				s+=(char)mt[i];
			}
			//}
		}catch(Exception e){
			e.printStackTrace();
		}
		return s;
	}
	public static void main(String[] args) {
		System.out.println(new decrypt().Execdecrypt());
	}

}
