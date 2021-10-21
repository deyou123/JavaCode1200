package com.encrypt;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
/**
 *��Ȩ���У����տƼ����޹�˾
 * @author Administrator
 */
public class genKey {

	/**
	 * @param args
	 */
	public genKey() {
		try {
			//������Կ��������
			KeyPairGenerator KPG=KeyPairGenerator.getInstance("RSA");
			//��ʼ����������
			KPG.initialize(1024);
			//������Կ��
			KeyPair KP=KPG.genKeyPair();
			//��ȡ��Կ��˽Կ
			PublicKey pbkey=KP.getPublic();
			PrivateKey prkey=KP.getPrivate();
			//����Կд���ļ���
			FileOutputStream filePbkey=new FileOutputStream("RSAPubkey.dat");
			ObjectOutputStream filePbkeyStream=new ObjectOutputStream(filePbkey);
			filePbkeyStream.writeObject(pbkey);
			//����Կд���ļ���
			FileOutputStream filePrkey=new FileOutputStream("RSAPrikey.dat");
			ObjectOutputStream filePrkeyStream=new ObjectOutputStream(filePrkey);
			filePrkeyStream.writeObject(prkey);
			
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
	}

}
