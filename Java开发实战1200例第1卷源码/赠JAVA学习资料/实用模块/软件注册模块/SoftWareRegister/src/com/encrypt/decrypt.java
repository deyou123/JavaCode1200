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
 *��Ȩ���У����տƼ����޹�˾
 * @author Administrator
 */
public class decrypt {

	/**
	 * @param args
	 */
	public String Execdecrypt() {
		// TODO �Զ����ɹ��캯�����
		String s="";
		try{
			//��ȡ����
			//BufferedReader in=new BufferedReader(new InputStreamReader(new FileInputStream("RSAmi.dat")));
			//String ctext=in.readLine();
			Properties pro = new Properties();
			try {
//				����Ϣ������a.ini�ļ���
				pro.store(new FileOutputStream("a.ini",true),null);
//			     ���Դ�a.ini��ͨ��Properties.get������ȡ������Ϣ
				pro.load(new FileInputStream("a.ini"));
			} catch (FileNotFoundException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			} catch (IOException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
			String ctext=String.valueOf(String.valueOf(pro.get("register")));
			System.out.println(ctext);
			BigInteger c=new BigInteger(ctext);
			//��ȡ˽Կ
			FileInputStream f=new FileInputStream("RSAPrikey.dat");
			ObjectInputStream b=new ObjectInputStream(f);
			RSAPrivateKey prk=(RSAPrivateKey)b.readObject();
			//�õ���Կ����������
			BigInteger d=prk.getPrivateExponent();
			BigInteger n=prk.getModulus();
			//System.out.println("���ܵ�˽Կ��ָ��Ϊ��"+d);
			//System.out.println("����˽Կ��ģΪ��"+n);
			//���ܴ���
			BigInteger m=c.modPow(d, n);
			byte [] mt=m.toByteArray();
			//System.out.println("���ܺ������Ϊ��");
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
