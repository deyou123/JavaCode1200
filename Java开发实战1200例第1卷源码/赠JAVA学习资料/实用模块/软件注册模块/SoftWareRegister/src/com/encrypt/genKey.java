package com.encrypt;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
/**
 *版权所有：明日科技有限公司
 * @author Administrator
 */
public class genKey {

	/**
	 * @param args
	 */
	public genKey() {
		try {
			//创建密钥对生成器
			KeyPairGenerator KPG=KeyPairGenerator.getInstance("RSA");
			//初始化该生成器
			KPG.initialize(1024);
			//生成密钥对
			KeyPair KP=KPG.genKeyPair();
			//获取共钥和私钥
			PublicKey pbkey=KP.getPublic();
			PrivateKey prkey=KP.getPrivate();
			//将共钥写进文件中
			FileOutputStream filePbkey=new FileOutputStream("RSAPubkey.dat");
			ObjectOutputStream filePbkeyStream=new ObjectOutputStream(filePbkey);
			filePbkeyStream.writeObject(pbkey);
			//将密钥写进文件中
			FileOutputStream filePrkey=new FileOutputStream("RSAPrikey.dat");
			ObjectOutputStream filePrkeyStream=new ObjectOutputStream(filePrkey);
			filePrkeyStream.writeObject(prkey);
			
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}

}
